package com.hire.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author h.v
 *
 */
public class UserDTO {

	private String userName;
	private String userIdentifier;
	private String password;
	private String status;
	private String userProfile;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserIdentifier() {
		return userIdentifier;
	}
	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}
	public String getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userIdentifier=");
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
