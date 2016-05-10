/**
 * 
 */
package com.hcentive.poi.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author prashant
 *
 */
public class Row {
	
	private Integer sheetIndex = null;
	
	private String sheetName = null;
	
	private Integer rowIndex = null;
	
	private List<Cell> cells = null;

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
	 * Row Index in the sheet starting from 0
	 * @return
	 */
	public Integer getRowIndex() {
		return rowIndex;
	}

	/**
	 * Row Index in the sheet starting from 0
	 * @return
	 */
	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}

	public List<Cell> getCells() {
		return cells;
	}

	/**
	 * @return the sheetName
	 */
	public String getSheetName() {
		return sheetName;
	}

	/**
	 * @param sheetName the sheetName to set
	 */
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public void addCell(Cell cell)
	{
		if(cells == null)
			cells = new ArrayList<Cell>();
		cells.add(cell);
	}

	@Override
	public String toString() {
		return "Row [sheetIndex=" + sheetIndex + ", rowIndex=" + rowIndex
				+ ", cells=" + cells + "]";
	}
	

	
}
