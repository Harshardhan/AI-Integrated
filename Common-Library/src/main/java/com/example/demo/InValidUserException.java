package com.example.demo;

public class InValidUserException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InValidUserException(String message) {
		super(message);
	}
}