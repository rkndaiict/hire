/**
 * 
 */
package com.hcentive.xlsx.parser;

/**
 * @author prashant
 *
 */
@SuppressWarnings("rawtypes")
public class XLSXBindingContext {
	
	private Class[] classes = null;
	
	// workbook handler factory
	private WorkbookHandlerFactory wbFactory = null;	
		

	/**
	 * Initialise context with classes to be mapped with Workbook sheets
	 * @param classesToParse
	 * @throws Exception 
	 */
	public XLSXBindingContext(Class... classesToParse) throws Exception{
		this.classes = classesToParse;
		if(classes == null || classes.length <=0)
			throw new IllegalArgumentException("Invalid argument to initialize the context.");
		
		wbFactory = WorkbookHandlerFactory.create(classes);
	}
	
	public XLSXBindingContext() throws Exception{
		// to initialise empty context
		wbFactory = WorkbookHandlerFactory.create();
	}


	/**
	 * @return the classes
	 */
	public Class[] getClasses() {
		return classes;
	}

	/**
	 * @return the wbFactory
	 */
	public WorkbookHandlerFactory getWbFactory() {
		return wbFactory;
	}

	/**
	 * 
	 * @see com.hcentive.xlsx.parser.WorkbookHandlerFactory#destroy()
	 */
	public void destroy() {
		wbFactory.destroy();
	}	
	
	
	
	
}
