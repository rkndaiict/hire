package com.hire.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author h.v
 *
 */
public class UserDTO {

	private String ssoId;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String status;
	private Set<UserProfileDTO> userProfiles = new HashSet<UserProfileDTO>();
	
	public String getSsoId() {
		return ssoId;
	}
	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Set<UserProfileDTO> getUserProfiles() {
		return userProfiles;
	}
	public void setUserProfiles(Set<UserProfileDTO> userProfiles) {
		this.userProfiles = userProfiles;
	}
	
	
}
