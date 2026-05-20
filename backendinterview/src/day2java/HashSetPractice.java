package day2java;

import java.util.HashSet;
import java.util.Set;

public class HashSetPractice {

	public static void main(String[] args) {
		
		Set<String> set = new HashSet<>();
		
		set.add("Apple");
		set.add("Kiwi");
		set.add("Guava");
		set.add("Mango");
		set.add("Pineapple");
		
		set.remove("Apple");		
		set.add("Sapota");
		set.add("Guava");
		
		System.out.println("HashSet: "+set);
		// HashSet doesnot maintain insertion order and duplicates not allowed.
	}
}
