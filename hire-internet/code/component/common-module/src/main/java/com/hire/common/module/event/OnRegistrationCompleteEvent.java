package com.hire.common.module.event;

import java.util.Map;

public class OnRegistrationCompleteEvent extends AbstractEvent {

	public OnRegistrationCompleteEvent(String eventId, Map<String, Object> reqParams) {
		super(eventId, reqParams);
	}

}
