/**
 * 
 */
package com.hcentive.poi.objects;

/**
 * @author prashant
 *
 */
public class Cell {

	/**
	 * Row index in the sheet starting from 0
	 */
	private Integer rowIndex = null;
	
	/**
	 * Column Index in the sheet starting from 0
	 */
	private Integer columnIndex = null;
	
	/**
	 * Column Reference in the sheet starting from A
	 */
	private String columnReference = null;
	
	private Object value = null;
	
	/**
	 * Sheet Index in the sheet starting from 0
	 */
	private Integer sheetIndex = null;

	/**
	 * Row index in the sheet starting from 0
	 * @return
	 */
	public Integer getRowIndex() {
		return rowIndex;
	}

	/**
	 * Row index in the sheet starting from 0
	 * @param rowIndex
	 */
	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * Column Index in the sheet starting from 0
	 * @return
	 */
	public Integer getColumnIndex() {
		return columnIndex;
	}

	/**
	 * Column Index in the sheet starting from 0
	 * @param columnIndex
	 */
	public void setColumnIndex(Integer columnIndex) {
		this.columnIndex = columnIndex;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Sheet Index in the sheet starting from 0
	 * @return
	 */
	public Integer getSheetIndex() {
		return sheetIndex;
	}

	/**
	 * Sheet Index in the sheet starting from 0
	 * @param sheetIndex
	 */
	public void setSheetIndex(Integer sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	/**
	 * Column Reference in the sheet starting from A
	 * @return
	 */
	public String getColumnReference() {
		return columnReference;
	}

	/**
	 * Column Reference in the sheet starting from A
	 * @param columnReference
	 */
	public void setColumnReference(String columnReference) {
		this.columnReference = columnReference;
	}
	
	
	
}
