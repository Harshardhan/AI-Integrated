package corejavapractice.oops;

public class Employee {

	private String name;
	private double salary;
	/**
	 * @param name
	 * @param salary
	 */
	public Employee(String name, double salary) {
		super();
		this.name = name;
		this.salary = salary;
	}
	
	public void displayDetails() {
		System.out.println("Employee name: "+name);
		System.out.println("Salary: "+salary);
	}
}
