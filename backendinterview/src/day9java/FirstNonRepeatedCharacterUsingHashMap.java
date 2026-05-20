package day9java;

import java.util.HashMap;
import java.util.Map;

public class FirstNonRepeatedCharacterUsingHashMap {

	public static void main(String[] args) {

		// Given a string and a substring, find the first occurrence of the substring in
		// the string.

		String str = "aabbccd";
		char result = firstNonRepeatedCharacter(str);

		if (result != '-') {
			System.out.println("The first non-repeated character is: " + result);
		} else {
			System.out.println("No non-repeated character found.");
		}
	}

	private static char firstNonRepeatedCharacter(String str) {

		Map<Character, Integer> charCountMap = new HashMap<>();

		for (char c : str.toCharArray()) {
			charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
		}

		for (char c : str.toCharArray()) {
			if (charCountMap.get(c) == 1) {
				return c;
			}
		}
		return '-';
	}
}
