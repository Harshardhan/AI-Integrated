package practice;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SortListOfIntegers {

	
	public static void main(String[] args) {
		
		List<Integer> numbers = new LinkedList<>();
		
		numbers.add(1);
		numbers.add(5);
		numbers.add(4);
		numbers.add(55);
		numbers.add(2);
		
		Collections.sort(numbers);
		
		System.out.println(numbers);
	}
}
