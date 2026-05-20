package practice;

public class PalindromeAString {
	
	public static boolean palindromeAString(String str) {
	    int left = 0;
	    int right = str.length() - 1;

	    while (left < right) {
	        if (str.charAt(left) != str.charAt(right)) {
	            return false; // Not a palindrome
	        }
	        left++;
	        right--;
	    }
	    return true; // It is a palindrome
	}

	public static void main(String[] args) {
	    String word = "level";

	    if (palindromeAString(word)) {
	        System.out.println(word + " is a Palindrome");
	    } else {
	        System.out.println(word + " is NOT a Palindrome");
	    }
	}
}
