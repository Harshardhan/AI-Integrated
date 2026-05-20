package day9java;

public class FindLongestSubStringNonRepeatingCharacter {
	
		public static void main(String[] args) {
		String str = "harshavardhan";
		int result = lengthOfLongestSubstring(str);
		System.out.println("Length of longest substring without repeating characters: " + result);
	}

	private static int lengthOfLongestSubstring(String str) {
		int[] freq = new int[256]; // ASCII character set
		int left = 0, maxLength = 0;

		for (int right = 0; right < str.length(); right++) {
			char currentChar = str.charAt(right);
			freq[currentChar]++;

			while (freq[currentChar] > 1) {
				freq[str.charAt(left)]--;
				left++;
			}

			maxLength = Math.max(maxLength, right - left + 1);
			
		}

		return maxLength;
	}

}
