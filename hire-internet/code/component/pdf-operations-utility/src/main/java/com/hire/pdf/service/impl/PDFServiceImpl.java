package com.hire.pdf.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcentive.utils.error.BusinessException;
import com.hcentive.utils.error.Error;
import com.hcentive.utils.error.ErrorCode;
import com.hcentive.utils.util.CommonUtil;
import com.hire.pdf.domain.PDFCreationInfo;
import com.hire.pdf.domain.PDFTemplateConfig;
import com.hire.pdf.service.PDFConfigLookupService;
import com.hire.pdf.service.PDFProcessorService;
import com.hire.pdf.service.PDFService;
import com.hire.pdf.strategy.PDFStrategy;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * <p>
 * This utility offers services to create PDF files.
 * </p>
 * 
 * <p>
 * User can provide a map of values and template to simply create a PDF by injecting the values into the place holders defined in template which is also in PDF format.
 * </p>
 * 
 * <p>
 * Also, more complex PDF files can be generated, like ones having a table or multiple tables using configurations provided.
 * </p>
 * 
 * 
 */
@Service
public class PDFServiceImpl implements PDFService {

	private static final Logger logger = LoggerFactory.getLogger(PDFServiceImpl.class);

	/*
	 * Support for creating PDF having table will be fully implemented later.
	 * Only configuration injection has to be modified from the existing
	 * implementation. Also, support for creating multiple tables in a PDF will
	 * be provided.
	 * 
	 * private PDFStrategy pdfStrategy;
	 */

	@Autowired
	private PDFProcessorService pdfProcessorService;

	@Autowired
	private PDFConfigLookupService pdfConfigLookupService;

	private PDFStrategy pdfStrategy;

