package com.hire.company.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PostPersist;
import javax.persistence.Table;

import com.common.base.constants.StatusEnum;
import com.common.base.domain.AbstractTrackedEntity;

@Entity
@Table(name = "JobSeeker_Company_Mapping")
public class JobSeekerCompanyMapping extends AbstractTrackedEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "Job_Seeker_Identifier")
	private String jobSeekerIdentifier;

	@Column(name = "Company_Identifier")
	private String companyIdentifier;

	// ACTIVE, STALE
	@Enumerated(EnumType.STRING)
	@Column(name = "Status")
	private StatusEnum status;

	@Column(name = "identifier")
	private String identifier;

	public String getJobSeekerIdentifier() {
		return jobSeekerIdentifier;
	}

	public void setJobSeekerIdentifier(String jobSeekerIdentifier) {
		this.jobSeekerIdentifier = jobSeekerIdentifier;
	}

	public String getCompanyIdentifier() {
		return companyIdentifier;
	}

	public void setCompanyIdentifier(String companyIdentifier) {
		this.companyIdentifier = companyIdentifier;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@PostPersist
	public void postPersistIdentifer() {
		this.setIdentifier(UUID.randomUUID().toString());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("JobSeekerCompanyMapping [jobSeekerIdentifier=");
		builder.append(jobSeekerIdentifier);
		builder.append(", companyIdentifier=");
		builder.append(companyIdentifier);
		builder.append(", status=");
		builder.append(status);
		builder.append(", identifier=");
		builder.append(identifier);
		builder.append("]");
		return builder.toString();
	}

}
