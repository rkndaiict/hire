/**
 * 
 */
package com.hcentive.xlsx.test;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

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
public class XlsxFileReaderTest {
	
	private XLSXBindingContext mappingContext = null;
	
	private XLSXBindingContext emptyContext = null;

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
		mappingContext =  new XLSXBindingContext(Area.class,Premium.class,Rider.class,FamilyDisc.class);
		emptyContext = new XLSXBindingContext();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	

	public void testWithMappedClassesWithoutHandler() {
		try {
			
			XLSXProcessor processor = new XLSXProcessor(mappingContext);
			processor.init("/home/prashant/SHS_Street_Premium_Output-CV_B_HEALTH_20130101_v2.xlsx");
			DefaultSheetHandler handler = (DefaultSheetHandler) processor.process(1);
			printMeta(handler.getWorbookData());
			processor.closeHandler(1);
			
			processor.close();
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	

	public void testWithMappedClassesWithHandler() {
		try {
			XLSXProcessor processor = new XLSXProcessor(mappingContext);
			processor.init("/home/prashant/SHS_Street_Premium_Output-CV_B_HEALTH_20130101_v2.xlsx");

			TestPremiumWorkbookRowHandler premiums = new TestPremiumWorkbookRowHandler();
			processor.process(1,premiums);
			processor.closeHandler(1);
			processor.close();
			System.out.println("Rows processed: "+premiums.getCount());
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
	
	

	public void testWithoutMappedClassesWithHandler() {
		try {
			XLSXProcessor processor = new XLSXProcessor(emptyContext);
			processor.init("/home/prashant/SHS_Street_Premium_Output-CV_B_HEALTH_20130101_v2.xlsx");
			TestPremiumWorkbookRowHandler premiums = new TestPremiumWorkbookRowHandler();
			processor.process(1,premiums);
			processor.closeHandler(1);
			processor.close();
			System.out.println("Rows processed: "+premiums.getCount());
			
		} catch (Exception e) {

			e.printStackTrace();
		}
	}	
	

	public void testWithoutMappedClassesWithoutHandler() {
		try {
			XLSXProcessor processor = new XLSXProcessor(emptyContext);
			processor.init("/home/prashant/SHS_Street_Premium_Output-CV_B_HEALTH_20130101_v2.xlsx");
			
			DefaultSheetHandler handler = (DefaultSheetHandler) processor.process(1);
			printMeta(handler.getWorbookData());
			
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
