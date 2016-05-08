package com.hire.ns.service.api;

import java.util.Date;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.util.UriComponentsBuilder;

import com.common.base.domain.LinkMetaData;
import com.common.base.repository.LinkPersistRepository;
import com.hire.ns.domain.EmailRecipientType;
import com.hire.ns.dto.MapDTO;
import com.hire.ns.service.api.EventStrategy.NOTIFICTAION_EVENT_TYPE;
import com.hire.ns.util.TokenGenerator;

public abstract class AbstractEventHandler {

	public static final String LINK = "LINK";

	public static final String portalLink = "portallink";

	public static final String BROKER_PORTAL_REG_URL = "brokerRegUrl";

	public static final String RESET_PASSWORD_LINK = "resetPasswordLink";

	public static final String SINGLE = "SINGLE";

	public static final String MULTIPLE = "MULTIPLE";

	public static final String CARRIER_REGISTRATION_URL = "carrierAdminRegistrationUrl";

	public static final String REGISTRATION_LINK = "registrationlink";
	
	public static final String EXT_IDENTIFIER = "externalIdentifier";
	
	public static final String IND_EMAIL = "individualEmail";
	
	
	
	public static final String REGISTRATION_URL = "registerUrl"; 

	@Autowired
	protected LinkPersistRepository linkPersistRepository;

	@Autowired
	protected TokenGenerator tokenGenerator;

	protected void saveLinkMetaData(LinkMetaData linkMetaData,
			String tokenIdentifier, boolean isExpirable, String usage,
			String notificationEventType, String externalIdentifier) {
		linkPersistRepository.updateByExternalIdentifierAndNotificationEventType(externalIdentifier,notificationEventType);
		linkMetaData.setNotificationEventType(notificationEventType);
		linkMetaData.setExpirable(isExpirable);
		linkMetaData.setUsage(usage);
		linkMetaData.setTokenGenerated(new Date());
		linkMetaData.setTokenIdentifier(tokenIdentifier);
		linkMetaData.setExternalIdentifier(externalIdentifier);
		linkMetaData.setActive(true);
		DateTime dateTime = new DateTime();
		dateTime.plusDays(30);
		linkMetaData.setTokenExpires(dateTime.toDate());
		linkPersistRepository.saveAndFlush(linkMetaData);
	}
	
	protected void saveLinkMetadataForBroker(LinkMetaData linkMetaData,
			String tokenIdentifier, boolean isExpirable, String usage,
			String notificationEventType, String externalIdentifier) {
		updateLinkMetaData(notificationEventType,externalIdentifier,EmailRecipientType.COMMON);	
		linkMetaData.setNotificationEventType(notificationEventType);
		linkMetaData.setExpirable(isExpirable);
		linkMetaData.setUsage(usage);
		linkMetaData.setTokenGenerated(new Date());
		linkMetaData.setTokenIdentifier(tokenIdentifier);
		linkMetaData.setExternalIdentifier(externalIdentifier);
		linkMetaData.setActive(true);
		DateTime dateTime = new DateTime();
		dateTime.plusDays(30);//TODO : make this configurable
		linkMetaData.setTokenExpires(dateTime.toDate());
		linkPersistRepository.saveAndFlush(linkMetaData);
	}
	protected void saveLinkMetadataForIndividual(LinkMetaData linkMetaData,
			String tokenIdentifier, boolean isExpirable, String usage,
			String notificationEventType, String externalIdentifier) {
		updateLinkMetaData(notificationEventType, externalIdentifier, EmailRecipientType.COMMON);
		linkMetaData.setNotificationEventType(notificationEventType);
		linkMetaData.setExpirable(isExpirable);
		linkMetaData.setUsage(usage);
		linkMetaData.setTokenGenerated(new Date());
		linkMetaData.setTokenIdentifier(tokenIdentifier);
		linkMetaData.setExternalIdentifier(externalIdentifier);
		linkMetaData.setActive(true);
		DateTime dateTime = new DateTime();
		dateTime.plusDays(30);//TODO : make this configurable
		linkMetaData.setTokenExpires(dateTime.toDate());
		linkPersistRepository.saveAndFlush(linkMetaData);
	}
	
