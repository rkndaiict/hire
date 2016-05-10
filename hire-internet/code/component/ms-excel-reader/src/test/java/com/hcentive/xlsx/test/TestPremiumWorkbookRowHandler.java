/**
 * 
 */
package com.hcentive.xlsx.test;

import com.hcentive.poi.objects.Row;
import com.hcentive.xlsx.parser.SheetHandler;
import com.hcentive.xlsx.test.poi.objects.Premium;
import com.hcentive.xlsx.test.poi.objects.Rider;

/**
 * @author prashant
 *
 */
public class TestPremiumWorkbookRowHandler implements SheetHandler {

	private int count = 0;
	@Override
	public void processRow(Integer rowNum, Object row) throws Exception {
		
		if(row instanceof Premium){
			Premium premium = (Premium) row;
			System.out.println(premium);
		}else if(row instanceof Row)
		{
			Row objRow = (Row) row;
			System.out.println(objRow);
		}else if(row instanceof Rider){
			Rider rider = (Rider) row;
			System.out.println(rider);
		}
		count++;
		
	}
	
	public int getCount()
	{
		return count;
	}

}
