package com.hire.pdf.service;

import java.util.Map;

public interface HtmlToPdfPreProcessor {
	
	public String getResultantHtml(String htmlContent);
	
	public String getResultantCleanHtml(String htmlContent) throws Exception;
	
	public String fillTemplate(String htmlContent, Map<String,String> fillingObject) throws Exception;

}
