package com.hire.ns.util;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.xmlgraphics.util.MimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.hire.ns.domain.Email;
import com.hire.ns.exception.MailServiceException;

/**
 * The Class EmailUtil.
 */
@Component
public final class EmailUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailUtil.class);

    @Autowired
    private JavaMailSender mailSender;
    
    private String[] getAddressArray(String addressList) {
    	final String[] addresses;
        if(addressList != null) {
        	addresses = addressList.split(",");
        } else {
        	addresses = null;
        }
        return addresses;
    }
    
    /**
     * void sendEmail(String, Map).
     * 
     * @param nsEmail the email
     * @param list the attachment
     * @return the boolean
     * @throws MailServiceException the mail service exception
     */
    public String sendEmail(final Email nsEmail)
            throws MailServiceException {
        final String addressTo = nsEmail.getToEmail();
        final String addressFrom = nsEmail.getFromEmail();
        final String subject = nsEmail.getSubject();
        final String ccList = nsEmail.getCcEmail();
        final String bccList = nsEmail.getBccEmail();
        final String body = nsEmail.getBody() == null? null : StringEscapeUtils.unescapeHtml(new String(nsEmail.getBody()));
        final String messageId = Long.toString(nsEmail.getId());
        
        final String[] ccAddresses = getAddressArray(ccList);
        final String[] bccAddresses = getAddressArray(bccList);
        
        try {
            MimeMessagePreparator preparator = new MimeMessagePreparator() {

                @Override
                public void prepare(MimeMessage mimeMessage) throws Exception {
                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                    message.setTo(addressTo);
                    message.setFrom(addressFrom);
                    message.setSubject(subject);
                    if (ccAddresses != null) {
                        message.setCc(ccAddresses);
                    }
                    if (bccAddresses != null) {
                        message.setBcc(bccAddresses);
                    }
                    
                    message.setText(body,true);
                    
				}
			};
            mailSender.send(preparator);
            logger.debug("Mail Send Sucessfully to:" + addressTo);
            return body;
        } catch (MailPreparationException e) {
            logger.error("Could not send email to: " + addressTo + ", from address: " + addressFrom + ", subject "
                    + subject, e);
            throw new MailServiceException("MAIL_NOT_SENT:" + e.getMessage(), e);
        } catch (MailAuthenticationException e) {
            logger.error("Could not send email to: " + addressTo + ", from address: " + addressFrom + ", subject "
                    + subject, e);
            throw new MailServiceException("MAIL_NOT_SENT:" + e.getMessage(), e);
        } catch (MailSendException e) {
            logger.error("Could not send email to: " + addressTo + ", from address: " + addressFrom + ", subject "
                    + subject, e);
            throw new MailServiceException("MAIL_NOT_SENT:" + e.getMessage(), e);
        } catch (MailException e) {
            logger.error("Could not send email to: " + addressTo + ", from address: " + addressFrom + ", subject "
                    + subject, e);
            throw new MailServiceException("MAIL_NOT_SENT:" + e.getMessage(), e);
        }
    }

    /**
     * Sets the mail sender.
     * 
     * @param mailSender the new mail sender
     */
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    
    private String getMimeType(String fileExtension){
    	if(fileExtension.toLowerCase().contains("pdf")){
    		return MimeConstants.MIME_PDF;
    	}else if(fileExtension.toLowerCase().contains("doc")){
    		return MimeConstants.MIME_RTF;
    	}else if(fileExtension.toLowerCase().contains("png")){
    		return MimeConstants.MIME_PNG;
    	}else if(fileExtension.toLowerCase().contains("jpeg")){
    		return MimeConstants.MIME_JPEG;
    	}else{
    		return MimeConstants.MIME_PDF;
    	}
    }

}
