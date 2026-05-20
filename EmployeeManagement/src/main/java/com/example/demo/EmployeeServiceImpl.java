package com.example.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);
	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public EmployeeResponseDTO createEmployee(EmployeeRequestDTO employee) {
		if (employee == null) {
			logger.error("Employee request is null");
			throw new IllegalArgumentException("Employee request cannot be null");
		}

		Employee emp = new Employee();
		emp.setEmpName(employee.empName());
		emp.setEmpEmail(employee.empEmail());
		emp.setMobileNumber(employee.mobileNumber());
		emp.setEmpDepartment(employee.empDepartment());
		emp.setAddress(employee.address());
		emp.setEmpSalary(employee.empSalary());
		emp.setEmpDesignation(employee.empDesignation());

		Employee savedEmp = employeeRepository.save(emp);
		return new EmployeeResponseDTO(savedEmp.getId(), savedEmp.getEmpName(), savedEmp.getEmpEmail(),
				savedEmp.getEmpDepartment(), savedEmp.getEmpDesignation(), savedEmp.getMobileNumber(),
				savedEmp.getAddress(), savedEmp.getEmpSalary());
	}

	@Override
	public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO employee) {

		Employee existingEmp = employeeRepository.findById(id).orElseThrow(() -> {
			logger.error("Employee not found with id: {}", id);
			return new EmployeeNotFoundException("Employee not found with id: " + id);
		});

		existingEmp.setEmpName(employee.empName());
		existingEmp.setEmpEmail(employee.empEmail());
		existingEmp.setMobileNumber(employee.mobileNumber());
		existingEmp.setEmpDepartment(employee.empDepartment());
		existingEmp.setAddress(employee.address());
		existingEmp.setEmpSalary(employee.empSalary());
		existingEmp.setEmpDesignation(employee.empDesignation());

		Employee updatedEmp = employeeRepository.save(existingEmp);

		logger.info("Employee updated successfully with id: {}", id);

		return new EmployeeResponseDTO(updatedEmp.getId(), updatedEmp.getEmpName(), updatedEmp.getEmpEmail(),
				updatedEmp.getEmpDepartment(), updatedEmp.getEmpDesignation(), updatedEmp.getMobileNumber(),
				updatedEmp.getAddress(), updatedEmp.getEmpSalary());
	}

	@Override
	public void deleteEmployee(Long id) {

		if (!employeeRepository.existsById(id)) {
			logger.error("Employee not found with id: {}", id);
			throw new EmployeeNotFoundException("Employee not found with id: " + id);
		}

		employeeRepository.deleteById(id);
	}

	@Override
	public EmployeeResponseDTO getEmployeeById(Long id) {

		return employeeRepository.findById(id)
				.map(emp -> new EmployeeResponseDTO(emp.getId(), emp.getEmpName(), emp.getEmpEmail(),
						emp.getEmpDepartment(), emp.getEmpDesignation(), emp.getMobileNumber(), emp.getAddress(),
						emp.getEmpSalary()))
				.orElseThrow(() -> {
					logger.error("Employee not found with id: {}", id);
					return new EmployeeNotFoundException("Employee not found with id: " + id);
				});
	}

	@Override
	public List<EmployeeResponseDTO> getAllEmployees() {

		List<Employee> employees = employeeRepository.findAll();

		if (employees.isEmpty()) {
			logger.error("No employees found");
			throw new EmployeeNotFoundException("No employees found");
		}

		return employees.stream()
				.map(emp -> new EmployeeResponseDTO(emp.getId(), emp.getEmpName(), emp.getEmpEmail(),
						emp.getEmpDepartment(), emp.getEmpDesignation(), emp.getMobileNumber(), emp.getAddress(),
						emp.getEmpSalary()))
				.toList();

	}

	@Override
	public List<EmployeeResponseDTO> getEmployeesByDepartment(String name) {

		List<EmployeeResponseDTO> employees = employeeRepository.findByEmpDepartment(name);

		if (employees.isEmpty()) {
			logger.error("No employees found in department: {}", name);
			throw new EmployeeNotFoundException("No employees found in department: " + name);
		}

		return employees
				.stream().map(emp -> new EmployeeResponseDTO(emp.id(), emp.empName(), emp.empEmail(),
						emp.empDepartment(), emp.empDesignation(), emp.mobileNumber(), emp.address(), emp.empSalary()))
				.toList();
	}
}
