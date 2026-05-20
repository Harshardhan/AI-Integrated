package interviewpreparation;

public class Palindrome {

	public static void main(String[] args) {

		String str = "level";


		if (checkPalindrome(str)) {
			System.out.println("Given String " + str + " is a palindrome...");
		} else {
			System.out.println("Given String " + str + " is not a Palindrome...");
		}

	}

	private static boolean checkPalindrome(String str) {

		
		if(str == null  ) {
			throw new NullPointerException("String must not be null...");
		}
		
		str = str.replaceAll("\\s","").toLowerCase();

		
		int left = 0;
		int right = str.length()-1;
		
		while(left < right) {
			if(str.charAt(left) != str.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
		
	}
}
