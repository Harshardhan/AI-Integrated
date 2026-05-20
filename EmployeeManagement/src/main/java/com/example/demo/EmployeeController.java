package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	private final EmployeeService employeeService;
	
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@PostMapping
	public ResponseEntity<EmployeeResponseDTO> createEmployee(@Valid @RequestBody EmployeeRequestDTO employee) {
		logger.info("Creating employee with name: {}", employee.empName());
		EmployeeResponseDTO createdEmployee = employeeService.createEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeRequestDTO employee) {
		logger.info("Updating employee with id: {}", id);
		EmployeeResponseDTO updatedEmployee = employeeService.updateEmployee(id, employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponseDTO> getEmployeeById(@PathVariable Long id) {
		logger.info("Fetching employee with id: {}", id);
		EmployeeResponseDTO employee = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employee);
	}
	
	
	@GetMapping("/all")
	public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {
		logger.info("Fetching all employees");
		List<EmployeeResponseDTO> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping("/department/{name}")
	public ResponseEntity<List<EmployeeResponseDTO>> getEmployeesByDepartment(@PathVariable String name) {
		logger.info("Fetching employees in department: {}", name);
		List<EmployeeResponseDTO> employees = employeeService.getEmployeesByDepartment(name);
		return ResponseEntity.ok(employees);
	}
		
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		logger.info("Deleting employee with id: {}", id);
		employeeService.deleteEmployee(id);
		return ResponseEntity.noContent().build();
	}
	
}
