package corejavapractice.studentmanagementsystem;

public class Course {

	private String courseId;
	
	private String courseName;
	
	private int credits;
	
	
	public void displayCourseDetails() {
		
		System.out.println("CourseId: "+ courseId + ", CourseName: "+courseName+", credits: "+credits);
	}
	

	/**
	 * @param courseId
	 * @param courseName
	 * @param credits
	 */
	public Course(String courseId, String courseName, int credits) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.credits = credits;
	}

	/**
	 * @return the courseId
	 */
	public String getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	
	
}
