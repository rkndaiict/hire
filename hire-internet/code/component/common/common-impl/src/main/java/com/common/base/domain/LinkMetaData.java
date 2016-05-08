package com.common.base.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LINK_META_DATA")
public class LinkMetaData implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "TOKEN_IDENTIFIER")
	private String tokenIdentifier;

	@Column(name = "TOKEN_GENERATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenGenerated;

	@Column(name = "TOKEN_EXPIRES")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenExpires;

	@Column(name = "IS_ACTIVE")
	private boolean isActive;

	@Column(name = "IS_EXPIRABLE")
	private boolean isExpirable;
	
	@Column(name = "NOTIFICTAION_EVENT_TYPE")
	private String notificationEventType;
	
	@Column(name = "USAGE")
	private String usage;
	
	@Column(name = "EXTERNAL_IDENTIFIER")
	private String externalIdentifier;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTokenIdentifier() {
		return tokenIdentifier;
	}

	public void setTokenIdentifier(String tokenIdentifier) {
		this.tokenIdentifier = tokenIdentifier;
	}

	public Date getTokenGenerated() {
		return tokenGenerated;
	}

	public void setTokenGenerated(Date tokenGenerated) {
		this.tokenGenerated = tokenGenerated;
	}

	public Date getTokenExpires() {
		return tokenExpires;
	}

	public void setTokenExpires(Date tokenExpires) {
		this.tokenExpires = tokenExpires;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isExpirable() {
		return isExpirable;
	}

	public void setExpirable(boolean isExpirable) {
		this.isExpirable = isExpirable;
	}

	public String getNotificationEventType() {
		return notificationEventType;
	}

	public void setNotificationEventType(String notificationEventType) {
		this.notificationEventType = notificationEventType;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getExternalIdentifier() {
		return externalIdentifier;
	}

	public void setExternalIdentifier(String externalIdentifier) {
		this.externalIdentifier = externalIdentifier;
	}
}
