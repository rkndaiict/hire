package com.hire.ems.listener;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.hire.common.module.constant.NotifyEvent;
import com.hire.common.module.event.OnRegistrationCompleteEvent;
import com.hire.dto.UserDTO;
//import com.hire.ns.dto.NotificationDTO;

@Component
public class OnRegistrationCompleteEventListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	private static Logger logger = LoggerFactory.getLogger(OnRegistrationCompleteEventListener.class);
	
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		Map<String, Object> map = (HashMap<String, Object>)event.getEventContext();
		logger.info("Listening to RegistrationCompleteEvent.");
		if(NotifyEvent.SEEKER_REGISTRATION.equals(event.getEventId())) {
			
		}
		else if (NotifyEvent.AGENCY_REGISTRATION.equals(event.getEventId())) {
			
		}
	}
	
	private void prepareNotification(UserDTO userDTO) {
//		NotificationDTO notification = new NotificationDTO();
//		notification.setEmailSubject("Thanks For Registering at");
//		notification.setEvent(NotifyEvent.SEEKER_REGISTRATION);
//		notification.setEmailRecipients(userDTO.getEmail());
//		notification.setCcList(null);
//		notification.setEmailBody(null);
	}

}
