package com.hire.ems.listener;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.hire.common.module.constant.NotifyEvent;
import com.hire.common.module.event.GenerateNewRegistrationTokenEvent;

@Component
public class GenerateNewRegistrationTokenEventListener implements ApplicationListener<GenerateNewRegistrationTokenEvent> {

	private static Logger logger = LoggerFactory.getLogger(GenerateNewRegistrationTokenEventListener.class);
	
	@Override
	public void onApplicationEvent(GenerateNewRegistrationTokenEvent event) {
		Map<String, Object> map = (HashMap<String, Object>)event.getEventContext();
		logger.info("Listening to RegistrationCompleteEvent.");
		if(NotifyEvent.SEEKER_REGISTRATION.equals(event.getEventId())) {
			
		}
		else if (NotifyEvent.AGENCY_REGISTRATION.equals(event.getEventId())) {
			
		}
	}
}
