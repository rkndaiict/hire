package com.service.usermanagement.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.base.domain.AbstractTrackedEntity;

@Entity
@Table(name = "USER_PASSWORD")
public class UserPassword extends AbstractTrackedEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "password")
	private String password;

	@OneToOne
	@JoinColumn(name = "user_id")
	private UserData userData;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserPassword [password=");
		builder.append(password);
		builder.append(", userData=");
		builder.append(userData);
		builder.append("]");
		return builder.toString();
	}

}
