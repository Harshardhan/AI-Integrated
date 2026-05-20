package corejavapractice.studentmanagementsystem;

import java.util.List;

public class Student extends Person implements Role {
	private String studentId;
	private List<Course> enrolledCourses;

	/**
	 * @param name
	 * @param age
	 * @param studentId
	 * @param enrolledCourses
	 */
	public Student(String name, int age, String studentId, List<Course> enrolledCourses) {
		super(name, age);
		this.studentId = studentId;
		this.enrolledCourses = enrolledCourses;
	}

	// Getter and Setter for studentId (Optional if you want to access or modify
	// studentId directly)
	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	// Overriding getDetails method to print student details
    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Student ID: " + studentId);
        System.out.println("Enrolled Courses:");
        for (Course course : enrolledCourses) {
            course.displayCourseDetails();
        }
    }

	@Override
	public void displayRole() {
		// TODO Auto-generated method stub
		System.out.println("Role: Student");

	}
}
