package corejavapractice.oops;

public class Manager extends Employee {
	
	
	private String department;

	/**
	 * @param name
	 * @param salary
	 * @param department
	 */
	public Manager(String name, double salary, String department) {
		super(name, salary);
		this.department = department;
	
	}

	@Override
	public void displayDetails() {
		super.displayDetails();
		System.out.println("Department: "+department);
	}

}
