package com.service.usermanagement.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.base.domain.AbstractTrackedEntity;
import com.common.base.domain.Address;

@Entity
@Table(name = "USER_ADDRESS")
public class UserAddress extends AbstractTrackedEntity {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "USER_ID")
	private UserData userData;

	@OneToOne
	@JoinColumn(name = "PRIMARY_ADDRESS_ID")
	private Address primaryAddress;

	@OneToOne
	@JoinColumn(name = "SECONDARY_ADDRESS_ID")
	private Address secondaryAddress;

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
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
		builder.append("UserAddress [userData=");
		builder.append(userData);
		builder.append(", primaryAddress=");
		builder.append(primaryAddress);
		builder.append(", secondaryAddress=");
		builder.append(secondaryAddress);
		builder.append("]");
		return builder.toString();
	}

}
