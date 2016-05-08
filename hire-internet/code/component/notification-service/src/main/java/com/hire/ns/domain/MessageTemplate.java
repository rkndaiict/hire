package com.hire.ns.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.Where;


/**
 * The persistent class for the NS_MESSAGETEMPLATE database table.
 */
@Entity
@Table(name = "MESSAGE_TEMPLATE")
@Where(clause="deleted=0")
public class MessageTemplate implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private long id;
	
	@Column(name = "EVENT")
	private String event;

	@Column(name = "SENDABLE")
	private boolean sendable;
	
	@Column(name="IS_DEFAULT")
	private boolean isDefault;
	
	@Lob
	@Column(name = "TEMPLATE")
	private byte[] template;
	
	@Column(name = "RECIPIENT_TYPE")
	@Enumerated(EnumType.STRING)
	private EmailRecipientType recipientType;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public boolean isSendable() {
		return sendable;
	}

	public void setSendable(boolean sendable) {
		this.sendable = sendable;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public byte[] getTemplate() {
		return template;
	}

	public void setTemplate(byte[] template) {
		this.template = template;
	}

	public EmailRecipientType getRecipientType() {
		return recipientType;
	}

	public void setRecipientType(EmailRecipientType recipientType) {
		this.recipientType = recipientType;
	}

}