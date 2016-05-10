package com.hire.ems.listener;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;

import com.hire.common.module.constant.NotifyEvent;
import com.hire.common.module.event.OnRegistrationCompleteEvent;

public class RegistrationEventListener implements ApplicationListener<OnRegistrationCompleteEvent> {

	private static Logger logger = LoggerFactory.getLogger(RegistrationEventListener.class);
	
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		Map<String, Object> map = (HashMap<String, Object>)event.getEventContext();
		logger.info("Listening to RegistrationCompleteEvent.");
		if(NotifyEvent.SEEKER_REGISTRATION.equals(event.getEventId())) {
			
		}
		else if (NotifyEvent.AGENCY_REGISTRATION.equals(event.getEventId())) {
			
		}
	}

}
