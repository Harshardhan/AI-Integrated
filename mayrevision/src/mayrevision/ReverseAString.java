package mayrevision;

public class ReverseAString {

	public static void main(String[] args) {
		
		String str = "harsha";
		System.out.println(str + " reversed is " + new StringBuilder(str).reverse().toString());
	}

	public static void reverseString(String str) {
		String reversed = "";
		for (int i = str.length() - 1; i >= 0; i--) {
			reversed += str.charAt(i);
		}
		System.out.println(str + " reversed is " + reversed);
	}
}
