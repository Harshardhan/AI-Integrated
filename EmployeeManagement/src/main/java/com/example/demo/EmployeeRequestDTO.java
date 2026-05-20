package com.example.demo;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EmployeeRequestDTO(

		Long id, 
		
		@NotBlank(message = "Employee name is required")
		String empName, 
		
		@Email(message = "Invalid email format")
		String empEmail, 
		
		String empDepartment,
		
		String empDesignation, 
		
		@Pattern(regexp = "\\d{10}", message = "Mobile number must be 10 digits")
		String mobileNumber,
		
		
		String address, 
		
		@DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
		BigDecimal empSalary) {

}