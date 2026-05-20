package practice;

public class CountFrequency {

	public static void countFrequencyInEachCharacter(String str) {
		int[] frequency = new int[256];

		// count frequency
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			frequency[ch]++;
		}

		// print the characters after count
		for (int i = 0; i < 256; i++) {
			if (frequency[i] > 0) {
				System.out.println((char) i + " = " + frequency[i]);

			}
		}
	}

	public static void main(String[] args) {

		String input = "harshavardhan";
		countFrequencyInEachCharacter(input);

	}
}
