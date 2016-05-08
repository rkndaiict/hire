package com.hire.ns.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class NotificationDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String extRefId;
	private String name;
	private List<MapDTO> paramList = new ArrayList<MapDTO>();
	private String ccList;
	private String bccList;
	private String emailBody;
	private String emailSubject;
	private String emailRecipients;
	private String userEmail;
	private String event;
	private String recipientType;
	
	public String getExtRefId() {
		return extRefId;
	}
	public void setExtRefId(String extRefId) {
		this.extRefId = extRefId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<MapDTO> getParamList() {
		return paramList;
	}
	public void setParamList(List<MapDTO> paramList) {
		this.paramList = paramList;
	}
	public String getCcList() {
		return ccList;
	}
	public void setCcList(String ccList) {
		this.ccList = ccList;
	}
	public String getBccList() {
		return bccList;
	}
	public void setBccList(String bccList) {
		this.bccList = bccList;
	}
	public String getEmailBody() {
		return emailBody;
	}
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getEmailRecipients() {
		return emailRecipients;
	}
	public void setEmailRecipients(String emailRecipients) {
		this.emailRecipients = emailRecipients;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getRecipientType() {
		return recipientType;
	}
	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}
	
}
