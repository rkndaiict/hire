/**
 * 
 */
package com.hcentive.xlsx.annotations.processor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author prashant
 * 
 */
public class WBMappingInfo {

	private Class<?> clazz = null;
	private Map<String, String> mappings = null;

	private int[] sheetIndexes = null;

	private String[] sheetNames = null;

	public String[] getSheetNames() {
		return sheetNames;
	}

	public void setSheetNames(String[] sheetNames) {
    this.sheetNames = Arrays.copyOf(sheetNames, sheetNames.length); 
  }

	private List<String> headers = null;

	public Map<String, String> getMappings() {
		return mappings;
	}

	public void setMappings(Map<String, String> mappings) {
		this.mappings = mappings;
	}

	public Integer getSheetIndex() {
		return sheetIndexes[0];
	}

	public int[] getSheetIndexes() {
		return sheetIndexes;
	}

	public void setSheetIndexes(int[] sheetIndexes) {
	  this.sheetIndexes = Arrays.copyOf(sheetIndexes, sheetIndexes.length); 
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(String headers) {
		this.headers = Arrays.asList(headers.split(","));
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}

}
