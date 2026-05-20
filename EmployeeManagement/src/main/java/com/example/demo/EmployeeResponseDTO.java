package com.example.demo;

import java.math.BigDecimal;

public record EmployeeResponseDTO(

		Long id, String empName, String empEmail, String empDepartment, String empDesignation, String mobileNumber,
		String address, BigDecimal empSalary

)

{

}
