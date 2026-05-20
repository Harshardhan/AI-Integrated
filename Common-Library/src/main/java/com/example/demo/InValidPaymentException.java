package com.example.demo;

public class InValidPaymentException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InValidPaymentException(String message) {
		super(message);
	}

}
