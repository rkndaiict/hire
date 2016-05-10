package com.hire.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hire.validator.custom.api.PasswordMatches;
import com.hire.validator.custom.api.ValidEmail;

/**
 * 
 * @author h.v
 *
 */
@PasswordMatches
public class UserDTO {

	@NotNull
	@Size(min = 1)
	private String userName;
	
	@ValidEmail
	@NotNull
	@Size(min = 1)
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
    @Size(min = 1)
    private String matchingPassword;
	
	private String status;
	private String userProfile;
	private String userIdentifier;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMatchingPassword() {
		return matchingPassword;
	}
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	
	public String getUserIdentifier() {
		return userIdentifier;
	}
	public void setUserIdentifier(String userIdentifier) {
		this.userIdentifier = userIdentifier;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userIdentifier=");
		builder.append(email);
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
