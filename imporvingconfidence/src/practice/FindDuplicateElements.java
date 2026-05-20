package practice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FindDuplicateElements {

	public static List<String> findDuplicateElements(List<String> list) {
		return list.stream().collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream()
				.filter(entry -> entry.getValue() > 1).map(entry -> entry.getKey()).collect(Collectors.toList());
	}
	
	public static List<String> findDuplicateElement(List<String> list) {
	    Set<String> seen = new HashSet<>();
	    return list.stream()
	               .filter(e -> !seen.add(e))
	               .distinct()
	               .collect(Collectors.toList());
	}


	public static void main(String[] args) {

		List<String> input = Arrays.asList("Apple", "Banana", "Carrot", "Mango", "Apple", "Mango");

		List<String> result = findDuplicateElements(input);
		List<String> set = findDuplicateElement(input);

		System.out.println(result);

		System.out.println(set);
	}
}
