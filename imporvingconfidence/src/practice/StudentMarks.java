package practice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentMarks {

	public static Student searchStudentById(List<Student> list, long id) {
		
		for(Student student : list) {
			if(student.getId() == id) {
				
				System.out.println("Student Id: "+student.getId());
			}
		}

		
		return null;
		
	}
	
	public static boolean deleteStudentById(List<Student> list, long id) {
	    return list.removeIf(student -> student.getId() == id);
	}
	
	public static Map<Long, String> highestMarks(List<Student> list){
	    Map<Long, String> result = new HashMap<>();

	    list.stream()
		        .max(Comparator.comparingInt(Student::getMarks))
		        .ifPresent(student -> result.put(student.getId(), student.getStudentName()));

		return result;
		
	}

	public static void main(String[] args) {
		
		List<Student> list = new ArrayList<>();
		
		
		int searchById = 2;
		
		Long deleteById  =3L;
		
		list.add(new Student(1L, "harshavardhan", 25,"English"));
		list.add(new Student(2L, "harsha", 23,"English"));
		list.add(new Student(3L, "vardhan", 22,"English"));

		
		Map<Long, String> map = highestMarks(list);
		
		System.out.println(map);

		
		Student found = searchStudentById(list, searchById);
		if (found != null) {
		    System.out.println("Student Found: " + found);
		} else {
		    System.out.println("Student not found");
		}
		
		boolean removed = deleteStudentById(list, deleteById);
		if (removed) {
		    System.out.println("Student deleted successfully");
		} else {
		    System.out.println("Student not found to delete");
		}
		
		for(Student s : list) {
			System.out.println(s);
		}

	}
	
}
