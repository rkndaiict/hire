/**
 * 
 */
package com.hcentive.xlsx.parser;

import java.util.HashMap;
import java.util.Map;

/**
 * @author prashant
 * @param <T>
 *
 */
public class DefaultSheetHandler implements SheetHandler {
	
	private Map<Integer, Object> worbookData = null;

	/* (non-Javadoc)
	 * @see com.hcentive.xlsx.parser.SheetHandler#processRow(java.lang.Integer, java.lang.Object)
	 */
	@Override
	public void processRow(Integer rowNum, Object row) throws Exception {
		
		if(worbookData == null)
			worbookData = new HashMap<Integer, Object>();
		
		worbookData.put(rowNum, row);
		
	}

	public Map<Integer, Object> getWorbookData() {
		return worbookData;
	}

}
