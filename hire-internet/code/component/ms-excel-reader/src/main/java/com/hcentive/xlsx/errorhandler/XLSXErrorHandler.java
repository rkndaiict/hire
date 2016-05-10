/**
 * 
 */
package com.hcentive.xlsx.errorhandler;

/**
 * @author prashant
 *
 */
public interface XLSXErrorHandler {

	public void handleError(XLSXErrorCode errorCode, Throwable ex);
}
