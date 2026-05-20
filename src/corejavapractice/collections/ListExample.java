package corejavapractice.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class ListExample {
	public static void main(String[] args) {
		List<Integer> numbers =  new ArrayList();
		Random random =  new Random();
		for(int i = 0;i<15;i++) {
			numbers.add(random.nextInt(100));
		}
	
	System.out.println("original list: "+numbers);
	
	Collections.sort(numbers);
	System.out.println("Sorted list(Ascending order): "+numbers);
	
	Collections.sort(numbers, Collections.reverseOrder());
	System.out.println("Sorted list(Descending order): "+numbers);
	
	
	Set<Integer> uniqueNumberSet =  new HashSet<>(numbers);
	List<Integer> uniqueNumbers = new ArrayList<>(uniqueNumberSet);
	
	Collections.sort(numbers);
    System.out.println("List after Removing Duplicates: " + uniqueNumbers);

    List<Integer> uniqueNumberStream = numbers.stream().distinct().sorted().collect(Collectors.toList());
    System.out.println("List after Removing Duplicates (Using Streams): " + uniqueNumberStream);

	
	}
	
	

}
