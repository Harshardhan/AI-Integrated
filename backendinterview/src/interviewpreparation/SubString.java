package interviewpreparation;

import java.util.HashMap;
import java.util.Map;

public class SubString {

	public static void main(String[] args) {

		String str = "harshavardhan";

		String t = "var";

		String result = checkMinSubString(str, t);
		System.out.println("Find Minimum SubString using window: " + result.toString());
	}

	private static String checkMinSubString(String str, String t) {

	    if (str == null || t == null) return "";

	    str = str.toLowerCase();
	    t =t.toLowerCase();
	    Map<Character, Integer> minChar = new HashMap<>();

	    for (char c : t.toCharArray()) {
	        minChar.put(c, minChar.getOrDefault(c, 0) + 1);
	    }

	    int left = 0, required = t.length();
	    int minLen = Integer.MAX_VALUE, start = 0;

	    for (int right = 0; right < str.length(); right++) {
	        char c = str.charAt(right);

	        if (minChar.containsKey(c)) {
	            minChar.put(c, minChar.get(c) - 1);

	            if (minChar.get(c) >= 0) {
	                required--;
	            }
	        }

	        while (required == 0) {
	            if (right - left + 1 < minLen) {
	                minLen = right - left + 1;
	                start = left;
	            }

	            char leftChar = str.charAt(left);

	            if (minChar.containsKey(leftChar)) {
	                minChar.put(leftChar, minChar.get(leftChar) + 1);

	                if (minChar.get(leftChar) > 0) {
	                    required++;
	                }
	            }

	            left++;
	        }
	    }

	    return minLen == Integer.MAX_VALUE ? "" : str.substring(start, start + minLen);
	}}
