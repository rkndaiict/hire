package com.hire.ns.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.common.base.domain.LinkMetaData;
import com.hire.ns.dto.MapDTO;
import com.hire.ns.dto.NotificationDTO;
import com.hire.ns.service.api.AbstractEventHandler;
import com.hire.ns.service.api.EventStrategy;

@Service
public class ResendEmployeeRegistrationLinkEvent extends AbstractEventHandler implements
		EventStrategy {

	@Override
	public boolean appendTokenIdentifierToURLUpdateNotification(
			NotificationDTO notificationDTO) {
		if (CollectionUtils.isNotEmpty(notificationDTO.getParamList())) {
			for (MapDTO mapDTO : notificationDTO.getParamList()) {
				String tokenIdentifier = tokenGenerator.generateToken();
				generateTokenAndAppendToURL(mapDTO,REGISTRATION_LINK,tokenIdentifier);
				String externalIdentifier = (String) mapDTO.getMap().get(EXT_IDENTIFIER);
				
				// Expire Employer publish event token
				expireLinkMetaDataForExternalId(NOTIFICTAION_EVENT_TYPE.EP.getValue()
						, externalIdentifier);
				
				saveLinkMetaData(new LinkMetaData()
						, tokenIdentifier
						, true
						, SINGLE
						, NOTIFICTAION_EVENT_TYPE.EMPLOYEE_RESEND_REGISTRATION_LINK.getValue()
						, externalIdentifier);
			}
		}
		return true;
	}
}
