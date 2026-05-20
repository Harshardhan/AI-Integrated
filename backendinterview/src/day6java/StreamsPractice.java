package day6java;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsPractice {

	public static void main(String[] args) {
		
	
	List<Gender> list = new ArrayList<>();
	
	list.add(new Gender(1, "HarshaVardhan", 25));
	list.add(new Gender(2, "Vardhan", 26));
	list.add(new Gender(3, "Harsha", 27));
	list.add(new Gender(4, "Rajesh", 28));
	list.add(new Gender(5, "Prasad", 29));
	list.add(new Gender(6, "John DEO", 24));
	
	List<String> comparingByAge = list.stream()
			.filter(gender -> gender.getAge()>25).map(Gender::getName).
			collect(Collectors.toList());
	
	double averageByAge = list.stream()
			.mapToInt(Gender::getAge).average().orElse(0);
	System.out.println(averageByAge);

	 list.stream().max(Comparator.comparingInt(Gender::getAge))
			.ifPresent(System.out::println);
	
	List<String> sortedNamesByAge = list.stream()
			.sorted(Comparator.comparingInt(Gender::getAge)).map(Gender::getName).collect(Collectors.toList());

	System.out.println(sortedNamesByAge);
	for(String result: comparingByAge) {
		System.out.println(result);
	}
}
}