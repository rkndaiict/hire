/**
 * 
 */
package com.hcentive.xlsx.parser;

/**
 * @author prashant
 *
 */
public interface CellValueProcessor {

	/**
	 * Callback for handling the cell value
	 * @param rowNum
	 * @param cellReference
	 * @param value
	 * @param destObject
	 * @param fieldName
	 * @throws Exception
	 * @return
	 */
	public Object process(Integer rowNum, String cellReference, String value,Object destObject,String fieldName) throws Exception;
}
