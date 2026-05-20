package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	public EmployeeResponseDTO findByEmpEmail(String empEmail);

	public EmployeeResponseDTO findByEmpName(String empName);

	public EmployeeResponseDTO findByMobileNumber(String mobileNumber);

	public List<EmployeeResponseDTO> findByEmpDepartment(String empDepartment);
	


}
