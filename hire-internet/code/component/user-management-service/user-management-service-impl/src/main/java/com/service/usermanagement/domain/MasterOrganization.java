package com.service.usermanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.common.base.domain.AbstractTrackedEntity;

@Entity
@Table(name = "MASTER_ORGANIZATION")
public class MasterOrganization extends AbstractTrackedEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "ORGANIZATION_NAME")
	private String organizationName;

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MasterOrganization [organizationName=");
		builder.append(organizationName);
		builder.append("]");
		return builder.toString();
	}
}
