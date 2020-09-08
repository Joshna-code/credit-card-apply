package com.example.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.dto.CustomerDto;
import com.example.service.CreditCardService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This is the controller class for the customer
 * to apply credit card
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class CreditCardController {

	private CreditCardService creditCardService;
	
	private final Logger logger = LoggerFactory.getLogger(CreditCardController.class);
	@Autowired
	RestTemplate restTemplate;
	
	 @Autowired
	    public CreditCardController(CreditCardService creditCardService){
	        this.creditCardService = creditCardService;
	    }
	/**
	 * 
	 * @param customerDto
	 * @return
	 */
	@ApiOperation(value = "credit card apply by customer", notes = "This endpoint submits request to raise credit card by customer")
	@PostMapping("/applyCreditCard")
	public ResponseEntity<String> saveCustomerDetails(
			@ApiParam(value = "customer details", required= true) @RequestBody CustomerDto customerDto){
		
		String requestId = creditCardService.addCustomer(customerDto);
		
		logger.info("The request Id is {}", requestId);
		HttpEntity<String> requestBody = new HttpEntity<>(requestId);
         restTemplate.postForEntity("http://localhost:8081/api/v1/customer", requestBody, String.class);

		return ResponseEntity.ok("Request submitted, your request id is :" + requestId);
	}
	

	/**
	 * 
	 * @param status
	 * @param id
	 * @return
	 * This endpoint is used to update the status for the raised request i.e either Rejected or accepted
	 */
	@ApiOperation(value = "saves customer status", notes = "This endpoint saves the customer details")
	@PutMapping("/saveCustomerStatus/{id}")
	public ResponseEntity<String> saveCustomerStatus(
			@RequestBody String status,
			@PathVariable(value = "id", required = true) String id)
	{
		creditCardService.updateStatus(id,status);
		logger.info("Status updated is {}", status);
		return ResponseEntity.ok().build();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * this endpoint finds the customer details
	 */
	 @ApiOperation(value = "get customer details", notes = "this endpoint finds the customer details")
	 @GetMapping("/findCustomerDetails/{id}")
	 public CustomerDto findCustomerDetails(
			@PathVariable (value = "id", required = true) Long id)
	 {
		return creditCardService.findCustomerDetails(id);
	 }
	 
}
