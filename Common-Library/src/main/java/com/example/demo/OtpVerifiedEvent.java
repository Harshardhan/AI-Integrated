package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OtpVerifiedEvent {

	private Long userId;
	private String emailId;
	private String eventType ;
	
	private String mobileNumber;

		// TODO Auto-generated method stub
		
	
}
