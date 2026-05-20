package practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeesList {

	public static List<Employee> sortEmployeeSalary(List<Employee> empList) {

		empList.sort(Comparator.comparingDouble(Employee::getSalary));
		return empList;

	}

	public static List<Employee> sortEmployeeDepartment(List<Employee> empList) {

		empList.sort(Comparator.comparing(Employee::getDepartment));
		return empList;

	}

	public static Map<String, List<Employee>> groupEmployeesByDepartment(List<Employee> department) {
		Map<String, List<Employee>> map =

				department.stream().collect(Collectors.groupingBy(Employee::getDepartment));
		return map;

	}

	public static void main(String[] args) {

		List<Employee> empList = new ArrayList<>();

		empList.add(new Employee(1L, "harshavardhan", "9160819142", "harshavardhan.harish@gmail.com", 25000.00,
				"software engineer", "IT Department"));

		empList.add(new Employee(2L, "prasad", "8978861132", "harshavardhan@gmail.com", 32000.00, "software engineer",
				"QA Department"));
		empList.add(new Employee(3L, "lakshmi", "7619231563", "lakshmi.harish@gmail.com", 42000.00, "software engineer",
				"Banking Operations"));
		empList.add(new Employee(4L, "rajesh", "9000821169", "rajesh.@gmail.com", 18000.00, "software engineer",
				"IT Department"));

		List<Employee> sortEmployees = sortEmployeeSalary(empList);
		List<Employee> sortEmpByDept = sortEmployeeDepartment(empList);
		Map<String, List<Employee>> grouped = groupEmployeesByDepartment(empList);

		for (Employee employee : sortEmployees) {
			System.out.println(employee);

		}

		for (Employee empByDept : sortEmpByDept) {
			System.out.println(empByDept);

		}

		for (Map.Entry<String, List<Employee>> entry : grouped.entrySet()) {
			System.out.println("Department: " + entry.getKey());
			for (Employee e : entry.getValue()) {
				System.out.println("   " + e.getEmpName());
			}

		}
	}
}
