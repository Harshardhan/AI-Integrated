package revision;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeesList {

	private static List<Employee> sortEmployeeBySalary(List<Employee> employee) {
		
		 employee.sort(Comparator.comparing(Employee::getSalary));
		return employee;
	}
	
	private static List<Employee> sortEmployeeByName(List<Employee> empName){
		 return empName.stream().
				 sorted(Comparator.comparing(Employee :: getEmpName)).
				 collect(Collectors.toList());
	}
	
	private static List<Employee> sortEmpByDept(List<Employee> empDept){
		 empDept.stream().collect(Collectors.groupingBy(Employee::getDepartment));
		 return empDept;
		 
	}
	public static void main(String[] args) {
		
		List<Employee> list = new ArrayList<>();
		
		list.add(new Employee(1, "harshavardhan", BigDecimal.valueOf(30000.00), "Software developer", "9160819142", "Tirupati", "IT Department"));
		list.add(new Employee(2, "vardhan", BigDecimal.valueOf(50000.00), "Software developer", "7619231563", "Tirupati"," HR Department"));
		list.add(new Employee(3, "prasad", BigDecimal.valueOf(80000.00), "Software developer", "7321073765", "Tirupati","QA Department"));
		list.add(new Employee(4, "rajesh", BigDecimal.valueOf(40000.00), "Software developer", "9000821169", "Tirupati"," IT Department"));

	
	List<Employee> sortEmp =sortEmployeeBySalary(list);

	List<Employee> sortEmpByName = sortEmployeeByName(list);
	
	List<Employee> sortEmpByDept = sortEmpByDept(list);

	for(Employee s : sortEmp) {
		System.out.println(s);
	}
	
	for(Employee s : sortEmpByName) {
		System.out.println(s);
	}

	for(Employee s : sortEmpByDept) {
		System.out.println(s);
	}

	}
}
