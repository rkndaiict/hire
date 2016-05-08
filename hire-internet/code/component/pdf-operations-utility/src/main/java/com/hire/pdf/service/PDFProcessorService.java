package com.hire.pdf.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.DocumentException;

public interface PDFProcessorService {

	public void prepareGenericMapForPDFTemplate(Map<String, Object> paramsMap, Map<String, String> pdfFieldsMap);

	public void prepareMapForPDFTemplateTable(List<Map<String, String>> tableRows, Map<String, String> pdfFieldsMap, int rowsPerPage, String tableName);

	public byte[] writePDF(Map<String, String> pdfFieldsMap, byte[] templatePDFBytes) throws IOException, DocumentException;

	public byte[] mergePDFs(byte[] primaryPDFBytes, List<byte[]> pdfList, int location) throws IOException, DocumentException;

	byte[] freezePDF(byte[] inputPDFBytes) throws IOException, DocumentException;

}
