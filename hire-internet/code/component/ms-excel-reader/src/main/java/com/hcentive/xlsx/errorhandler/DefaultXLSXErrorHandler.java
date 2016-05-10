/**
 * 
 */
package com.hcentive.xlsx.errorhandler;

/**
 * @author prashant
 *
 */
public class DefaultXLSXErrorHandler implements XLSXErrorHandler {


	/* (non-Javadoc)
	 * @see com.hcentive.xlsx.errorhandler.XLSXErrorHandler#handleError(com.hcentive.xlsx.errorhandler.XLSXErrorCode, java.lang.Throwable)
	 */
	@Override
	public void handleError(XLSXErrorCode errorCode, Throwable ex) {
		
		if(ex!=null)
			ex.printStackTrace();
		//System.out.println(errorCode);

	}

}
