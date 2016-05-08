package com.hire.ns.service.api;

import com.hire.ns.dto.NotificationDTO;

public interface LinkTokenService {

	
	void generateLinkAndPersist(NotificationDTO notificationDTO);
	
	void deactivateToken(String tokenIdentifier, String brokerExternalIdentifier);
	
	void deactivateToken(String tokenIdentifier);
	
	void updateByExternalIdentifierAndNotificationEventType(String externalIdentifier,
			String notificationEventType);
}
