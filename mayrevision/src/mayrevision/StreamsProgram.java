package mayrevision;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamsProgram {

	public static void main(String[] args) {

		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

		List<Integer> result = numbers.stream().filter(n -> n % 2 == 0) // Filter even numbers
				.map(n -> n * 2) // multiply each even number by 2
				.collect(Collectors.toList()); // Collect the results into a List
		
		System.out.println(result); // Output: [4, 8, 12, 16, 20]
	}
}