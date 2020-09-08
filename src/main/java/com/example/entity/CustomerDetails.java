package com.example.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * CustomerDetails Entity is the main entity. we will be using
 * for storing all the customer details
 */

@Entity
@Table(name = "CUSTOMER_DETAILS")
@Data
public class CustomerDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7579614388362775467L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "the customer id")
	@Column(name = "customer_id")
	private long customerId;

	@ApiModelProperty(notes = "mobileNumber")
	@Column(name = "mobile")
	private long mobileNumber;
	
	@ApiModelProperty(notes = "email id")
	@Column(name = "email_Id ")
	private String emailId;

	@ApiModelProperty(notes = "full name")
	@Column(name = "full_name ")
	private String fullName;
	
	@NotNull(message = "Name cannot be null")
	@ApiModelProperty(notes = "father name")
	@Column(name = "father_name ")
	private String fatherName;
	
	@ApiModelProperty(notes = "dateOfBirth")
	@Column(name = "date_of_birth ")
	private LocalDate dateOfBirth;
	
	@ApiModelProperty(notes = "marital status")
	@Column(name = "marital_status ")
	private String maritalStatus;
	
	@ApiModelProperty(notes = "citizenship")
	@Column(name = "citizenship ")
	private String citizenship;

	@ApiModelProperty(notes = "residentialStatus")
	@Column(name = "residential_status ")
	private String residentialStatus;

	@ApiModelProperty(notes = "panCard")
	@Column(name = "pan_card ")
	private String panCard;

	@ApiModelProperty(notes = "profession")
	@Column(name = "profession ")
	private String profession;
	
	@ApiModelProperty(notes = "companyName")
	@Column(name = "company_name ")
	private String companyName;
	
	@ApiModelProperty(notes = "designation")
	@Column(name = "designation ")
	private String designation;
	
	@ApiModelProperty(notes = "income")
	@Column(name = "gross_annual_income ")
	private long income;

	@ApiModelProperty(notes = "status of the request")
	@Column(name = "status")
	private String status;
	
	@ApiModelProperty(notes ="reason for approve/reject")
	@Column(name = "message")
	private String message;

	@OneToMany(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	private Set<AddressEntity> address; 
	
	public CustomerDetails() {
		
	}
	public String getStatus() {
		return status;
	}

	public Set<AddressEntity> getAddress() {
		return address;
	}
	public void setAddress(Set<AddressEntity> address) {
		this.address = address;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}


	public long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}

	public String getResidentialStatus() {
		return residentialStatus;
	}

	public void setResidentialStatus(String residentialStatus) {
		this.residentialStatus = residentialStatus;
	}

	public String getPanCard() {
		return panCard;
	}

	public void setPanCard(String panCard) {
		this.panCard = panCard;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public long getIncome() {
		return income;
	}

	public void setIncome(long income) {
		this.income = income;
	}



}
