package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEvent {

	private String eventType; // USER_REGISTERED, USER_VERIFIED
	private Long userId;
	private String emailId;
	private String mobileNumber;

}