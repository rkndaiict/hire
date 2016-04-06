package com.hire.agency.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.common.base.domain.AbstractTrackedEntity;

@Entity
@Table(name = "Agency")
public class Agency extends AbstractTrackedEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "agency_Name")
	private String agencyName;

	@Column(name = "contact_Number")
	private String contactNumber;

	@Column(name = "user_ID")
	private String userID;

	@Column(name = "agency_Identifier")
	private String agencyIdentifier;

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getAgencyIdentifier() {
		return agencyIdentifier;
	}

	public void setAgencyIdentifier(String agencyIdentifier) {
		this.agencyIdentifier = agencyIdentifier;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Agency [agencyName=");
		builder.append(agencyName);
		builder.append(", contactNumber=");
		builder.append(contactNumber);
		builder.append(", userID=");
		builder.append(userID);
		builder.append(", agencyIdentifier=");
		builder.append(agencyIdentifier);
		builder.append("]");
		return builder.toString();
	}
}
