package com.hire.ns.domain;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the EMAIL database table.
 */
@Entity
@Table(name = "EMAIL")
public class Email implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	private Long id;

	@Column(name = "BCC_EMAIL")
	private String bccEmail;

	@Lob
	@Column(name = "BODY")
	private byte[] body;

	@Column(name = "CC_EMAIL")
	private String ccEmail;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_RECEIVED")
	private Date nsDateReceived = Calendar.getInstance().getTime();

	@Column(name = "FROM_EMAIL")
	private String fromEmail;

	@Column(name = "FROM_NAME")
	private String fromName;

	@Column(name = "SUBJECT")
	private String subject;

	@Column(name = "TO_EMAIL")
	private String toEmail;
	
	@ManyToOne
	@JoinColumn(name = "MESSAGE_TEMPLATE_ID")
	private MessageTemplate messageTemplate;
	
	@Column(name = "SENT")
	private boolean sent;
	
	@Column(name = "ATTEMPTS")
	private int attempts;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBccEmail() {
		return bccEmail;
	}

	public void setBccEmail(String bccEmail) {
		this.bccEmail = bccEmail;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}

	public String getCcEmail() {
		return ccEmail;
	}

	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}

	public Date getNsDateReceived() {
		return nsDateReceived;
	}

	public void setNsDateReceived(Date nsDateReceived) {
		this.nsDateReceived = nsDateReceived;
	}

	public String getFromEmail() {
		return fromEmail;
	}

	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public MessageTemplate getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(MessageTemplate messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	public boolean isSent() {
		return sent;
	}

	public void setSent(boolean sent) {
		this.sent = sent;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

}