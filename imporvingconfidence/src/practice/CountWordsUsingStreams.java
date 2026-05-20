package practice;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CountWordsUsingStreams {

	public static Long countWords(List<String> list) {
		return list.stream().flatMap(sentence -> Arrays.stream(sentence.split("\\s+"))).count();
		
	}
	
	public static void main(String[] args) {
		
		List<String> input = Arrays.asList("I am a Java Developer", "I am working at Rakuten");
		
		Long result  = countWords(input);
		
		System.out.println(result.toString());
	}
}
