package mayrevision;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeManagementSystem {

	public static void main(String[] args) {

		List<Employee> employeeList = new ArrayList<>();

		// Adding employees to the list
		employeeList.add(new Employee(101, "John", "IT", 90000.00, 5, 6));
		employeeList.add(new Employee(102, "David", "HR", 65000.00, 4, 5));
		employeeList.add(new Employee(103, "Smith", "IT", 120000.00, 5, 8));
		employeeList.add(new Employee(104, "Alice", "Finance", 80000.00, 2, 7));
		employeeList.add(new Employee(105, "Emma", "HR", 70000.00, 5, 6));
		employeeList.add(new Employee(106, "Chris", "Finance", 95000.00, 4, 9));
		employeeList.add(new Employee(107, "Bob", "IT", 85000.00, 3, 4));
		employeeList.add(new Employee(108, "Nancy", "Finance", 100000.00, 5, 10));
		employeeList.add(new Employee(109, "Kevin", "HR", 60000.00, 1, 3));
		employeeList.add(new Employee(110, "Peter", "IT", 150000.00, 5, 12));

		// Group employees by department , ignore emmployees whose rating is less than
		// 3, for every department: Find highest Salary and average salary, count the
		// number of eligible employees, sort department based on average salary(highest
		// first)
		employeeList.stream().filter(e -> e.getRating() >= 3)
				.collect(Collectors.groupingBy(Employee::getEmployeeDepartment)).entrySet().stream()
				.sorted((e1, e2) -> Double.compare(
						e2.getValue().stream().mapToDouble(Employee::getEmployeeSalary).average().orElse(0),
						e1.getValue().stream().mapToDouble(Employee::getEmployeeSalary).average().orElse(0)))
				.forEach(entry -> {
					String department = entry.getKey();
					List<Employee> employees = entry.getValue();
					double highestSalary = employees.stream().mapToDouble(Employee::getEmployeeSalary).max().orElse(0);
					double averageSalary = employees.stream().mapToDouble(Employee::getEmployeeSalary).average()
							.orElse(0);
					long eligibleCount = employees.size();

					System.out.println("Department: " + department);
					System.out.println("Highest Salary: " + highestSalary);
					System.out.println("Average Salary: " + averageSalary);
					System.out.println("Eligible Employees Count: " + eligibleCount);
					System.out.println();
				});

	}
}