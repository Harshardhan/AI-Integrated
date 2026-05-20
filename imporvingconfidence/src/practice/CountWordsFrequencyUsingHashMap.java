package practice;

import java.util.HashMap;
import java.util.Map;

public class CountWordsFrequencyUsingHashMap {

	public static void countWordFrequency(String str) {

		str = str.toLowerCase().replaceAll("[^a-z ]", "");
		String[] words = str.split("\\s+");

		HashMap<String, Integer> map = new HashMap<>();

		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}

	}

	public static void main(String[] args) {

		String input = " I am a software engineer. I am working at Rakuten";

		countWordFrequency(input);

	}
}