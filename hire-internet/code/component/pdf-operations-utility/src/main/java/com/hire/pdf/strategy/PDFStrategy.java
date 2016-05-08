package com.hire.pdf.strategy;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.hire.pdf.domain.PDFTemplateConfig;
import com.itextpdf.text.DocumentException;

public interface PDFStrategy {

	public byte[] execute(List<Map<String, String>> paramsList, byte[] primaryTemplatePDFBytes, PDFTemplateConfig templateConfig) throws IOException, DocumentException;

}
