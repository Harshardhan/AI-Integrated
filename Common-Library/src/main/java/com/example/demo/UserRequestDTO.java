package com.example.demo;


import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

	@NotNull(message = "Username is required")
	private String username;

	@NotNull(message = "Email is required")
	@Email(message = "Email should be valid")
	
	private String email;

	@NotBlank(message = "Password is required")
	@Size(min = 6, message = "Password must be at least 6 characters long")
	private String password;

	private String firstName;

	private String lastName;

	@NotNull(message = "Mobile number is required")
	private String mobileNumber;

	private String address;

	private Set<Role> roles;

	private Boolean active;
	private Boolean locked;
	private Boolean expired;
	private UserStatus status;

	private String profilePictureUrl;



}
