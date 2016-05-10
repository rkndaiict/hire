package com.service.usermanagement.domain;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.common.base.domain.AbstractTrackedEntity;

@Entity
@Table(name = "USER_DATA")
public class UserData extends AbstractTrackedEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "USER_IDENTIFIER")
	private String userIdentifier;

	@Column(name = "USER_NAME")
	private String userName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	private Status status;
	
	@Enumerated(EnumType.STRING)
	@Column(name="USER_PROFILE_TYPE")
	private UserProfileType userProfile;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@JoinTable(name = "User_Organization_List", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = { @JoinColumn(name = "USER_ORGANIZATION_ID") })
	private List<UserOrganization> userOrganizations;

	@OneToOne(mappedBy = "userData", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private UserPassword userPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<UserOrganization> getUserOrganizations() {
		return userOrganizations;
	}

	public void setUserOrganizations(List<UserOrganization> userOrganizations) {
		this.userOrganizations = userOrganizations;
	}

	public String getUserIdentifier() {
		return userIdentifier;
	}

	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}

	public UserPassword getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(UserPassword userPassword) {
		this.userPassword = userPassword;
	}

	@PrePersist
	public void setUserIdentifier() {
		this.setUserIdentifier(UUID.randomUUID().toString());
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public UserProfileType getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfileType userProfile) {
		this.userProfile = userProfile;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserData [userIdentifier=");
		builder.append(userIdentifier);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", status=");
		builder.append(status);
		builder.append(", userProfile=");
		builder.append(userProfile);
		builder.append("]");
		return builder.toString();
	}

}
