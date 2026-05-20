package day2java;

import java.util.HashMap;
import java.util.Map;

public class UserAttemptLogin {

	public static void main(String[] args) {
		
		Map<String, Integer> map = new HashMap<>();

		String user = "harsha";

		int attempts = map.getOrDefault(user, 0);
		map.put(user, attempts + 1);
		System.out.println("FaileAttemps: "+map);
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
		    System.out.println(entry.getKey() + " -> " + entry.getValue());
		}

	}
}
