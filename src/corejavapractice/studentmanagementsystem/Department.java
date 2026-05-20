package corejavapractice.studentmanagementsystem;

import java.util.List;

public class Department {

	private String departmentId;
	
	private String departmentName;
	
	private List<Course> courses;
	
	
	/**
	 * @param departmentId
	 * @param departmentName
	 * @param courses
	 */
	public Department(String departmentId, String departmentName, List<Course> courses) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
		this.courses = courses;
	}

	/**
	 * @return the departmentId
	 */
	public String getDepartmentId() {
		return departmentId;
	}

	/**
	 * @param departmentId the departmentId to set
	 */
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 * @param courses the courses to set
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	
	
    public void displayDepartmentDetails() {
        System.out.println("Department ID: " + departmentId + ", Department Name: " + departmentName);
        System.out.println("Courses Offered:");
        for (Course course : courses) {
            course.displayCourseDetails();
        }
    }

	
}
