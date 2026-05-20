package mayrevision;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeManagementSystem {

	public static void main(String[] args) {

		List<Employee> employeeList = new ArrayList<>();

		employeeList.add(new Employee("John Doe", 1, "IT", 50000, "Software Engineer"));
		employeeList.add(new Employee("Jane Smith", 2, "HR", 45000, "HR Manager"));
		employeeList.add(new Employee("Alice Johnson", 3, "Finance", 55000, "Financial Analyst"));
		employeeList.add(new Employee("Bob Brown", 4, "IT", 60000, "Senior Software Engineer"));
		employeeList.add(new Employee("Charlie Davis", 5, "Marketing", 40000, "Marketing Specialist"));
		employeeList.add(new Employee("Eve Wilson", 6, "HR", 48000, "Recruiter"));
		employeeList.add(new Employee("Frank Miller", 7, "Finance", 52000, "Accountant"));

		// Highest salary employee
		Employee highestSalaryEmployee = employeeList.stream()
				.max(Comparator.comparingDouble(Employee::getEmployeeSalary)).orElse(null);
		if (highestSalaryEmployee != null) {
			System.out.println("Highest Salary Employee: " + highestSalaryEmployee.getEmployeeName() + " - "
					+ highestSalaryEmployee.getEmployeeSalary());
		}

		// Group employees by department
		employeeList.stream().collect(Collectors.groupingBy(Employee::getEmployeeDepartment))
				.forEach((department, employees) -> {
					System.out.println("Department: " + department);
					employees.forEach(employee -> System.out.println(" - " + employee.getEmployeeName()));
				});

		// Sort employees by salary in descending order
		employeeList.stream().sorted(Comparator.comparingDouble(Employee::getEmployeeSalary).reversed()).forEach(
				employee -> System.out.println(employee.getEmployeeName() + " - " + employee.getEmployeeSalary()));

		// Average salary of employees
		double averageSalary = employeeList.stream().mapToDouble(Employee::getEmployeeSalary).average().orElse(0);
		System.out.println("Average Salary: " + averageSalary);

		// Filter employees by salary greater than 50000
		employeeList.stream().filter(employee -> employee.getEmployeeSalary() > 50000).forEach(
				employee -> System.out.println(employee.getEmployeeName() + " - " + employee.getEmployeeSalary()));

	}
}