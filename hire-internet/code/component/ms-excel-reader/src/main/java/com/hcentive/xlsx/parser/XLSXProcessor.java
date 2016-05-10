package com.hcentive.xlsx.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * @author prashant
 * 
 */
public class XLSXProcessor {

	private final Logger logger = LoggerFactory.getLogger(XLSXProcessor.class);

	private OPCPackage xlsxPackage;

	private XLSXBindingContext ctx = null;

	public XLSXProcessor(XLSXBindingContext context) {
		this.ctx = context;
	}

	public XLSXProcessor() throws Exception {
		this(null);
	}

	public void init(String fileName) throws Exception
	{
		try {

			File file = new File(fileName);
			InputStream inputStream = new FileInputStream(file);

			xlsxPackage = OPCPackage.open(inputStream);

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			throw e;
		} catch (InvalidFormatException e) {
			logger.error(e.getMessage());
			throw e;
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}

		logger.info("Parser initialized.");
	}

	public void init(InputStream inputStream) throws Exception
	{
		try {
			//
			// File file = new File(fileName);
			// InputStream inputStream = new FileInputStream(file);

			xlsxPackage = OPCPackage.open(inputStream);

		} catch (FileNotFoundException e) {
			logger.error(e.getMessage());
			throw e;
		} catch (InvalidFormatException e) {
			logger.error(e.getMessage());
			throw e;
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}

		logger.info("Parser initialized.");
	}

	public void close()
	{
		try {
			xlsxPackage.close();
			ctx.destroy();
		} catch (IOException e) {
			logger.error("Error while closing Package: " + e.getMessage());
		}
	}

	private void processSheet(Integer sheetIndex, SheetHandler rowHandler) throws Exception
	{
		logger.info("Processing of sheet# " + sheetIndex + " started");
		ReadOnlySharedStringsTable readOnlyStrings = new ReadOnlySharedStringsTable(xlsxPackage);
		XSSFReader xssfReader = new XSSFReader(xlsxPackage);
		Iterator<InputStream> sheets = xssfReader.getSheetsData();

		for (int sheet = 0; sheets.hasNext(); sheet++) {
			InputStream is = sheets.next();
			if (sheetIndex.equals(sheet)) {
				logger.debug("Sheet with index" + sheetIndex + " found.");
				WorkbookHandler handler = null;
				if (rowHandler == null) {
					handler = ctx.getWbFactory().createWorkbookHandler(sheet, sheet);
				} else {
					handler = ctx.getWbFactory().createWorkbookHandler(sheetIndex, sheet, rowHandler);
				}
				if (handler != null) {
					logger.debug("Workbook Handler created");
					readSheet(handler, is, readOnlyStrings, xssfReader.getStylesTable());
				}
			}
			IOUtils.closeQuietly(is);
			logger.debug("Closing sheet inputstream.");
		}
	}

	private Integer processSheet(String sheetName, int mappingIndex, SheetHandler rowHandler) throws Exception
	{
		logger.info("Processing of sheet with name # " + sheetName + " started");
		ReadOnlySharedStringsTable readOnlyStrings = new ReadOnlySharedStringsTable(xlsxPackage);
		XSSFReader xssfReader = new XSSFReader(xlsxPackage);
		StylesTable styles = xssfReader.getStylesTable();

		XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader
				.getSheetsData();
		int index = 0;
		Integer sheetIndex = null;
		while (iter.hasNext()) {
			InputStream stream = iter.next();
			String currentSheetName = iter.getSheetName();

			if (currentSheetName.equalsIgnoreCase(sheetName)) {
				sheetIndex = index;
				logger.debug("Sheet with index" + index + " found.");
				WorkbookHandler handler = null;
				if (rowHandler == null) {
					handler = ctx.getWbFactory().createWorkbookHandler(sheetIndex, mappingIndex);
				} else {
					handler = ctx.getWbFactory().createWorkbookHandler(sheetIndex, mappingIndex, rowHandler);
				}
				if (handler != null) {
					logger.debug("Workbook Handler created");
					readSheet(handler, stream, readOnlyStrings, xssfReader.getStylesTable());
				}
			}
			// processSheet(styles, readOnlyStrings, stream);
			stream.close();
			++index;
		}

		return sheetIndex;
	}

	/**
	 * This implementation uses custom/provided implementation of sheet handler.
	 * This will invoke the callback of handler for a row.
	 * 
	 * @param sheetIndex
	 * @return
	 * @throws Exception
	 */
	public void process(Integer sheetIndex, SheetHandler rowHandler) throws Exception
	{
		try {
			processSheet(sheetIndex, rowHandler);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		} finally {
			closeHandler(sheetIndex);
		}

		logger.info("Exit processing gracefully.");
	}

	/**
	 * This implementation uses default implementation of sheet handler i.e. in
	 * memory map of row objects. As per method signature this returns instance
	 * of sheetHandler with workbook data for further use. This object must be
	 * closed externally by invoking closeHandler() method on the processor
	 * object.
	 * 
	 * @param sheetIndex
	 * @return
	 * @throws Exception
	 */
	public SheetHandler process(Integer sheetIndex) throws Exception {
		try {
			processSheet(sheetIndex, null);
			logger.info("Exit processing gracefully.");
			return getWorkbookRowHandler(sheetIndex);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	public SheetHandler process(String sheetName, int mappingIndex) throws Exception {
		try {
			Integer sheetIndex = processSheet(sheetName, mappingIndex, null);
			logger.info("Exit processing gracefully.");
			if (sheetIndex == null) {
				return null;
			}
			return getWorkbookRowHandler(sheetIndex);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
	}

	private void readSheet(SheetContentsHandler sheetHandler,
			InputStream sheetInputStream,
			ReadOnlySharedStringsTable sharedStringsTable, StylesTable styles)
			throws Exception {
		logger.info("Reading sheet data.");
		try {
			SAXParserFactory saxFactory = SAXParserFactory.newInstance();
			InputSource sheetSource = new InputSource(sheetInputStream);
			logger.debug("SAX Parser Factory initialized");
			SAXParser saxParser = saxFactory.newSAXParser();
			XMLReader sheetParser = saxParser.getXMLReader();
			logger.debug("XML Reader initialized");
			ContentHandler contentHandler = new XSSFSheetXMLHandler(styles,
					sharedStringsTable, sheetHandler, false);
			logger.debug("Content handler initialized");
			sheetParser.setContentHandler(contentHandler);

			sheetParser.parse(sheetSource);
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		} catch (SAXException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new Exception(e);
		}
		logger.info("Done with reading/parsing sheet data.");
	}

	public Map<Integer, String> fetchSheetDetails() throws Exception
	{
		Map<Integer, String> sheetDetails = new HashMap<Integer, String>();
		XSSFReader xssfReader = new XSSFReader(xlsxPackage);
		XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
		int SheetIndex = 0;
		while (iter.hasNext()) {
			InputStream stream = null;
			try {
				stream = iter.next();
				sheetDetails.put(SheetIndex, iter.getSheetName());
				++SheetIndex;
			} catch (Exception e) {
				logger.error("Encountered exception while fetching details of sheets of file");

			} finally {
				if (stream != null) {
					stream.close();
				}

			}
		}

		return sheetDetails;
	}

	private SheetHandler getWorkbookRowHandler(Integer sheetIndex)
	{
		return ctx.getWbFactory().getWorkbookRowHandler(sheetIndex);
	}

	public void closeHandler(Integer sheetIndex) {
		ctx.getWbFactory().closeHandler(sheetIndex);
	}
}
