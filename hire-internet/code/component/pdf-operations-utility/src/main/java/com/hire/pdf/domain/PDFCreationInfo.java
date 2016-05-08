/**
 * 
 */
package com.hire.pdf.domain;

import java.util.Map;

/**
 *
 */
public class PDFCreationInfo {

	private Map<String, Object> pdfValuesMap;

	private String pdfTemplatesLocation;

	private String context;

	/**
	 * @return the pdfValuesMap
	 */
	public Map<String, Object> getPdfValuesMap() {
		return pdfValuesMap;
	}

	/**
	 * @param pdfValuesMap the pdfValuesMap to set
	 */
	public void setPdfValuesMap(Map<String, Object> pdfValuesMap) {
		this.pdfValuesMap = pdfValuesMap;
	}

	/**
	 * @return the pdfTemplatesLocation
	 */
	public String getPdfTemplatesLocation() {
		return pdfTemplatesLocation;
	}

	/**
	 * @param pdfTemplatesLocation the pdfTemplatesLocation to set
	 */
	public void setPdfTemplatesLocation(String pdfTemplatesLocation) {
		this.pdfTemplatesLocation = pdfTemplatesLocation;
	}

	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(String context) {
		this.context = context;
	}


}
