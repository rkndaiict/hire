package com.hire.common.base.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.common.base.domain.LinkMetaData;
import com.common.base.repository.LinkPersistRepository;
import com.common.base.utils.DateUtil;
import com.hire.common.base.api.LinkMetadataService;

@Component
public class LinkMetadataServiceImpl implements LinkMetadataService {

	@Autowired
	private LinkPersistRepository linkPersistRepository;
	
	@Override
	public LinkMetaData getLinkMetadataByToken(String token) {
		return linkPersistRepository.findByTokenIdentifier(token);
	}
	
	@Override
	public LinkMetaData generateNewToken(String extId, String event) {
		
		LinkMetaData newLink = new LinkMetaData();
		newLink.setExternalIdentifier(extId);
		newLink.setActive(Boolean.TRUE);
		newLink.setExpirable(true);
		newLink.setNotificationEventType(event);
		newLink.setTokenExpires(DateUtil.addDaysToDate(new Date(), 30));
		newLink.setTokenGenerated(new Date());
		newLink.setTokenIdentifier(UUID.randomUUID().toString());
		newLink.setUsage("1");
		
		linkPersistRepository.save(newLink);
		
		return newLink;
	}
}
