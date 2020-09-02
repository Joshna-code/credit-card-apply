package com.example.dto;

import lombok.Data;

/**
 * This is the addressdto class
 *
 */
@Data
public class AddressDto {

	private Long addressId;
	private String residenceAddress;
	private String officeAddress;
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
