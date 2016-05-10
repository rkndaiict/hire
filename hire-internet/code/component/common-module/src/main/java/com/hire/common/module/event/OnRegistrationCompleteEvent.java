package com.hire.common.module.event;

import java.util.Map;

public class OnRegistrationCompleteEvent extends AbstractEvent {

	private static final long serialVersionUID = -6147896571909248429L;

	public OnRegistrationCompleteEvent(String eventId, Map<String, Object> reqParams) {
		super(eventId, reqParams);
	}

}
