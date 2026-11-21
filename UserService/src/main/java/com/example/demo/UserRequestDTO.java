package com.example.demo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

	@NotBlank(message = "Username is mandatory")
	private String username;

	@Email
	private String email;

	@Size(min = 8, message = "Password must be at least 8 characters")
	private String password;
	@Min(value = 18, message = "Age must be at least 18")
	private Integer age;
}
