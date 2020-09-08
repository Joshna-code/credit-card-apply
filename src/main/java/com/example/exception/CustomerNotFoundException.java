package com.example.exception;

public class CustomerNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1332597232379387235L;

	public CustomerNotFoundException(Long id) {
	    super("Could not find customer " + id);
	  }
}

