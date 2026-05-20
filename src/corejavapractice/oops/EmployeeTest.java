package corejavapractice.oops;

public class EmployeeTest {

    public static void main(String[] args) {
        // Creating instances of Manager and Developer
        Employee emp1 = new Manager("Alice", 80000, "Sales");
        Employee emp2 = new Developer("Bob", 60000, "Java");

        // Displaying details
        System.out.println("Manager Details:");
        emp1.displayDetails();
        System.out.println();

        System.out.println("Developer Details:");
        emp2.displayDetails();
    }

}
