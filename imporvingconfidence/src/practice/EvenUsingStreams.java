package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EvenUsingStreams {

	public static List<Integer> filterEvenUsingStreams(List<Integer> num) {
    return num.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
		
	}
	public static void main(String[] args) {
		
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
		
	    List<Integer> evenNumbers = filterEvenUsingStreams(numbers);
		System.out.println(evenNumbers);
	}
}
