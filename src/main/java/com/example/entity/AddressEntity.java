package com.example.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * AddressEntity is used to store the address of each customer
 * It contains both office address and residence address
 */
@Entity
@Table(name = "ADDRESS")
@Data
public class AddressEntity implements Serializable {

	
	private static final long serialVersionUID = -2728333885883931615L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Address_Id")
	private long addressId;
	
	@ApiModelProperty(notes = "residenceAddress")
	@Column(name = "Residence_Address")
	private String residenceAddress;
	
	@ApiModelProperty(notes = "officeAddress")
	@Column(name ="Office_Address")
	private String officeAddress;

	public AddressEntity() {

	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	
	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

}
