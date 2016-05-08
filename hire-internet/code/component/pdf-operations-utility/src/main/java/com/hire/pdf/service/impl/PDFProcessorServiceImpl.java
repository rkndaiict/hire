package com.hire.pdf.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.hire.pdf.service.PDFProcessorService;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

@Service
public class PDFProcessorServiceImpl implements PDFProcessorService {

	@Override
	public void prepareGenericMapForPDFTemplate(Map<String, Object> paramsMap, Map<String, String> pdfFieldsMap) {
		if (paramsMap != null && paramsMap.size() > 0) {
			Set<String> paramsKeySet = paramsMap.keySet();
			if (paramsKeySet != null) {
				for (String s : paramsKeySet) {
					if (StringUtils.isNotEmpty(s) && paramsMap.get(s) != null) {
						pdfFieldsMap.put(s, paramsMap.get(s).toString());
					}
				}
			}
		}
	}

	@Override
	public void prepareMapForPDFTemplateTable(List<Map<String, String>> tableRows, Map<String, String> pdfFieldsMap, int rowsPerPage, String tableName) {
		Iterator<Map<String, String>> rowIterator = tableRows.iterator();
		int i = 1;
		while (i <= rowsPerPage && rowIterator.hasNext()) {
			Map<String, String> rowMap = rowIterator.next();
			preparePDFTableMap(rowMap, pdfFieldsMap, tableName + i);
			i++;
			rowIterator.remove();
		}
	}

	private void preparePDFTableMap(Map<String, String> rowMap, Map<String, String> pdfFieldsMap, String fieldPrefix) {
		Set<String> rowKeySet = rowMap.keySet();
		for (String key : rowKeySet) {
			pdfFieldsMap.put(fieldPrefix + key, rowMap.get(key));
		}
	}

	@Override
	public byte[] writePDF(Map<String, String> pdfFieldsMap, byte[] templatePDFBytes) throws IOException, DocumentException {
		ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
		PdfReader pdfTemplate = new PdfReader(templatePDFBytes);
		PdfStamper pdfStamper = new PdfStamper(pdfTemplate, pdfOutputStream);
		AcroFields form = pdfStamper.getAcroFields();
		if (form != null && pdfFieldsMap != null) {
			for (String key : pdfFieldsMap.keySet()) {
				if (pdfFieldsMap.get(key) != null) {
					form.setField(key, pdfFieldsMap.get(key));
				}
			}
		}

		pdfStamper.close();
		byte[] pdfBytes = pdfOutputStream.toByteArray();
		pdfOutputStream.close();
		pdfTemplate.close();
		return pdfBytes;
	}

	@Override
	public byte[] mergePDFs(byte[] primaryPDFBytes, List<byte[]> pdfList, int location) throws IOException, DocumentException {
		Document document = new Document();
		ByteArrayOutputStream outputPDF = new ByteArrayOutputStream();
		PdfCopy copy = new PdfCopy(document, outputPDF);
		document.open();
		PdfImportedPage page;
		PdfCopy.PageStamp stamp;
		PdfReader reader1 = new PdfReader(primaryPDFBytes);
		int n1 = reader1.getNumberOfPages();

		for (int i = 1; i <= n1; i++) {
			page = copy.getImportedPage(reader1, i);
			stamp = copy.createPageStamp(page);
			stamp.alterContents();
			copy.addPage(page);
			if (i == location) {
				for (byte[] pdfBytes : pdfList) {
					PdfReader reader2 = new PdfReader(pdfBytes);
					int n2 = reader2.getNumberOfPages();
					for (int j = 1; j <= n2; j++) {
						page = copy.getImportedPage(reader2, j);
						stamp = copy.createPageStamp(page);
						stamp.alterContents();
						copy.addPage(page);
					}
				}
			}
		}
		document.close();
		return outputPDF.toByteArray();
	}

	@Override
	public byte[] freezePDF(byte[] inputPDFBytes) throws IOException, DocumentException {
		PdfReader reader = new PdfReader(inputPDFBytes);
		PdfStamper stamper;
		ByteArrayOutputStream outputByteStream = new ByteArrayOutputStream();
		stamper = new PdfStamper(reader, outputByteStream);
		stamper.setFormFlattening(true);
		stamper.close();
		return outputByteStream.toByteArray();
	}
}
