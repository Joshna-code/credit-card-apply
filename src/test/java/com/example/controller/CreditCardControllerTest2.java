package com.example.controller;


import static org.mockito.Mockito.when;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.entity.CustomerDetails;
import com.example.repository.CustomerDetailsRepository;
import com.example.service.CreditCardService;
import com.example.controller.CreditCardController;
import com.example.dto.CustomerDto;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardControllerTest2 {


	@Spy
	ModelMapper modelMapper;
	
	@InjectMocks
	CreditCardController creditCardController;
	
	@Mock 
	CreditCardService creditCardService;
	
	@Mock
	RestTemplate restTemplate;
	
	@Mock
	CustomerDetailsRepository customerRepository;
	

	@Test
	public void testSaveCustomerDetails() {
		
		RestTemplate restTemplate = new RestTemplate();
	
		CustomerDto customerDto = new CustomerDto();
		customerDto.setFatherName("Aldrin");
		customerDto.setPanCard("ABCDE1234A");
		when(creditCardService.addCustomer(customerDto)).thenReturn("1");
		String requestId = "1";
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> requestBody = new HttpEntity<>(requestId, headers);
		ResponseEntity<String> result = restTemplate.postForEntity("http://localhost:8081/api/v1/customer", requestBody, String.class);
		assertEquals("AWAITING APPROVAL", result.getBody());
	}
	
	@Test
	public void testSaveCustomerStatus_APPROVED() {
		
		creditCardService.updateStatus(anyString(), anyString());
		customerRepository.findById(any());
		 ResponseEntity<?> res = creditCardController.saveCustomerStatus("APPROVED", "1");
		CustomerDetails customer = new CustomerDetails();
		customer.setEmailId("abc@gmail.com");
		assertNotNull(res);
		
	}
	
	@Test
	public void testSaveCustomerStatus_REJECTED() {
		
		creditCardService.updateStatus(anyString(), anyString());
		customerRepository.findById(any());
		 ResponseEntity<?> res = creditCardController.saveCustomerStatus("REJECTED", "1");
		CustomerDetails customer = new CustomerDetails();
		customer.setEmailId("abc@gmail.com");
		assertNotNull(res);
	}

	@Test
	public void testfindCustomerDetails() {
		
		CustomerDto customerDto = new CustomerDto();
		customerDto.setFatherName("Aldrin");
		customerDto.setPanCard("ABCDE1234A"); 
		when(creditCardService.findCustomerDetails(any())).thenReturn(customerDto);
		creditCardController.findCustomerDetails(1l);
		assertEquals("ABCDE1234A", customerDto.getPanCard());
	}

}
