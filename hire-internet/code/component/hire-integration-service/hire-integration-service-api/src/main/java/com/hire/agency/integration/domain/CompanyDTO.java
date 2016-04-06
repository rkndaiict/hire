package com.hire.agency.integration.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Company")
@XmlType
public class CompanyDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "userID")
	private String userID;

	@XmlElement(name = "userOrganizationId")
	private String userOrganizationId;

	@XmlElement(name = "companyName")
	private String companyName;

	@XmlElement(name = "linkedInProfileID")
	private String linkedInProfileID;

	@XmlElement(name = "facebookProfileID")
	private String facebookProfileID;

	@XmlElement(name = "twitterProfileID")
	private String twitterProfileID;

	@XmlElement(name = "status")
	private String status;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserOrganizationId() {
		return userOrganizationId;
	}

	public void setUserOrganizationId(String userOrganizationId) {
		this.userOrganizationId = userOrganizationId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLinkedInProfileID() {
		return linkedInProfileID;
	}

	public void setLinkedInProfileID(String linkedInProfileID) {
		this.linkedInProfileID = linkedInProfileID;
	}

	public String getFacebookProfileID() {
		return facebookProfileID;
	}

	public void setFacebookProfileID(String facebookProfileID) {
		this.facebookProfileID = facebookProfileID;
	}

	public String getTwitterProfileID() {
		return twitterProfileID;
	}

	public void setTwitterProfileID(String twitterProfileID) {
		this.twitterProfileID = twitterProfileID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompanyDTO [userID=");
		builder.append(userID);
		builder.append(", userOrganizationId=");
		builder.append(userOrganizationId);
		builder.append(", companyName=");
		builder.append(companyName);
		builder.append(", linkedInProfileID=");
		builder.append(linkedInProfileID);
		builder.append(", facebookProfileID=");
		builder.append(facebookProfileID);
		builder.append(", twitterProfileID=");
		builder.append(twitterProfileID);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
