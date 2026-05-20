package day3java;

import java.util.HashMap;
import java.util.Map;

public class HashMapPractice {

	public static void main(String[] args) {
		
		Map<Integer, String> map = new HashMap<>();
		
		map.put(0, "Apple");
		map.put(1, "Mango");
		map.put(2, "Sapota");
		map.put(3, "Apple");
		map.put(4, "Apple");
		map.put(2, "Pineapple");
		
		map.remove(1);
		
		System.out.println("HashMap: "+map);
		
		System.out.println("Hashcode of key 0: " + Integer.valueOf(0).hashCode());
		System.out.println("Hashcode of key 1: " + Integer.valueOf(1).hashCode());
		System.out.println("Hashcode of key 2: " + Integer.valueOf(2).hashCode());
		
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
		    System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
		
		
	}
}
