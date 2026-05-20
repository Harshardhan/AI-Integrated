package com.example.demo;

import java.time.LocalDateTime;

public class ErrorResponse {
	
	
	private int statusCode;
	
	private String message;
	
	private LocalDateTime timeStamp;


	public int getStatus() {
		return statusCode;
	}

	public void setStatus(int status) {
		this.statusCode = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getCreatedAt() {
		return timeStamp;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.timeStamp = createdAt;
	}

	public ErrorResponse( int status, String message, LocalDateTime createdAt) {
		super();
		this.statusCode = status;
		this.message = message;
		this.timeStamp = createdAt;
	}
	
	
	

}
