package mayrevision;

import java.util.ArrayList;
import java.util.List;


public class Generics {

	public static void main(String[] args) {
		
		List<Employee> employeeList = new ArrayList<>();
		
		employeeList.add(new Employee(0, "John", null, 30, 0, 0));
		
		}
}
