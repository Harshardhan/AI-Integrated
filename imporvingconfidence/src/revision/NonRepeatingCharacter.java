package revision;

import java.util.HashMap;
import java.util.Map;

public class NonRepeatingCharacter {

	public static Character findFirstNonRepeatingCharacter(String str) {
		
		str = str.toLowerCase();
		
		Map<Character, Integer> map = new HashMap<>();
		

		for(int i = 0; i<str.length();i++) {
			char ch = str.charAt(i);
			map.put(ch, map.getOrDefault(ch, 0)+1);
		}
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (map.get(ch) == 1) {
                return ch;
            }
        }
        return null;
	}

	public static void main(String[] args) {
		String input = "harshavardhan";
		Character result =findFirstNonRepeatingCharacter(input);
		System.out.println(result);
	}
}
