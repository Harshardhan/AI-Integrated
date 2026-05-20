package day2java;

import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetPractice {

	public static void main(String[] args) {
		
		Set<String> set = new LinkedHashSet<>();
		
		set.add("Apple");
		set.add("Kiwi");
		set.add("Guava");
		set.add("Mango");
		set.add("Pineapple");
		
		set.remove("Apple");		
		set.add("Sapota");
		set.add("Guava");

		System.out.println("Linked Hash set: "+set);
		//Linked Hash set maintains insetion order but duplicates not allowed.
	}
}
