package mayrevision;

import java.util.HashMap;

public class StringFrequency {

	public static void main(String[] args) {

		String str = "Harsha Vardhan";

		int[] frequency = new int[256];

		HashMap<String, Integer> count = new HashMap<>();

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			frequency[ch]++;
		}

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (frequency[ch] > 0) {
				count.put(String.valueOf(ch), frequency[ch]);
				frequency[ch] = 0;
			}
		}

		System.out.println(count);

	}
}