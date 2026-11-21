package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	
	@PostMapping()
	public ResponseEntity<String> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
		logger.info("Received request to create user: {}", userRequestDTO);
		// Here you would typically call a service to handle the business logic
		return ResponseEntity.ok("User created successfully");
	}
	
	

}
