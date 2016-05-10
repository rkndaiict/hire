/**
 * 
 */
package com.hcentive.xlsx.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hcentive.poi.objects.Cell;
import com.hcentive.poi.objects.Row;
import com.hcentive.xlsx.annotations.processor.WBMappingInfo;
import com.hcentive.xlsx.errorhandler.XLSXErrorCode;
import com.hcentive.xlsx.errorhandler.XLSXErrorHandler;
import com.hcentive.xlsx.utils.XLSXUtility;

/**
 * This is the main handler which actually receives value held in every cell of spreadsheet 
 * as an event from POI event model. Scope of a handler is workbook instance. 
 * @author prashant
 *
 */
public class WorkbookHandler implements SheetContentsHandler{
	
	private Logger logger = LoggerFactory.getLogger(WorkbookHandler.class);

	private static final int HEADER_ROW_INDEX = 0;
	
	private Integer sheetIndex = null;
	private Object currentRow = null;
	private List<String> headers = null;
	private Integer currentRowIndex = 0;
	private CellValueProcessor cellValueProcessor = new XLSXCellValueProcessor();
	private WBMappingInfo mappingInfo = null;
	private XLSXErrorHandler errorHandler = null;
	private SheetHandler wbRowHandler = null;
	
	public WorkbookHandler()
	{
		// initialize the headers list
		if(headers == null)
			headers = new ArrayList<String>();
		
	}

	
	private Object createInstance()
	{
		try {
			if(mappingInfo !=null){
				return mappingInfo.getClazz().newInstance();
			}else{
				return new Row(); // class has not provided that means it should work with default Row and Cell model
			}
		} catch (InstantiationException e) {
			logger.error(e.getMessage(),e);
			errorHandler.handleError(XLSXErrorCode.X10001, e);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(),e);
			errorHandler.handleError(XLSXErrorCode.X10001, e);
		}
		
		return null;

	}
	
	@Override
	public void startRow(int rowNum) {

		// verify if Row handler has been registered, if not use default in-memory handler
		if(wbRowHandler == null){
			//TODO warn and provide hint for adding custom workbook handler
			throw new RuntimeException("Could not find any workbook row handler for sheet: "+sheetIndex);
		}
		currentRowIndex = rowNum;		
		currentRow = createInstance();
	}

	@Override
	public void endRow(){

		// if current row is header row and header is present then validate header
		if(isHeaderPresent() && (currentRowIndex == HEADER_ROW_INDEX))
		{
			if(!validateHeader())
			{
				//TODO log the error
				errorHandler.handleError(XLSXErrorCode.X10001, null);
			}
			
			try {
				wbRowHandler.processRow(currentRowIndex, currentRow);
			} catch (Exception e) {
				errorHandler.handleError(XLSXErrorCode.X10001, e);
			}
		} else
			try {
				wbRowHandler.processRow(currentRowIndex, currentRow);
			} catch (Exception e) {
				errorHandler.handleError(XLSXErrorCode.X10003, e);
			}
		currentRow = null;
	}

	@Override
	public void cell(String cellReference, String formattedValue) {
				
		// if cell belongs to header row, then populate value in header list
		if(isHeaderPresent() && (currentRowIndex == HEADER_ROW_INDEX)){
			headers.add(formattedValue);
		}else
		{
			// process cell value
			try {

				if(mappingInfo != null){
					String fieldName = mappingInfo.getMappings().get(XLSXUtility.resolveColumnReference(cellReference,currentRowIndex));
					if(!StringUtils.isEmpty(fieldName)){
						cellValueProcessor.process(currentRowIndex, cellReference, formattedValue,currentRow,fieldName);
					}
					else {// log warning message
						logger.warn("Row# "+currentRowIndex+",Column#"+cellReference+" Property name not specified.");
					}
				}else
				{
					Row row = (Row) currentRow;
					Cell cell = new Cell();
					cell.setColumnIndex(XLSXUtility.generateColumnIndex(cellReference));
					cell.setColumnReference(cellReference);
					cell.setRowIndex(currentRowIndex);
					cell.setValue(formattedValue);
					row.setRowIndex(currentRowIndex);
					row.setSheetIndex(sheetIndex);
					row.addCell(cell);
				}
					
			} catch (IllegalAccessException e) {
				errorHandler.handleError(XLSXErrorCode.X10004, e);
			} catch (InvocationTargetException e) {
				errorHandler.handleError(XLSXErrorCode.X10004, e);
			} catch (NoSuchMethodException e) {
				errorHandler.handleError(XLSXErrorCode.X10004, e);
			} catch (Exception e) {
				errorHandler.handleError(XLSXErrorCode.X10004, e);
			}

		}
		
	}

	@Override
	public void headerFooter(String text, boolean isHeader, String tagName) {
		//System.out.println("HeaderFooter : "+text+" "+isHeader+" "+tagName );
		
	}
	
	public void setWbRowHandler(SheetHandler wbRowHandler) {
		this.wbRowHandler = wbRowHandler;
	}

	private boolean validateHeader()
	{
		if(mappingInfo.getHeaders()==null && headers==null){
			return true;
		}
		
		if(mappingInfo.getHeaders()!=null && headers!=null){
			return mappingInfo.getHeaders().equals(headers);
		}
		
		return false;
	}
	
	private boolean isHeaderPresent()
	{
		if(mappingInfo == null)
			return false;
		return (mappingInfo.getHeaders() != null);
	}

	public void setMappingInfo(WBMappingInfo mappingInfo) {
		this.mappingInfo = mappingInfo;
	}

	public Integer getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(Integer sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public void setErrorHandler(XLSXErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

	public SheetHandler getWbRowHandler() {
		return wbRowHandler;
	}
	
	

}
