package com.hire.jobseeker.domain;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.common.base.constants.StatusEnum;
import com.common.base.domain.AbstractTrackedEntity;

@Entity
@Table(name = "JOB_SEEKER")
public class JobSeeker extends AbstractTrackedEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "USER_ID")
	private String userID;

	@Column(name = "USER_ORGANIZATION_ID")
	private String userOrganizationId;

	@Column(name = "jobSeeker_Identifier")
	private String jobSeekerIdentifier;

	@Column(name = "facebook_Account_ID")
	private String facebookAccountID;

	@Column(name = "linkedIn_Account_ID")
	private String linkedInAccountID;

	@Column(name = "twitter_Account_ID")
	private String twitterAccountID;

	@Column(name = "name")
	private String name;

	@Column(name = "mobile_Number")
	private String mobileNumber;

	@Column(name = "alternate_Mobile_Number")
	private String alternateMobileNumber;

	@Column(name = "email_ID")
	private String emailID;

	@Column(name = "alternate_Email_ID")
	private String alternateEmailID;

	@OneToOne(mappedBy = "jobSeeker", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private JobProfileData jobProfileData;

	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private StatusEnum status;

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

	public String getJobSeekerIdentifier() {
		return jobSeekerIdentifier;
	}

	public void setJobSeekerIdentifier(String jobSeekerIdentifier) {
		this.jobSeekerIdentifier = jobSeekerIdentifier;
	}

	@PrePersist
	public void setJobSeekerIdentifer() {
		this.setJobSeekerIdentifier(UUID.randomUUID().toString());
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

	public JobProfileData getJobProfileData() {
		return jobProfileData;
	}

	public void setJobProfileData(JobProfileData jobProfileData) {
		this.jobProfileData = jobProfileData;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobSeeker [userID=");
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
