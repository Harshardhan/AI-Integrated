package day2java;

import java.util.Set;
import java.util.TreeSet;

public class TreeSetPractice {

	public static void main(String[] args) {
		
		Set<String> set = new TreeSet<>();
		
		set.add("Apple");
		set.add("Kiwi");
		set.add("Guava");
		set.add("Mango");
		set.add("Pineapple");
		
		set.remove("Apple");		
		set.add("Sapota");
		set.add("Guava");

		System.out.println("Tree Set: "+set);
	}
}
