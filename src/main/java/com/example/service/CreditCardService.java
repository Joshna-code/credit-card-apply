package com.example.service;

import com.example.dto.CustomerDto;

public interface CreditCardService {

	String addCustomer(CustomerDto customerDto);

	void updateStatus(String id, String status);

	CustomerDto findCustomerDetails(Long id);

}
