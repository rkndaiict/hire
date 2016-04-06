package com.service.usermanagement.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.base.domain.AbstractTrackedEntity;

@Entity
@Table(name = "USER_ORGANIZATION")
public class UserOrganization extends AbstractTrackedEntity {

	private static final long serialVersionUID = 1L;
	
	@OneToOne
	@JoinColumn(name = "master_Organization_ID")
	private MasterOrganization masterOrganization;

	public MasterOrganization getMasterOrganization() {
		return masterOrganization;
	}

	public void setMasterOrganization(MasterOrganization masterOrganization) {
		this.masterOrganization = masterOrganization;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserOrganization [masterOrganization=");
		builder.append(masterOrganization);
		builder.append("]");
		return builder.toString();
	}

}
