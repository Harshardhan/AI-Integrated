package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {

	private Long id ;
	
	private String studentName;
	
	private int marks;
	
	private String subjectName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", studentName=" + studentName + ", marks=" + marks + ", subjectName="
				+ subjectName + "]";
	}

	public Student(Long id, String studentName, int marks, String subjectName) {
		super();
		this.id = id;
		this.studentName = studentName;
		this.marks = marks;
		this.subjectName = subjectName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, marks, studentName, subjectName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(id, other.id) && marks == other.marks && Objects.equals(studentName, other.studentName)
				&& Objects.equals(subjectName, other.subjectName);
	}
	
	
}
