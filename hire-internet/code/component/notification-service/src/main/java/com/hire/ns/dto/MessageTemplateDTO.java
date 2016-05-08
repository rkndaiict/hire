package com.hire.ns.dto;

public class MessageTemplateDTO {

	private Long messageTemplateId;
	private String event;
	private String emailContent;
	private Boolean sendable;
	private String template;
	private String subject;
	
	public Long getMessageTemplateId() {
		return messageTemplateId;
	}
	public void setMessageTemplateId(Long templateId) {
		this.messageTemplateId = templateId;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getEmailContent() {
		return emailContent;
	}
	public void setEmailContent(String emailContent) {
		this.emailContent = emailContent;
	}
	public Boolean getSendable() {
		return sendable;
	}
	public void setSendable(Boolean sendable) {
		this.sendable = sendable;
	}
	public String getTemplate() {
		return template;
	}
	public void setTemplate(String template) {
		this.template = template;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
}
