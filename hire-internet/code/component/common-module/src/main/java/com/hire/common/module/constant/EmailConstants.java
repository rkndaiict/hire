package com.hire.common.module.constant;

/**
 * The Interface EmailConstants.
 */
public final class EmailConstants {

    public static final String MAIL_TO_ADDRESS = "mailToAddress";

    public static final String MAIL_FROM_ADDRESS = "mailFromAddress";

    public static final String MAIL_CC_ADDRESS = "mailCCAddress";

    public static final String MAIL_BCC_ADDRESS = "mailBCCAddress";

    public static final String MAIL_SUBJECT = "mailSubject";

    public static final String MAIL_BODY = "mailBody";

    public static final String CUSTOM_BODY_MESSAGE = "customBodyMessage";

	public static final String PORTAL_URL = "portalUrl";

    public static enum NOTIFICTAION_EVENT_TYPE {       
        EP("Employer Publish"),
    	FILE_PROCESSING_FAILUER("File Processing Failure");	
        
        private String value;

        NOTIFICTAION_EVENT_TYPE(String value) {

            this.value = value;
        }

        public String getValue() {

            return value;
        };
    }
}
