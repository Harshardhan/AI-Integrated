package corejavapractice.oops;

public class Developer extends Employee {


	private String programmingLanguage;

	/**
	 * @param name
	 * @param salary
	 * @param programmingLanguage
	 */
	public Developer(String name, double salary, String programmingLanguage) {
		super(name, salary);
		this.programmingLanguage = programmingLanguage;
	}
	
    @Override
    public void displayDetails() {
        super.displayDetails(); // Call the superclass method
        System.out.println("Programming Language: " + programmingLanguage);
    }

}