	@Override
	public byte[] createPDFUsingTemplate(PDFCreationInfo pdfCreationInfo) throws BusinessException, IOException {

		byte[] pdfTemplate = pdfConfigLookupService.getTemplateByLocation(pdfCreationInfo.getPdfTemplatesLocation());

		if (pdfTemplate == null) {
			logger.debug("Pdf template is not found at specified path: " + pdfCreationInfo.getPdfTemplatesLocation());
			return null;
		}
		logger.debug("Processing pdf template: " + pdfCreationInfo.getPdfTemplatesLocation() + "");

		Map<String, Object> filteredParamsMap = filterParamsMap(pdfCreationInfo.getPdfValuesMap(), pdfCreationInfo.getPdfTemplatesLocation());
		Map<String, String> pdfFieldsMap = new HashMap<String, String>();
		pdfProcessorService.prepareGenericMapForPDFTemplate(filteredParamsMap, pdfFieldsMap);

		byte[] outputPDF = null;
		try {
			outputPDF = pdfProcessorService.writePDF(pdfFieldsMap, pdfTemplate);
			if (outputPDF != null) {
				outputPDF = pdfProcessorService.freezePDF(outputPDF);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException(new Error(ErrorCode.INTERNAL_ERROR, "Error occured while writing and freezing pdf :" + pdfCreationInfo.getPdfTemplatesLocation()));
		}
		return outputPDF;
	}

	@Override
	public byte[] createPDFUsingTemplateAndConfig(PDFCreationInfo pdfCreationInfo) throws BusinessException, IOException {
		/*
		 * PDFTemplateConfig pdfTemplateConfig =
		 * pdfConfigLookupService.getPDFTemplateConfig
		 * (pdfCreationInfo.getContext());
		 * 
		 * Map<String, Object> filteredParamsMap =
		 * filterParamsMap(pdfCreationInfo.getPdfValuesMap(),
		 * pdfCreationInfo.getContext()); Map<String, String> pdfFieldsMap = new
		 * HashMap<String, String>();
		 * pdfProcessorService.prepareGenericMapForPDFTemplate
		 * (filteredParamsMap, pdfFieldsMap);
		 * 
		 * byte[] outputPDF = null; try{ outputPDF =
		 * pdfProcessorService.writePDF(pdfFieldsMap,
		 * CommonUtil.getBytesFromFile(new
		 * File(pdfTemplateConfig.getPrimaryTemplateLocation())));
		 * if(pdfStrategy != null){ outputPDF =
		 * pdfStrategy.execute((List<Map<String,
		 * String>>)pdfCreationInfo.getPdfValuesMap
		 * ().get(pdfCreationInfo.getContext()), outputPDF, pdfTemplateConfig);
		 * } outputPDF = pdfProcessorService.freezePDF(outputPDF); }
		 * catch(Exception e){ logger.error(e.getMessage()); throw new
		 * BusinessException(new Error(ErrorCode.INTERNAL_ERROR,
		 * "Error occured while generating attachment")); } return outputPDF;
		 */
		return null;
	}

	private Map<String, Object> filterParamsMap(Map<String, Object> pdfParamsMap, String excludeKey) {
		Map<String, Object> filteredParamsMap = new HashMap<String, Object>();
		for (String key : pdfParamsMap.keySet()) {
			if (!key.equals(excludeKey)) {
				filteredParamsMap.put(key, pdfParamsMap.get(key));
			}
		}
		return filteredParamsMap;
	}

	/*
	 * public PDFStrategy getPdfStrategy() { return pdfStrategy; }
	 * 
	 * public void setPdfStrategy(PDFStrategy pdfStrategy) { this.pdfStrategy =
	 * pdfStrategy; }
	 */

	@Override
	public ByteArrayOutputStream getAllPdfMergedInOne(List<String> pdfTemplateLocation, Map<String, Object> mappedPropertyValue) throws BusinessException {
		List<InputStream> listOfPdfInInputStream = new ArrayList<InputStream>();
		if (pdfTemplateLocation != null && pdfTemplateLocation.size() > 0) {
			for (String pdfTemplatePath : pdfTemplateLocation) {
				InputStream inputStream = populatePdf(pdfTemplatePath, mappedPropertyValue);
				if (inputStream != null) {
					listOfPdfInInputStream.add(inputStream);
				}
			}
		}
		if (listOfPdfInInputStream.size() > 0) {
			return doMergedPdf(listOfPdfInInputStream);
		}
		return null;
	}

	private InputStream populatePdf(String samplePDFTemplateLocation, Map<String, Object> values) throws BusinessException {
		PDFCreationInfo pdfCreationInfo = new PDFCreationInfo();
		pdfCreationInfo.setPdfTemplatesLocation(samplePDFTemplateLocation);
		pdfCreationInfo.setPdfValuesMap(values);
		byte[] outputPDF = null;
		InputStream inputStream = null;
		try {
			outputPDF = createPDFUsingTemplate(pdfCreationInfo);
			if (outputPDF != null && outputPDF.length > 0) {
				inputStream = new ByteArrayInputStream(outputPDF);
			}
		} catch (IOException e) {
			throw new BusinessException();
		}
		return inputStream;
	}

	/**
	 * Merge multiple pdf into one pdf
	 * 
	 * @param list
	 *            of pdf input stream
	 * @param outputStream
	 *            output file output stream
	 * @throws DocumentException
	 * @throws IOException
	 */
	private ByteArrayOutputStream doMergedPdf(List<InputStream> list) throws BusinessException {
		Document document = new Document();
		OutputStream outputStream = new ByteArrayOutputStream();
		try {
			PdfWriter writer = PdfWriter.getInstance(document, outputStream);
			document.open();
			PdfContentByte cb = writer.getDirectContent();
			for (InputStream in : list) {
				PdfReader reader = new PdfReader(in);
				for (int i = 1; i <= reader.getNumberOfPages(); i++) {
					document.newPage();
					PdfImportedPage page = writer.getImportedPage(reader, i);
					cb.addTemplate(page, 0, 0);
				}
			}
			outputStream.flush();
			document.close();
			outputStream.close();
		} catch (DocumentException documentException) {
			throw new BusinessException();
		} catch (IOException ioException) {
			throw new BusinessException();
		}
		return (ByteArrayOutputStream) outputStream;
	}

	@Override
	public byte[] createPDF(String contextParam, Map<String, Object> pdfParamsMap) throws BusinessException {
		PDFTemplateConfig pdfTemplateConfig = pdfConfigLookupService.getPDFTemplateConfig(contextParam);

		Map<String, Object> filteredParamsMap = filterParamsMap(pdfParamsMap, contextParam);
		Map<String, String> pdfFieldsMap = new HashMap<String, String>();
		pdfProcessorService.prepareGenericMapForPDFTemplate(filteredParamsMap, pdfFieldsMap);

		byte[] outputPDF = null;
		try {
			outputPDF = pdfProcessorService.writePDF(pdfFieldsMap, CommonUtil.getBytesFromFile(new File(pdfTemplateConfig.getPrimaryTemplateLocation())));
			if (pdfStrategy != null) {
				outputPDF = pdfStrategy.execute((List<Map<String, String>>) pdfParamsMap.get(contextParam), outputPDF, pdfTemplateConfig);
			}
			outputPDF = pdfProcessorService.freezePDF(outputPDF);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException(new Error(ErrorCode.INTERNAL_ERROR, "Error occured while generating attachment"));
		}
		return outputPDF;
	}

	public PDFStrategy getPdfStrategy() {
		return pdfStrategy;
	}

	public void setPdfStrategy(PDFStrategy pdfStrategy) {
		this.pdfStrategy = pdfStrategy;
	}
}
