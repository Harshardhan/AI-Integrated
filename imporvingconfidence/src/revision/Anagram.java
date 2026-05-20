package revision;

import java.util.HashMap;
import java.util.Map;

public class Anagram {
	
	public static boolean checkAnagram(String str1, String str2) {
		str1 =str1.replaceAll("\\s", "").toLowerCase();
		str2 =str2.replaceAll("\\s", "").toLowerCase();

		
		if(str1.length() != str2.length()) 
			return false;

		Map<Character, Integer> map = new HashMap<>();
		
		for(char ch : str1.toCharArray()) {
			map.put(ch, map.getOrDefault(ch, 0)+1);
		}
		
		for(char ch : str2.toCharArray()) {
			if(! map.containsKey(ch)) {
				return false;
			}
			map.put(ch, map.get(ch)-1);
			if(map.get(ch)==0){
				map.remove(ch);
			}
		}
	
		return map.isEmpty();
	}
	public static void main(String[] args) {
		
		String str1 = "harsh";
		String str2 ="shard";
        if (checkAnagram(str1, str2)) {
            System.out.println("Anagram");
        } else {
            System.out.println("Not Anagram");
        }
    }

	}
