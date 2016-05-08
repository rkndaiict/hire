package com.hire.ns.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.base.domain.LinkMetaData;
import com.common.base.repository.LinkPersistRepository;
import com.hire.ns.dto.NotificationDTO;
import com.hire.ns.service.api.EventStrategy;
import com.hire.ns.service.api.LinkTokenService;
import com.hire.ns.util.EventFactory;

@Service
public class LinkExpiryServiceImpl  implements
		LinkTokenService {

	@Autowired
	private EventFactory eventFactory;
	
	@Autowired
	private LinkPersistRepository linkPersistRepository;

	@Override
	@Transactional(readOnly = false)
	public void generateLinkAndPersist(NotificationDTO notificationDTO) {
		
		EventStrategy eventStrategy = eventFactory.getEventType(notificationDTO);
		if (eventStrategy == null) {
			return;
		}
		if (!eventStrategy.appendTokenIdentifierToURLUpdateNotification(notificationDTO)) {
			return;
		}
	}

	@Override
	@Transactional
	public void deactivateToken(String tokenIdentifier,String brokerExternalIdentifier) 
	{
		LinkMetaData linkMetaData = null;
		if (tokenIdentifier != null) {
			linkMetaData = linkPersistRepository
					.findByTokenIdentifierAndExternalIdentifier(tokenIdentifier,brokerExternalIdentifier);
		}
		if (linkMetaData != null) {
			linkMetaData.setActive(false);
			linkPersistRepository.saveAndFlush(linkMetaData);
		}
		
	}
	
	@Override
	@Transactional
	public void deactivateToken(String tokenIdentifier) 
	{
		LinkMetaData linkMetaData = null;
		if (tokenIdentifier != null) {
			linkMetaData = linkPersistRepository
					.findByTokenIdentifier(tokenIdentifier);
		}
		if (linkMetaData != null) {
			linkMetaData.setActive(false);
			linkPersistRepository.saveAndFlush(linkMetaData);
		}
		
	}
	
	@Override
	@Transactional
	public void updateByExternalIdentifierAndNotificationEventType(String externalIdentifier,
			String notificationEventType) {
		linkPersistRepository.updateByExternalIdentifierAndNotificationEventType(externalIdentifier, notificationEventType);
	}
	
}
