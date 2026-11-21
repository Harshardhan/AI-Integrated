package mayrevision;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Streams {

	public static void main(String[] args) {

		List<Employee> employees = new ArrayList<>();

		employees.add(new Employee(1, "John", null, 70000, 0, 0));
		employees.add(new Employee(3, "Alice", null, 40000, 0, 0));

		Stream<Employee> stream = employees.stream();

		employees.add(new Employee(2, "Bob", null, 90000, 0, 0));

		stream.forEach(System.out::println);
	}}