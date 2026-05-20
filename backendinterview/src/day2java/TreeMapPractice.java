package day2java;


import java.util.Map;
import java.util.TreeMap;

public class TreeMapPractice {

	public static void main(String[] args) {
		
		Map<Integer, String> map = new TreeMap<>();
		
		map.put(0, "Apple");
		map.put(1, "Mango");
		map.put(2, "Sapota");
		map.put(3, "Apple");
		map.put(3, "Kiwi");
		map.put(2, "Pineapple");
		
		map.remove(1);
		System.out.println("TreeMap: "+map);
		
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
		    System.out.println(entry.getKey() + " -> " + entry.getValue());
		}
	}
}