	protected void saveLinkMetadataForEmployer(LinkMetaData linkMetaData,
			String tokenIdentifier, boolean isExpirable, String usage,
			String notificationEventType, String externalIdentifier) {
		updateLinkMetaData(notificationEventType, externalIdentifier, EmailRecipientType.COMMON);
		linkMetaData.setNotificationEventType(notificationEventType);
		linkMetaData.setExpirable(isExpirable);
		linkMetaData.setUsage(usage);
		linkMetaData.setTokenGenerated(new Date());
		linkMetaData.setTokenIdentifier(tokenIdentifier);
		linkMetaData.setExternalIdentifier(externalIdentifier);
		linkMetaData.setActive(true);
		DateTime dateTime = new DateTime();
		dateTime.plusDays(30);//TODO : make this configurable
		linkMetaData.setTokenExpires(dateTime.toDate());
		linkPersistRepository.saveAndFlush(linkMetaData);
	}
	
	protected void saveLinkMetadataForAgency(LinkMetaData linkMetaData,
			String tokenIdentifier, boolean isExpirable, String usage,
			String notificationEventType, String externalIdentifier) {
		updateLinkMetaData(notificationEventType,externalIdentifier,EmailRecipientType.COMMON);	
		linkMetaData.setNotificationEventType(notificationEventType);
		linkMetaData.setExpirable(isExpirable);
		linkMetaData.setUsage(usage);
		linkMetaData.setTokenGenerated(new Date());
		linkMetaData.setTokenIdentifier(tokenIdentifier);
		linkMetaData.setExternalIdentifier(externalIdentifier);
		linkMetaData.setActive(true);
		DateTime dateTime = new DateTime();
		dateTime.plusDays(30);//TODO : make this configurable
		linkMetaData.setTokenExpires(dateTime.toDate());
		linkPersistRepository.saveAndFlush(linkMetaData);
	}
	
	private void updateLinkMetaData(String notificationEventType, String externalIdentifier, EmailRecipientType recipientType) {
		LinkMetaData dbLinkMetaData = null;
		if(recipientType==EmailRecipientType.COMMON) {
				dbLinkMetaData = linkPersistRepository.findActiveTokenByEventAndExternalIdentifier(NOTIFICTAION_EVENT_TYPE.AGENCY_ADDED_BROKER.toString(), externalIdentifier);
				if(dbLinkMetaData!=null){
					linkPersistRepository.updateByExternalIdentifierAndNotificationEventType(externalIdentifier,NOTIFICTAION_EVENT_TYPE.AGENCY_ADDED_BROKER.toString());
				}
				else {
					linkPersistRepository.updateByExternalIdentifierAndNotificationEventType(externalIdentifier,notificationEventType);
				}
		}
		else if(recipientType==EmailRecipientType.COMMON) {
			dbLinkMetaData = linkPersistRepository.findActiveTokenByEventAndExternalIdentifier(NOTIFICTAION_EVENT_TYPE.CLIENT_ADD.toString(), externalIdentifier);
			if(dbLinkMetaData!=null){
				linkPersistRepository.updateByExternalIdentifierAndNotificationEventType(externalIdentifier,NOTIFICTAION_EVENT_TYPE.CLIENT_ADD.toString());
			}
			else {
				linkPersistRepository.updateByExternalIdentifierAndNotificationEventType(externalIdentifier,notificationEventType);
			}
		}
		else if(recipientType==EmailRecipientType.COMMON) {
			dbLinkMetaData = linkPersistRepository.findActiveTokenByEventAndExternalIdentifier(NOTIFICTAION_EVENT_TYPE.AGENCY_ADDED_BROKER.toString(), externalIdentifier);
			if(dbLinkMetaData!=null){
				linkPersistRepository.updateByExternalIdentifierAndNotificationEventType(externalIdentifier,NOTIFICTAION_EVENT_TYPE.AGENCY_ADDED_BROKER.toString());
			}
			else {
				linkPersistRepository.updateByExternalIdentifierAndNotificationEventType(externalIdentifier,notificationEventType);
			}
		}
	}

	protected void generateTokenAndAppendToURL(MapDTO mapDTO, String tobeChangedURL, String tokenIdentifier) {
		String link = (String) mapDTO.getMap().get(tobeChangedURL);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(link);
		builder.queryParam("tokenIdentifier", tokenIdentifier);
		String url = builder.build().toUriString();
		mapDTO.getMap().put(tobeChangedURL, url);
	}
	
	protected void expireLinkMetaDataForExternalId(String notificationEventType, String externalIdentifier) {
		linkPersistRepository.updateByExternalIdentifierAndNotificationEventType(externalIdentifier,notificationEventType);
		
	}
}
