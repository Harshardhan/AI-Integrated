package corejavapractice.studentmanagementsystem;


import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Create some courses
        Course course1 = new Course("C001", "Mathematics", 4);
        Course course2 = new Course("C002", "Computer Science", 3);
        Course course3 = new Course("C003", "Physics", 4);

        // Create a department with multiple courses
        Department department = new Department("D001", "Science", Arrays.asList(course1, course2, course3));

        // Create a student who is enrolled in multiple courses
        Student student = new Student("John Doe", 20, "S001", Arrays.asList(course1, course2));

        // Create a teacher who teaches multiple courses
        Teacher teacher = new Teacher("Dr. Smith", 45, "T100", Arrays.asList(course2, course3));

        // Display department details
        System.out.println("Department Details:");
        department.displayDepartmentDetails();

        // Display student details
        System.out.println("\nStudent Details:");
        student.getDetails();

        // Display teacher details
        System.out.println("\nTeacher Details:");
        teacher.getDetails();
    }
}
