package corejavapractice.studentmanagementsystem;


import java.util.List;

public class Teacher extends Person {
    private String teacherId;
    private List<Course> teachingCourses; // Teacher can teach multiple courses

    // Constructor
    public Teacher(String name, int age, String teacherId, List<Course> teachingCourses) {
        super(name, age);
        this.teacherId = teacherId;
        this.teachingCourses = teachingCourses;
    }

    // Method to display teacher details and courses they teach
    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Teacher ID: " + teacherId);
        System.out.println("Courses Taught:");
        for (Course course : teachingCourses) {
            course.displayCourseDetails();
        }
    }
}
