package com.service.usermanagement.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.base.domain.AbstractTrackedEntity;
import com.common.base.domain.Address;

@Entity
@Table(name = "PERSON_ADDRESS")
public class PersonAddress extends AbstractTrackedEntity {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "PERSON_DETAIL_ID")
	private PersonDetail personDetail;

	@OneToOne
	@JoinColumn(name = "PRIMARY_ADDRESS_ID")
	private Address primaryAddress;

	@OneToOne
	@JoinColumn(name = "SECONDARY_ADDRESS_ID")
	private Address secondaryAddress;

	public PersonDetail getPersonDetail() {
		return personDetail;
	}

	public void serPersonDetail(PersonDetail userData) {
		this.personDetail = userData;
	}

	public Address getPrimaryAddress() {
		return primaryAddress;
	}

	public void setPrimaryAddress(Address primaryAddress) {
		this.primaryAddress = primaryAddress;
	}

	public Address getSecondaryAddress() {
		return secondaryAddress;
	}

	public void setSecondaryAddress(Address secondaryAddress) {
		this.secondaryAddress = secondaryAddress;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonAddress [");
		builder.append("primaryAddress=");
		builder.append(primaryAddress);
		builder.append(", secondaryAddress=");
		builder.append(secondaryAddress);
		builder.append("]");
		return builder.toString();
	}

}
