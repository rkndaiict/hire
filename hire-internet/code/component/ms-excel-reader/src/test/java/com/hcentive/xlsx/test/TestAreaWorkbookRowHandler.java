/**
 * 
 */
package com.hcentive.xlsx.test;

import java.util.HashMap;
import java.util.Map;

import com.hcentive.xlsx.parser.SheetHandler;

/**
 * @author prashant
 *
 */
public class TestAreaWorkbookRowHandler implements SheetHandler {

	private Map<Integer, Object> worbookData = null;
	@Override
	public void processRow(Integer rowNum, Object row) throws Exception {
		
		if(worbookData == null)
			worbookData = new HashMap<Integer,Object>();
		
		worbookData.put(rowNum, row);
		
	}

	public Map<Integer, Object> getWorbookData() {
		return worbookData;
	}

}
