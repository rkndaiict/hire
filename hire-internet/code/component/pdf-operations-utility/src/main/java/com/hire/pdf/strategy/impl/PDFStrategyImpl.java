package com.hire.pdf.strategy.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hcentive.utils.util.CommonUtil;
import com.hire.pdf.domain.PDFTemplateConfig;
import com.hire.pdf.service.PDFProcessorService;
import com.hire.pdf.strategy.PDFStrategy;
import com.itextpdf.text.DocumentException;

@Component
public class PDFStrategyImpl implements PDFStrategy {

	@Autowired
	private PDFProcessorService pdfProcessorService;

	@Override
	public byte[] execute(List<Map<String, String>> plans, byte[] primaryTemplateBytes, PDFTemplateConfig pdfTemplateConfig) throws IOException, DocumentException {

		String planTableName = pdfTemplateConfig.getTableName();
		int primaryTemplateRows = pdfTemplateConfig.getPrimaryTemplateRows();
		int secondaryTemplateRows = pdfTemplateConfig.getSecondaryTemplateRows();
		int planTablePageNo = pdfTemplateConfig.getTableStartIndex();

		int totalPlans = plans.size();
		Map<String, String> pdfFieldsMap = new HashMap<String, String>();

		int pagesToBeAdded = (int) Math.ceil((double) (totalPlans-primaryTemplateRows)/secondaryTemplateRows);

		pdfProcessorService.prepareMapForPDFTemplateTable(plans, pdfFieldsMap, primaryTemplateRows, planTableName);
		byte[] primaryPDFBytes = pdfProcessorService.writePDF(pdfFieldsMap, primaryTemplateBytes);
		primaryPDFBytes = pdfProcessorService.freezePDF(primaryPDFBytes);

		List<byte[]> secondaryPDFList = new ArrayList<byte[]>();
		for(int i = 0; i<pagesToBeAdded; i++){
			pdfFieldsMap = new HashMap<String, String>();
			pdfProcessorService.prepareMapForPDFTemplateTable(plans, pdfFieldsMap, secondaryTemplateRows, planTableName);
			byte[] secondaryPDFBytes = pdfProcessorService.writePDF(pdfFieldsMap, CommonUtil.getBytesFromFile(new File(pdfTemplateConfig.getSecondaryTemplateLocation())));
			secondaryPDFList.add(pdfProcessorService.freezePDF(secondaryPDFBytes));
		}

		byte[] outputPDF = pdfProcessorService.mergePDFs(primaryPDFBytes, secondaryPDFList, planTablePageNo);
		return outputPDF;
	}
}
