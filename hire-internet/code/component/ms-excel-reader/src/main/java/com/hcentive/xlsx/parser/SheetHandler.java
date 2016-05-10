/**
 * 
 */
package com.hcentive.xlsx.parser;

/**
 * This interface must be implemented and registered with WorkbookHandler in order to capture and process the workbook row object.
 * 
 * @author prashant
 *
 */
public interface SheetHandler {

	/**
	 * Callback method for processing the row object.
	 * The type of Row object depends on XLSXReader initialization, if reader has been initialized in default mode then 
	 * object of type com.hcentive.poi.objects.Row will be returned and if processor has been initialised with mapping classes then object of mapped class will be provided.
	 * @param rowNum
	 * @param row
	 * @throws Exception
	 */
	public void processRow(Integer rowNum,Object row) throws Exception;
}
