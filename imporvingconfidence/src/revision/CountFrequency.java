package revision;

import java.util.HashMap;
import java.util.Map;

public class CountFrequency {

	public static void characterFrequency(String str) {
		
		str = str.toLowerCase();
		
		Map<Character, Integer> countFrequency = new HashMap<>();
		
		for(int i = 0; i<str.length();i++) {
			char ch = str.charAt(i);
				countFrequency.put(ch, countFrequency.getOrDefault(ch, 0) + 1);
		}	
			for(Map.Entry<Character, Integer> entry: countFrequency.entrySet()) {
				System.out.println(entry.getKey()+" = "+entry.getValue());
			}
		}
	

	public static void main(String[] args) {
		String input = "harshavardhan";
		 characterFrequency(input);
	}
}