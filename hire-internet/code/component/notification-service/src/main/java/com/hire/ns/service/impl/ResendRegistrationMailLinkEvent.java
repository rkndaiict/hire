package com.hire.ns.service.impl;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.common.base.domain.LinkMetaData;
import com.hire.ns.domain.EmailRecipientType;
import com.hire.ns.dto.MapDTO;
import com.hire.ns.dto.NotificationDTO;
import com.hire.ns.service.api.AbstractEventHandler;
import com.hire.ns.service.api.EventStrategy;

@Service
public class ResendRegistrationMailLinkEvent extends AbstractEventHandler implements 
		EventStrategy {

	@Override
	public boolean appendTokenIdentifierToURLUpdateNotification(
			NotificationDTO notificationDTO) {
		if(notificationDTO.getRecipientType()==EmailRecipientType.COMMON.name()) {
		if (CollectionUtils.isNotEmpty(notificationDTO.getParamList())) {
			for (MapDTO mapDTO : notificationDTO.getParamList()) {
				String tokenIdentifier = tokenGenerator.generateToken();
				generateTokenAndAppendToURL(mapDTO,BROKER_PORTAL_REG_URL,tokenIdentifier);
				String externalIdentifier=(String) mapDTO.getMap().get(EXT_IDENTIFIER);
				saveLinkMetadataForBroker(new LinkMetaData(), tokenIdentifier, true,
						SINGLE,
						NOTIFICTAION_EVENT_TYPE.RESEND_REGISTRATION_LINK
								.getValue(),externalIdentifier);
			}
		  }
		}	else if(notificationDTO.getRecipientType()==EmailRecipientType.COMMON.name()) {
			if (CollectionUtils.isNotEmpty(notificationDTO.getParamList())) {
				for (MapDTO mapDTO : notificationDTO.getParamList()) {
					String tokenIdentifier = tokenGenerator.generateToken();
					generateTokenAndAppendToURL(mapDTO,BROKER_PORTAL_REG_URL,tokenIdentifier);
					String externalIdentifier=(String) mapDTO.getMap().get(EXT_IDENTIFIER);
					saveLinkMetadataForAgency(new LinkMetaData(), tokenIdentifier, true,
							SINGLE,
							NOTIFICTAION_EVENT_TYPE.RESEND_REGISTRATION_LINK
									.getValue(),externalIdentifier);
				}
			  }
			}
		else if(notificationDTO.getRecipientType()==EmailRecipientType.COMMON.name()) {
			if (CollectionUtils.isNotEmpty(notificationDTO.getParamList())) {
				for (MapDTO mapDTO : notificationDTO.getParamList()) {
					String tokenIdentifier = tokenGenerator.generateToken();
					generateTokenAndAppendToURL(mapDTO,portalLink,tokenIdentifier);
					String externalIdentifier=(String) mapDTO.getMap().get(EXT_IDENTIFIER);
					saveLinkMetadataForIndividual(new LinkMetaData(), tokenIdentifier, true,
							SINGLE,
							NOTIFICTAION_EVENT_TYPE.RESEND_REGISTRATION_LINK
									.getValue(),externalIdentifier);
				}
			  }
			}else if(notificationDTO.getRecipientType()==EmailRecipientType.COMMON.name()) {
				if (CollectionUtils.isNotEmpty(notificationDTO.getParamList())) {
					for (MapDTO mapDTO : notificationDTO.getParamList()) {
						String tokenIdentifier = tokenGenerator.generateToken();
						generateTokenAndAppendToURL(mapDTO,portalLink,tokenIdentifier);
						String externalIdentifier=(String) mapDTO.getMap().get(EXT_IDENTIFIER);
						saveLinkMetadataForEmployer(new LinkMetaData(), tokenIdentifier, true,
								SINGLE,
								NOTIFICTAION_EVENT_TYPE.RESEND_REGISTRATION_LINK
										.getValue(),externalIdentifier);
					}
				  }
				}
		return true;
	}
}
