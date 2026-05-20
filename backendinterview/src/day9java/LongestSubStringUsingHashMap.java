package day9java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubStringUsingHashMap {

	public static void main(String[] args) {

		String str = "harshavardhan";
		List<String> result = checkLongestSubString(str);
		System.out.println("Length of longest substring without repeating characters using hashmap: " + result);
	}

	private static List<String> checkLongestSubString(String str) {

	    if (str == null) {
	        throw new NullPointerException("String must not be null...");
	    }
	    
	    List<String> list = new ArrayList<>();

	    Map<Character, Integer> map = new HashMap<>();
	    int left = 0, maxLength = 0, start = 0;

	    for (int right = 0; right < str.length(); right++) {
	        char currentChar = str.charAt(right);

	        if (map.containsKey(currentChar)) {
	            left = Math.max(left, map.get(currentChar) + 1);
	        }

	        map.put(currentChar, right);

	        if (right - left + 1 > maxLength) {
	            maxLength = right - left + 1;
	            start = left;
	        }
	        for(int i = left; i<=right; i++) {
	        	list.add(str.substring(i, right+1));
	        }
	    }

	    return list;
		  /*  for (int right = 0; right < str.length(); right++) {
	        char currentChar = str.charAt(right);

	        if (map.containsKey(currentChar)) {
	            left = Math.max(left, map.get(currentChar) + 1);
	        }

	        map.put(currentChar, right);

	        maxLength = Math.max(maxLength, right - left + 1);
	    }

	    return maxLength;
	} */
	    
	    
}
}