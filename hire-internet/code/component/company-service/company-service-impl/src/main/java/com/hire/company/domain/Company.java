package com.hire.company.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.common.base.constants.StatusEnum;
import com.common.base.domain.AbstractTrackedEntity;

@Entity
@Table(name = "Company")
public class Company extends AbstractTrackedEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "User_ID")
	private String userID;

	@Column(name = "USER_ORGANIZATION_ID")
	private String userOrganizationId;

	@Column(name = "Company_Name")
	private String companyName;

	@Column(name = "LinkedIn_Profile_ID")
	private String linkedInProfileID;

	@Column(name = "Facebook_Profile_ID")
	private String facebookProfileID;

	@Column(name = "Twitter_Profile_ID")
	private String twitterProfileID;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private StatusEnum status;

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

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

}
