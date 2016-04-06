package com.hire.agency.integration.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "JobSeeker")
@XmlType
public class JobSeekerDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "userID")
	private String userID;

	@XmlElement(name = "userOrganizationId")
	private String userOrganizationId;

	@XmlElement(name = "jobSeekerIdentifier")
	private String jobSeekerIdentifier;

	@XmlElement(name = "facebookAccountID")
	private String facebookAccountID;

	@XmlElement(name = "linkedInAccountID")
	private String linkedInAccountID;

	@XmlElement(name = "twitterAccountID")
	private String twitterAccountID;

	@XmlElement(name = "name")
	private String name;

	@XmlElement(name = "mobileNumber")
	private String mobileNumber;

	@XmlElement(name = "alternateMobileNumber")
	private String alternateMobileNumber;

	@XmlElement(name = "emailID")
	private String emailID;

	@XmlElement(name = "alternateEmailID")
	private String alternateEmailID;

	@XmlElement(name = "jobProfileData")
	private JobProfileDataDTO jobProfileData;

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

	public String getJobSeekerIdentifier() {
		return jobSeekerIdentifier;
	}

	public void setJobSeekerIdentifier(String jobSeekerIdentifier) {
		this.jobSeekerIdentifier = jobSeekerIdentifier;
	}

	public String getFacebookAccountID() {
		return facebookAccountID;
	}

	public void setFacebookAccountID(String facebookAccountID) {
		this.facebookAccountID = facebookAccountID;
	}

	public String getLinkedInAccountID() {
		return linkedInAccountID;
	}

	public void setLinkedInAccountID(String linkedInAccountID) {
		this.linkedInAccountID = linkedInAccountID;
	}

	public String getTwitterAccountID() {
		return twitterAccountID;
	}

	public void setTwitterAccountID(String twitterAccountID) {
		this.twitterAccountID = twitterAccountID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getAlternateMobileNumber() {
		return alternateMobileNumber;
	}

	public void setAlternateMobileNumber(String alternateMobileNumber) {
		this.alternateMobileNumber = alternateMobileNumber;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}

	public String getAlternateEmailID() {
		return alternateEmailID;
	}

	public void setAlternateEmailID(String alternateEmailID) {
		this.alternateEmailID = alternateEmailID;
	}

	public JobProfileDataDTO getJobProfileData() {
		return jobProfileData;
	}

	public void setJobProfileData(JobProfileDataDTO jobProfileData) {
		this.jobProfileData = jobProfileData;
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
		builder.append("JobSeekerDTO [userID=");
		builder.append(userID);
		builder.append(", userOrganizationId=");
		builder.append(userOrganizationId);
		builder.append(", jobSeekerIdentifier=");
		builder.append(jobSeekerIdentifier);
		builder.append(", facebookAccountID=");
		builder.append(facebookAccountID);
		builder.append(", linkedInAccountID=");
		builder.append(linkedInAccountID);
		builder.append(", twitterAccountID=");
		builder.append(twitterAccountID);
		builder.append(", name=");
		builder.append(name);
		builder.append(", mobileNumber=");
		builder.append(mobileNumber);
		builder.append(", alternateMobileNumber=");
		builder.append(alternateMobileNumber);
		builder.append(", emailID=");
		builder.append(emailID);
		builder.append(", alternateEmailID=");
		builder.append(alternateEmailID);
		builder.append(", jobProfileData=");
		builder.append(jobProfileData);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}

}
