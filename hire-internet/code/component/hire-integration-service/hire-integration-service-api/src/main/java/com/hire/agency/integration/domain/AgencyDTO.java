package com.hire.agency.integration.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Agency")
@XmlType
public class AgencyDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "agencyName")
	private String agencyName;

	@XmlElement(name = "contactNumber")
	private String contactNumber;

	@XmlElement(name = "userID")
	private String userID;

	@XmlElement(name = "agencyIdentifier")
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
		builder.append("AgencyDTO [agencyName=");
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
