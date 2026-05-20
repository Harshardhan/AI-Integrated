package practice;

public class ReverseAString {

	public static String reverseString(String str) {
		char[] chars = str.toCharArray();
		int left = 0;
		int right = chars.length - 1;
		while (left < right) {
			char temp = chars[left];
			chars[left] = chars[right];
			chars[right] = temp;
			left++;
			right--;
		}

		return new String(chars);

	}

	public static String reverseAString(String str) {
		return new StringBuilder(str).reverse().toString();
	}
	public static void main(String[] args) {
		String input = "harshavardhan";

		String result = reverseString(input);
		String reverseString = reverseAString(input);
		System.out.println("Reverse A String: " + result);
		System.out.println("Reverse A String using string builder: "+reverseString);
	}
}
