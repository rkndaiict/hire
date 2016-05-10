package com.hire.ns.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hire.common.module.constant.EmailConstants;
import com.hire.ns.domain.Email;
import com.hire.ns.domain.EmailRecipientType;
import com.hire.ns.domain.MessageTemplate;
import com.hire.ns.dto.MapDTO;
import com.hire.ns.dto.NotificationDTO;
import com.hire.ns.repository.MessageTemplateRepositoryCustom;
import com.hire.ns.service.api.EmailService;
import com.hire.ns.service.api.TemplateStrategyService;
import com.hire.ns.startup.SendMailTLS;

/**
 * The Class TemplateStrategyServiceImpl.
 */
@Service
public class TemplateStrategyServiceImpl implements TemplateStrategyService {

    private static final Logger logger = LoggerFactory.getLogger(TemplateStrategyServiceImpl.class);

    @Autowired
    private MessageTemplateRepositoryCustom messageTemplateRepositoryCustom;
    
    @Autowired
    private EmailService emailService;

    @Override
    public void sendEmailWithDatabaseTemplate(NotificationDTO notificationDTO) throws Exception {

	String body;
	String customText = "";
	String subject = null;
	MessageTemplate messageTemplate = null;
	Map<String, Object> params = new HashMap<String, Object>();

	EmailRecipientType recipient = EmailRecipientType.valueOf(notificationDTO.getRecipientType());
	messageTemplate = messageTemplateRepositoryCustom.findByEventAndRecipientType(notificationDTO.getEvent(), recipient);
	
	if(messageTemplate== null){
		logger.warn("Template is either not available or deleted for given event type: " + notificationDTO.getEvent());
		return;
	}

	body = new String(messageTemplate.getTemplate(), "UTF-8");
	subject = body.substring(8, body.indexOf("\n"));
	body = body.substring(body.indexOf("\n"));
	
	if (StringUtils.isNotEmpty(notificationDTO.getEmailBody())) {
	    customText = "<br/>"+notificationDTO.getEmailBody()+"<br/>";
	}

		for (MapDTO dto : notificationDTO.getParamList()) {
	
		    params = dto.getMap();
		    
		    // Convert date object to String with date format as MM/dd/yyyy
		    for(Entry<String,Object> entry  : params.entrySet()) {
		    	if(entry.getValue() instanceof Date) {
		    		Date paramDate = (Date)entry.getValue();
		    		String paramDateString = new SimpleDateFormat("MM/dd/yyyy").format(paramDate);
		    		entry.setValue(paramDateString);
		    	}
		    }
		   
		    params.put(EmailConstants.CUSTOM_BODY_MESSAGE, customText);
		    params.put(EmailConstants.MAIL_TO_ADDRESS, notificationDTO.getEmailRecipients());
	    	params.put(EmailConstants.MAIL_SUBJECT, subject);
		    params.put(EmailConstants.MAIL_BODY, body);
		    notificationDTO.setEmailBody(body);
		    addEntryAndSendMail(notificationDTO, messageTemplate,params);
		}
    }
    
    private void addEntryAndSendMail(NotificationDTO notificationDTO,
    	    MessageTemplate messageTemplate, Map<String, Object> params) {
        	
        params.put(EmailConstants.MAIL_FROM_ADDRESS, notificationDTO.getUserEmail());
    	
    	if (StringUtils.isNotBlank(notificationDTO.getCcList())) {
    	    params.put(EmailConstants.MAIL_CC_ADDRESS, notificationDTO.getCcList());
    	}
    	if (StringUtils.isNotBlank(notificationDTO.getBccList())) {
    		params.put(EmailConstants.MAIL_BCC_ADDRESS, notificationDTO.getBccList());
    	}

    	Email email = emailService.saveUpdateEmailNotification(notificationDTO, params, null, messageTemplate);
    	
    	try {
			SendMailTLS.sendEmail(email, notificationDTO.getEmailBody());
		} catch (AddressException e) {
			
			e.printStackTrace();
		} catch (MessagingException e) {
			
			e.printStackTrace();
		}
   }

}
