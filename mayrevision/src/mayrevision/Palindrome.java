package mayrevision;

public class Palindrome {

	public static void main(String[] args) {
		
		String str = "Harsha";
		if (isPalindrome(str)) {
			System.out.println(str + " is a palindrome");
		} else {
			System.out.println(str + " is not a palindrome");
		}
	}

	private static boolean isPalindrome(String str) {
		
		int left =0;
		int right = str.length() - 1;
		
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		
		return true;
	}
}
