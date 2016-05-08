package com.hire.ns.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.common.base.domain.LinkMetaData;
import com.hire.ns.dto.MapDTO;
import com.hire.ns.dto.NotificationDTO;
import com.hire.ns.service.api.AbstractEventHandler;
import com.hire.ns.service.api.EventStrategy;

@Service
public class ResetPasswordEvent extends AbstractEventHandler implements
		EventStrategy {

	@Override
	public boolean appendTokenIdentifierToURLUpdateNotification(
			NotificationDTO notificationDTO) {
		if (CollectionUtils.isNotEmpty(notificationDTO.getParamList())) {
			for (MapDTO mapDTO : notificationDTO.getParamList()) {
				String tokenIdentifier = tokenGenerator.generateToken();
				generateTokenAndAppendToURL(mapDTO,RESET_PASSWORD_LINK,tokenIdentifier);
				String externalIdentifier=(String) mapDTO.getMap().get(EXT_IDENTIFIER);
				saveLinkMetaData(new LinkMetaData(), tokenIdentifier, false,
						SINGLE,
						NOTIFICTAION_EVENT_TYPE.RESET_PASSWORD.getValue(),externalIdentifier);
			}
		}
		return true;
	}
}
