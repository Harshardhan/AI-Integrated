package revision;

import java.util.HashMap;
import java.util.Map;

public class RemoveDuplicatesFromString {

	public static String removeDuplicates(String str) {

	    StringBuilder result = new StringBuilder();

	    for (int i = 0; i < str.length(); i++) {
	        char ch = str.charAt(i);

	        if (result.indexOf(String.valueOf(ch)) == -1) {
	            result.append(ch);
	        }
	    }

	    return result.toString();
	}
	public static void main(String[] args) {
		
		String input = "harshavardhan";
		String result =removeDuplicates(input);
		System.out.println(result);

	}
}
