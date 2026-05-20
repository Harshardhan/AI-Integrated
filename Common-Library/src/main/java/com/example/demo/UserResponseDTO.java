package com.example.demo;

import java.io.Serializable;




import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long userId;
	
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String address;
	private Set<String> roleNames;
	private UserStatus status;
	private String profilePictureUrl;
}
	
	


