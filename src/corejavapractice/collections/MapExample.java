package corejavapractice.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExample {

	public static void main(String[] args) {

		Map<String, Integer> studentGrades = new HashMap<>();

		studentGrades.put("Bob", 96);
		studentGrades.put("Harsha", 85);
		studentGrades.put("Alice", 88);
		studentGrades.put("Alice", 85);
		studentGrades.put("Bob", 92);
		studentGrades.put("Charlie", 78);
		studentGrades.put("Diana", 90);
		studentGrades.put("Eve", 88);

		Map<Integer, List<String>> gradeToStudentsMap = new HashMap<>();
		for (Map.Entry<String, Integer> entry : studentGrades.entrySet()) {
			Integer grade = entry.getValue();
			String name = entry.getKey();

			// If the grade already exists, add the student to the list
			if (!gradeToStudentsMap.containsKey(grade)) {
				gradeToStudentsMap.put(grade, new ArrayList<>());
			}
			gradeToStudentsMap.get(grade).add(name);
		}

		// Display students with the same grade
		System.out.println("Students with the same grades:");
		for (Map.Entry<Integer, List<String>> entry : gradeToStudentsMap.entrySet()) {
			List<String> names = entry.getValue();
			if (names.size() > 1) { // More than one student with the same grade
				System.out.println("Grade " + entry.getKey() + ": " + names);
			}
		}

		// Display all student grades
		System.out.println("\nAll Student Grades:");
		for (Map.Entry<String, Integer> entry : studentGrades.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());

		}
	}
}
