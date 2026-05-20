package practice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class MaxNumberUsingStreams {

	public static Optional<Integer> findMaxNumberUsingStreams(List<Integer> numbers){
		return numbers.stream()
	              .sorted(Comparator.reverseOrder()).skip(1)
	              .findFirst();
	}
	
	public static void main(String[] args) {
		
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6);
		
		Optional<Integer> maxNumber = findMaxNumberUsingStreams(numbers);
		
		System.out.println(maxNumber.orElse(null));

}
}