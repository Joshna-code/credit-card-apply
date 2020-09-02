package com.example.service.impl;


import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.dto.CustomerDto;
import com.example.entity.CustomerDetails;
import com.example.exception.CustomerNotFoundException;
import com.example.repository.CustomerDetailsRepository;
import com.example.service.CreditCardService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * This is the service implementation class for credit card controller
 *
 */
@Slf4j
@Service
public class CreditCardServiceImpl implements CreditCardService{

	private final Logger logger = LoggerFactory.getLogger(CreditCardServiceImpl.class);
	@Autowired CustomerDetailsRepository customerRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JavaMailSender emailSender;
	
	private static final String FROM = "xyz@gmail.com";
	private static final String TO = "xyz@gmail.com";
	private static final String SUBJECT = "REG:CREDIT CARD APPLY";
	
	/* (non-Javadoc)
	 * method to add the customer details
	 */
	@Override
	public String addCustomer(CustomerDto customerDto) {
		CustomerDetails customerDetails = modelMapper.map(customerDto, CustomerDetails.class);
			// encrypt pan card
			encryptionOfPanCard(customerDetails);
			 CustomerDetails customer=  customerRepository.save(customerDetails);
			 logger.info("The customer details successfully saved");
			 // method to generate email
				generateEmail(FROM, TO, "REQUEST RECEIVED", SUBJECT);
				logger.info("Email is sent successfully");
				return String.valueOf(customer.getCustomerId());
	
	}


	/**
	 * @param customerDetails
	 */
	private void encryptionOfPanCard(CustomerDetails customerDetails) {
		Encoder encoder = Base64.getEncoder();
		String panCard = customerDetails.getPanCard();
		String encodedString = encoder.encodeToString(panCard.getBytes());
		customerDetails.setPanCard(encodedString);
	}



	/**
	 * @param from
	 * @param to
	 * @param text
	 * @param subject
	 */
	private void generateEmail(String from, String to, String text, String subject) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setText(text);
		message.setSubject(subject);
		emailSender.send(message);
	}


	/* (non-Javadoc)
	 * method to update the status of credit card request
	 */
	@Override
	public void updateStatus(String id, String status) {
		Optional<CustomerDetails> customerDetails = 	customerRepository.findById(Long.valueOf(id));
		
		if(customerDetails.isPresent()) {
			CustomerDetails	customerDetail = customerDetails.get();
		
		if(status.equalsIgnoreCase("REJECTED"))
		{
			logger.info("The request is rejected");
			customerDetail.setStatus(status);
			customerDetail.setMessage("missing fields");
			customerRepository.save(customerDetail);
			generateEmail(FROM, TO, "Please fill the missing fields", SUBJECT);
		}
		if(status.equalsIgnoreCase("ACCEPTED"))
		{
			logger.info("The request is accepted");
			customerDetail.setStatus(status);
			customerDetail.setMessage("need proof");
			customerRepository.save(customerDetail);
			generateEmail(FROM, TO, "Request to send proof", SUBJECT);
		}
		}
	}


	/**
	 *This method fetches the corresponding details of the customer
	 */
	@Override
	public CustomerDto findCustomerDetails(Long id) {
		Optional<CustomerDetails> customerDetails = customerRepository.findById(id);
		if(customerDetails.isPresent()) {
			CustomerDetails customer = customerDetails.get();
			CustomerDto customerDto = modelMapper.map(customer, CustomerDto.class);
			//decryption of pan card
			Base64.Decoder decoder = Base64.getDecoder();
			String decodedPanCard = new String(decoder.decode(customer.getPanCard()));
			customerDto.setPanCard(decodedPanCard);
			return customerDto;
		}
		else
			throw new CustomerNotFoundException(id);
		
		
	}




}
