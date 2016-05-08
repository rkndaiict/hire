package com.hire.pdf.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcentive.phix.dms.DMSService;
import com.hcentive.utils.error.BusinessException;
import com.hcentive.utils.error.Error;
import com.hcentive.utils.error.ErrorCode;
import com.hire.pdf.domain.PDFTemplateConfig;
import com.hire.pdf.service.PDFConfigLookupService;

/**
 * 
 * 
 */
@Service
public class PDFConfigLookupServiceImpl implements PDFConfigLookupService {

	private static final Logger logger = LoggerFactory.getLogger(PDFConfigLookupServiceImpl.class);

	@Autowired
	private DMSService dMSService;

	private List<PDFTemplateConfig> pdfTemplateConfigList;

	@Override
	public byte[] getTemplateByLocation(String templateLocation) throws IOException {
		logger.debug("+getEnrollmetTemplateByLocation+");
		InputStream inputStream = dMSService.getDocument(templateLocation);
		if (inputStream != null && inputStream.available() > 0) {
			logger.debug("-getEnrollmetTemplateByLocation found-");
			return IOUtils.toByteArray(inputStream);
		}
		logger.debug("-getEnrollmetTemplateByLocation-");
		return null;
	}

	@Override
	public PDFTemplateConfig getPDFTemplateConfig(String context) throws BusinessException {
		if (context == null) {
			throw new BusinessException(new Error(ErrorCode.INVALID_ARGUMENTS, "context cannot be null"));
		}
		PDFTemplateConfig pdfTemplateConfig = null;
		if (pdfTemplateConfigList != null && pdfTemplateConfigList.size() > 0) {
			for (PDFTemplateConfig templateConfig : pdfTemplateConfigList) {
				if (templateConfig.getContext().equals(context)) {
					pdfTemplateConfig = templateConfig;
					break;
				}
			}
		}
		if (pdfTemplateConfig == null) {
			throw new BusinessException(new Error(ErrorCode.DATA_NOT_FOUND, "No PDF Template Configuration found for context :" + context));
		}
		return pdfTemplateConfig;
	}

	public List<PDFTemplateConfig> getPdfTemplateConfigList() {
		return pdfTemplateConfigList;
	}

	public void setPdfTemplateConfigList(List<PDFTemplateConfig> pdfTemplateConfigList) {
		this.pdfTemplateConfigList = pdfTemplateConfigList;
	}
}
