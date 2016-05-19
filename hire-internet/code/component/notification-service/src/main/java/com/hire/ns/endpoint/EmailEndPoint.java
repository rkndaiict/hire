package com.hire.ns.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hire.ns.dto.NotificationDTO;
import com.hire.ns.service.api.LinkTokenService;
import com.hire.ns.service.api.TemplateStrategyService;

/**
 * 
 * @author harshul.varshney
 *
 */
@Component
public class EmailEndPoint {
	
	private static final Logger logger = LoggerFactory.getLogger(EmailEndPoint.class);
	
	@Autowired
	private TemplateStrategyService templateStrategyService;
	
	@Autowired
	private LinkTokenService linkTokenService;
	
	/**
	 * Sends email with the information provided in Notification DTO.
	 * Token generation service will be called, which will in tern generate and 
	 * save tokens for registration links.
	 * @param notificationDTO
	 */
	public void sendEmail(NotificationDTO notificationDTO) {
		
		
		try {
			logger.debug("Received the request to send e-mail");
			linkTokenService.generateLinkAndPersist(notificationDTO);
			templateStrategyService.sendEmailWithDatabaseTemplate(notificationDTO);
		}
		catch(Exception e) {
			logger.error("Failed to send email : "+e.getMessage());
		}
	}

}
