package practice;

import java.util.LinkedList;
import java.util.List;

public class RemoveDuplicatesFromList {
	
	public static List<Student> removeDuplicates(List<Student> list) {

	    List<Student> uniqueList = new LinkedList<>();

	    for (Student student : list) {

	        boolean isDuplicate = false;

	        for (Student uniqueStudent : uniqueList) {
	            if (uniqueStudent.getId().equals(student.getId())) {
	                isDuplicate = true;
	                break;
	            }
	        }

	        if (!isDuplicate) {
	            uniqueList.add(student);
	        }
	    }

	    return uniqueList;
	}
	
	public static void main(String[] args) {
		List<Student> list = new LinkedList<>();
		
		list.add(new Student(1L, "harshavardhan", 25,"English"));
		list.add(new Student(2L, "harsha", 23,"English"));
		list.add(new Student(3L, "vardhan", 22,"English"));
		list.add(new Student(4L, "vardhan", 22,"English"));
		list.add(new Student(5L, "vardhan", 22,"English"));
		list.add(new Student(2L, "harsha", 23,"English"));
		list.add(new Student(1L, "harshavardhan", 25,"English"));
		list.add(new Student(2L, "harsha", 23,"English"));
		list.add(new Student(3L, "vardhan", 22,"English"));
		list.add(new Student(4L, "vardhan", 22,"English"));
		list.add(new Student(5L, "vardhan", 22,"English"));
		list.add(new Student(2L, "harsha", 23,"English"));
		

		

		List<Student> updatedList = removeDuplicates(list);
		for(Student s : updatedList) {
			System.out.println(s);
		}

	}

}
