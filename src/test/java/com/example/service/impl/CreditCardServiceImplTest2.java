package com.example.service.impl;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;

import static org.mockito.Mockito.anyLong;
import org.modelmapper.ModelMapper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.dto.CustomerDto;
import com.example.entity.CustomerDetails;
import com.example.exception.CustomerNotFoundException;
import com.example.repository.CustomerDetailsRepository;


@RunWith(MockitoJUnitRunner.class)
public class CreditCardServiceImplTest2 {

	@Spy
	ModelMapper modelMapper;
	
	@InjectMocks
	CreditCardServiceImpl creditCardServiceImpl;
	
	@Mock
	CustomerDetailsRepository customerRepository;
	
	@Mock
	JavaMailSender emailSender;
	
	@Before
	public void setUp() {
		modelMapper.getConfiguration().setAmbiguityIgnored(true);
	}
	
	@Test
	public void testAddCustomer() {
		
		CustomerDto customerDto = new CustomerDto();
		customerDto.setPanCard("ABCDE1234A");
		CustomerDetails customerEntity = getCustomerDetails();
		when(modelMapper.map(customerDto, CustomerDetails.class)).thenReturn(customerEntity);
		when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("dummies007@gmail.com");
		message.setTo("dummies007@gmail.com");
		message.setText("request received");
		message.setSubject("reg: credit card apply");
		emailSender.send(message);
		String.valueOf(customerEntity.getCustomerId());
		String requestId =  creditCardServiceImpl.addCustomer(customerDto);
		assertEquals("1", requestId);
	}

	/**
	 * @return
	 */
	private CustomerDetails getCustomerDetails() {
		CustomerDetails customerEntity = new CustomerDetails();
		customerEntity.setFatherName("Aldrin");
		customerEntity.setPanCard("ABCDE1234A");
		customerEntity.setCustomerId(1L);
		return customerEntity;
	}
	
	@Test
	public void testUpdateStatus_ACCEPTED() {
		CustomerDetails customerEntity = getCustomerDetails();
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customerEntity));
		creditCardServiceImpl.updateStatus("1", "ACCEPTED");
		assertEquals("ACCEPTED", customerEntity.getStatus());
	}
	
	@Test
	public void testUpdateStatus_REJECTED() {
		CustomerDetails customerEntity = getCustomerDetails();
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customerEntity));
		creditCardServiceImpl.updateStatus("1", "REJECTED");
		assertEquals("REJECTED", customerEntity.getStatus());
	}

	@Test
	public void testFindCustomerDetails() {
		CustomerDetails customerEntity = getCustomerDetails();
		when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customerEntity));
		CustomerDto customerDto = new CustomerDto();
		customerDto.setFatherName("Aldrin");
		customerDto.setPanCard("ABCDE1234A"); 
		when(modelMapper.map(customerEntity, CustomerDto.class)).thenReturn(customerDto);
		creditCardServiceImpl.findCustomerDetails(1l);
		assertNotNull(customerDto);
		
	}
	
	@Test(expected = CustomerNotFoundException.class)
	public void testFindCustomerDetails_CustomerNotFoundException() {
		when(customerRepository.findById(anyLong())).thenReturn(Optional.empty());
		creditCardServiceImpl.findCustomerDetails(1l);
		
	}
	
}
