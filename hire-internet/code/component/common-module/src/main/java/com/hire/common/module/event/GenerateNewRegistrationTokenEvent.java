package com.hire.common.module.event;

import java.util.Map;

public class GenerateNewRegistrationTokenEvent extends AbstractEvent {
	
	private static final long serialVersionUID = 3345305873679834780L;

	public GenerateNewRegistrationTokenEvent(String eventId, Map<String, Object> reqParams) {
		super(eventId, reqParams);
	}

}
