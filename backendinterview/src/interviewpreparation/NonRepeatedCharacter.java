package interviewpreparation;

import java.util.LinkedHashMap;
import java.util.Map;

public class NonRepeatedCharacter {

	public static void main(String[] args) {
		String str = "HARSHA VARDAHAN";

		Character result = findFirstNonRepeatedCharacter(str);

		if (result != null) {
			System.out.println("The first non-repeated character is: " + result);
		} else {
			System.out.println("No non-repeated character found.");

		}
	}

	private static Character findFirstNonRepeatedCharacter(String str) {

		Map<Character, Integer> map = new LinkedHashMap<>();

		if (str == null || str.isEmpty()) {
			throw new IllegalArgumentException("Input string cannot be null or empty");
		}

		for (char c : str.toCharArray()) {
			if (c == ' ')
				continue;
			char lower = Character.toUpperCase(c);
			map.put(c, map.getOrDefault(lower, 0) + 1);
		}

		for (Map.Entry<Character, Integer> entry : map.entrySet()) {

			if (entry.getValue() == 1) {
				return entry.getKey();
			}
		}
		return null;
	}
}
