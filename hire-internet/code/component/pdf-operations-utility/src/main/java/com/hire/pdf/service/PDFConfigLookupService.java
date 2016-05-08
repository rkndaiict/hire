package com.hire.pdf.service;

import java.io.IOException;

import com.hcentive.utils.error.BusinessException;
import com.hire.pdf.domain.PDFTemplateConfig;

/**
 * Provides Lookup service for templates and configuration.
 * 
 */
public interface PDFConfigLookupService {

	PDFTemplateConfig getPDFTemplateConfig(String context) throws BusinessException;

	/**
	 * Fetches the PDF template as byte[] from the specified location on the disk
	 * 
	 * @param templateLocation
	 * @return
	 * @throws IOException
	 * 
	 * @author Shalini Gupta
	 */
	byte[] getTemplateByLocation(String templateLocation) throws IOException;
}
