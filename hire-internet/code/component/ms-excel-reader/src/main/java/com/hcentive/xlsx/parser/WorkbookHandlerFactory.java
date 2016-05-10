/**
 * 
 */
package com.hcentive.xlsx.parser;

import java.util.HashMap;
import java.util.Map;

import com.hcentive.xlsx.annotations.processor.AnnotationProcessor;
import com.hcentive.xlsx.annotations.processor.WBMappingInfo;
import com.hcentive.xlsx.errorhandler.DefaultXLSXErrorHandler;
import com.hcentive.xlsx.errorhandler.XLSXErrorHandler;

/**
 * @author prashant
 * 
 */
public class WorkbookHandlerFactory {

	private AnnotationProcessor annotationProcessor = new AnnotationProcessor();
	private Map<Integer, WBMappingInfo> mappingInfo = new HashMap<Integer, WBMappingInfo>();
	private XLSXErrorHandler errorHandler = null;
	private Map<Integer, WorkbookHandler> wbHandlers = null;

	private WorkbookHandlerFactory() {
	}

	public static WorkbookHandlerFactory create(Class<?>[] mappings) throws Exception
	{

		WorkbookHandlerFactory factory = create();
		// process annotations in Parameterized type

		for (Class<?> clazz : mappings) {
			WBMappingInfo mapping = factory.annotationProcessor.process(clazz);
			for (int sheetIndex : mapping.getSheetIndexes()) {
				factory.mappingInfo.put(sheetIndex, mapping);
			}
		}

		return factory;
	}

	public static WorkbookHandlerFactory create() throws Exception
	{
		WorkbookHandlerFactory factory = new WorkbookHandlerFactory();
		factory.errorHandler = new DefaultXLSXErrorHandler();
		factory.wbHandlers = new HashMap<Integer, WorkbookHandler>();
		return factory;
	}

	public WorkbookHandler createWorkbookHandler(Integer sheetIndex, int mappingSheetIndex, SheetHandler rowHandler)
			throws Exception {
		WorkbookHandler wbHandler = createInstance();

		wbHandler.setSheetIndex(sheetIndex);
		if (rowHandler == null)
		{
			rowHandler = new DefaultSheetHandler(); // Use default sheet handler
		}
		wbHandler.setWbRowHandler(rowHandler);

		WBMappingInfo info = this.mappingInfo.get(mappingSheetIndex);
		if (info != null) {
			wbHandler.setMappingInfo(info);
		}

		wbHandler.setErrorHandler(errorHandler);

		wbHandlers.put(sheetIndex, wbHandler);
		return wbHandler;
	}

	public WorkbookHandler createWorkbookHandler(Integer sheetIndex, int mappingSheetIndex) throws Exception {
		return createWorkbookHandler(sheetIndex, mappingSheetIndex, null);
	}

	private WorkbookHandler createInstance() throws Exception
	{
		return new WorkbookHandler();
	}

	// public static void main(String[] aa)
	// {
	// try {
	// System.out.println("Pass 1");
	// WorkbookHandler arHandler = new WorkbookHandler();
	// System.out.println("Pass 2");
	// // WorkbookHandler handler = new WorkbookHandler();
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public XLSXErrorHandler getErrorHandler() {
		return errorHandler;
	}

	public void setErrorHandler(XLSXErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
		for (WorkbookHandler wbHandler : wbHandlers.values()) {
			wbHandler.setErrorHandler(errorHandler);
		}

	}

	public WorkbookHandler getWorkbookHandler(Integer sheetIndex)
	{
		return wbHandlers.get(sheetIndex);
	}

	public void setWorkbookRowHandler(Integer sheetIndex, SheetHandler handler)
	{
		WorkbookHandler wbHandler = this.getWorkbookHandler(sheetIndex);
		if (wbHandler != null) {
			wbHandler.setWbRowHandler(handler);
		}
	}

	public SheetHandler getWorkbookRowHandler(Integer sheetIndex)
	{
		WorkbookHandler handler = this.getWorkbookHandler(sheetIndex);
		if (handler != null) {
			return handler.getWbRowHandler();
		}
		return null;
	}

	public void closeHandler(Integer sheetIndex) {
		wbHandlers.remove(sheetIndex);
	}

	public void destroy() {
		annotationProcessor = null;
		mappingInfo = null;
		wbHandlers = null;
		errorHandler = null;
	}

}
