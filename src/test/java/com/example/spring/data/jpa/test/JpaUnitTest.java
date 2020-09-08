package com.example.spring.data.jpa.test;



import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.CreditCardApplyApplication;
import com.example.entity.CustomerDetails;
import com.example.repository.CustomerDetailsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CreditCardApplyApplication.class)

public class JpaUnitTest {

	@Autowired
	private CustomerDetailsRepository customerRepository;
	
	
	@Test
	public void testCustomerDetailsRepository() {
		CustomerDetails customer = new CustomerDetails();
		customer.setCustomerId(1L);
		customer.setFatherName("Aldrin");
		customer.setPanCard("ABCDE1234A");
		customerRepository.save(customer);
		Assert.assertNotNull(customer.getFatherName());
	}

}
