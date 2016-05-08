package com.hire.pdf.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.hcentive.utils.error.BusinessException;
import com.hire.pdf.domain.PDFCreationInfo;

/**
 * 
 * 
 */
public interface PDFService {

	/**
	 * Creates a PDF file using a Map of values and a template which is itself in PDF format, having place holders having names to which values in the provided map are mapped.
	 * 
	 * @param pdfCreationInfo
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	byte[] createPDFUsingTemplate(PDFCreationInfo pdfCreationInfo) throws BusinessException, IOException;

	/**
	 * Creates a PDF using Map of values and configuration.
	 * 
	 * @param pdfCreationInfo
	 * @return
	 * @throws BusinessException
	 * @throws IOException
	 */
	byte[] createPDFUsingTemplateAndConfig(PDFCreationInfo pdfCreationInfo) throws BusinessException, IOException;

	public ByteArrayOutputStream getAllPdfMergedInOne(List<String> pdfTemplateLocation, Map<String, Object> mappedPropertyValue) throws BusinessException;

	byte[] createPDF(String contextParam, Map<String, Object> pdfFieldsMap) throws BusinessException;
}
