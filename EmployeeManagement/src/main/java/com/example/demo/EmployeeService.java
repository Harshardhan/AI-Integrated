package com.example.demo;

import java.util.List;

public interface EmployeeService {

	public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employee)
			throws InvalidEmployeeException, EmployeeAlreadyExistsException;

	public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO employee)
			throws InvalidEmployeeException, EmployeeNotFoundException;

	public void deleteEmployee(Long id) throws EmployeeNotFoundException;

	public EmployeeResponseDTO getEmployeeById(Long id) throws EmployeeNotFoundException;

	public List<EmployeeResponseDTO> getAllEmployees();

	public List<EmployeeResponseDTO> getEmployeesByDepartment(String name);

}