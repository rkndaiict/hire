package com.hire.agency.integration.domain;

import java.io.Serializable;
import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "JobProfileData")
@XmlType
public class JobProfileDataDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "isLookingForChange")
	private boolean isLookingForChange;

	@XmlElement(name = "resume")
	private Byte[] resume;

	public boolean isLookingForChange() {
		return isLookingForChange;
	}

	public void setLookingForChange(boolean isLookingForChange) {
		this.isLookingForChange = isLookingForChange;
	}

	public Byte[] getResume() {
		return resume;
	}

	public void setResume(Byte[] resume) {
		this.resume = resume;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobProfileDataDTO [isLookingForChange=");
		builder.append(isLookingForChange);
		builder.append(", resume=");
		builder.append(Arrays.toString(resume));
		builder.append("]");
		return builder.toString();
	}

}
