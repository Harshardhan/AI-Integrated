package revision;


public class CharacterFrequency {

	public static int[] charFrequency(String str) {

		if (str == null || str.length() == 0) {
			return null;
		}

		int[] frequency = new int[256];

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			frequency[ch]++;
		}

		for (int i = 0; i < 256; i++) {
			if (frequency[i] > 0) {
				System.out.println((char) i + " = " + frequency[i]);

			}
		}

		return frequency;

	}

	public static void main(String[] args) {

		String input = "harshavardhan";

		 charFrequency(input);

	}
}
