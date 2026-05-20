package practice;

public class FindFirstNonRepeatingCharacter {

	public static Character  findFirstNonRepeatCharacter(String str) {
        if (str == null || str.isEmpty()) 
        	return null;

        str = str.toLowerCase();
		int[] count = new int[256];

		for (char ch : str.toCharArray()) {
			count[ch]++;

		}

		for (char c : str.toCharArray()) {
			if (count[c] == 1) {
				return c;
			}
		}
		return null;

	}

	public static void main(String[] args) {
		String input = "harshavardhan";
		Character result = findFirstNonRepeatCharacter(input);

		if (result != '\0') {
			System.out.println("First non-repeating character: " + result);
		} else {
			System.out.println("No non-repeating character found");
		}
	}

}
