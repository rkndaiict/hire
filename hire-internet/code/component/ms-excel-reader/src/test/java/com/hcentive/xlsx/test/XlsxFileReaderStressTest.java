/**
 * 
 */
package com.hcentive.xlsx.test;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hcentive.xlsx.parser.DefaultSheetHandler;
import com.hcentive.xlsx.parser.XLSXBindingContext;
import com.hcentive.xlsx.parser.XLSXProcessor;
import com.hcentive.xlsx.test.poi.objects.Area;
import com.hcentive.xlsx.test.poi.objects.FamilyDisc;
import com.hcentive.xlsx.test.poi.objects.Premium;
import com.hcentive.xlsx.test.poi.objects.Rider;

/**
 * @author prashant
 *
 */
public class XlsxFileReaderStressTest {

	private XLSXBindingContext context = null;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		context =  new XLSXBindingContext(Area.class,Premium.class,Rider.class,FamilyDisc.class);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	public void stressTestWithMappedClassesWithoutHandler() {
		try {
			XLSXProcessor processor = new XLSXProcessor(context);
			processor.init("/home/prashant/SHS_Street_Premium_Output-CV_B_HEALTH_20130101_v2.xlsx");
			for(int i=0;i<=1000;i++){
				DefaultSheetHandler handler = (DefaultSheetHandler) processor.process(1);
				printMeta(handler.getWorbookData());
				processor.closeHandler(1);
			}
			
			processor.close();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	@Test
	public void stressTestWithMappedClassesWithHandler() {
		try {
			XLSXProcessor processor = new XLSXProcessor(context);
			processor.init("C:/Users/Amitsharma/Downloads/SC_Mof.xlsx");
			TestPremiumWorkbookRowHandler premiums = new TestPremiumWorkbookRowHandler();
	
			for(int i=0;i<=0;i++){
				processor.process(2,premiums);
				processor.closeHandler(2);
			}
			
			processor.close();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}		
	
	private void printMeta(Map<Integer, Object> handler)
	{
		System.out.println(handler.size());
		System.out.println(handler.get(2).getClass());
		if(handler.get(2) instanceof Premium){
			Premium premi = (Premium) handler.get(2) ;
			System.out.println(premi.getAge());
			System.out.println(premi.getPremium());
		}
		System.out.println("\n");
	}

}
