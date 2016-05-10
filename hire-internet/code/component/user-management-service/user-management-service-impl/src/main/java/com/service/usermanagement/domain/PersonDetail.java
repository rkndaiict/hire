package com.service.usermanagement.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.common.base.domain.AbstractTrackedEntity;

@Entity
@Table(name = "PERSON_DETAIL")
public class PersonDetail extends AbstractTrackedEntity {

	private static final long serialVersionUID = -4492442608201594719L;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "salutation")
	private Salutation salutation;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	private String middleName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;

	@Enumerated(EnumType.STRING)
	@Column(name = "GENDER")
	private Gender gender;
	
	@Column(name = "is_disabled")
	private Boolean isDisabled;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "MARITAL_STATUS")
	private MaritalStatus maritalStatus;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "WORK_PHONE")
	private String workPhone;
	
	@Column(name = "CELL_PHONE")
	private String cellPhone;

	@Column(name = "PREFERRED_COMMUNICATION_MODE")
	private PreferredCommunicationMode preferredCommunicationMode;
	
	@OneToOne(mappedBy = "personDetail", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private PersonAddress personAddress;

	public Salutation getSalutation() {
		return salutation;
	}

	public void setSalutation(Salutation salutation) {
		this.salutation = salutation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Boolean getIsDisabled() {
		return isDisabled;
	}

	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public PreferredCommunicationMode getPreferredCommunicationMode() {
		return preferredCommunicationMode;
	}

	public void setPreferredCommunicationMode(PreferredCommunicationMode preferredCommunicationMode) {
		this.preferredCommunicationMode = preferredCommunicationMode;
	}
	
	
}
