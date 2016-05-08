package com.hire.ns.exception;

/**
 * The Class MailServiceException.
 */
public class MailServiceException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Instantiates a new mail service exception.
     */
    public MailServiceException() {
        super();
    }

    /**
     * Instantiates a new mail service exception.
     * 
     * @param message the message
     */
    public MailServiceException(String message) {
        super(message);
    }

    /**
     * Instantiates a new mail service exception.
     * 
     * @param message the message
     * @param t the t
     */
    public MailServiceException(String message, Throwable t) {
        super(message, t);
    }
}
