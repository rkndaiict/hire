package com.common.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.common.base.domain.LinkMetaData;


public interface LinkPersistRepository extends JpaRepository<LinkMetaData, Long> {

	public LinkMetaData findByTokenIdentifierAndExternalIdentifier(
			@Param("tokenIdentifier") String tokenIdentifier,@Param("externalIdentifier") String externalIdentifier);

	@Modifying
	@Query("update LinkMetaData es set es.isActive= false where es.externalIdentifier =:externalIdentifier and es.notificationEventType =:notificationEventType ")
	public void updateByExternalIdentifierAndNotificationEventType(
			@Param("externalIdentifier") String externalIdentifier,
			@Param("notificationEventType") String notificationEventType);
	
	public LinkMetaData findByTokenIdentifier(
			@Param("tokenIdentifier") String tokenIdentifier);
	
	@Query("select linkmetadata from LinkMetaData linkmetadata where linkmetadata.notificationEventType=:notificationEventType and linkmetadata.externalIdentifier=:externalIdentifier and linkmetadata.isActive=true")
	public LinkMetaData findActiveTokenByEventAndExternalIdentifier(
			@Param("notificationEventType") String notificationEventType, @Param("externalIdentifier") String externalIdentifier);
	
	

}