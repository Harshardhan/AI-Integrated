package com.example.demo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String empName;
	
	private String empEmail;
	
	private String empDepartment;
	
	private String empDesignation;
	
	private String mobileNumber;
	
	private String address;
	
	private BigDecimal empSalary;

	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
}
