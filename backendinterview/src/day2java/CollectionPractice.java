package day2java;

import java.util.ArrayList;
import java.util.List;

public class CollectionPractice {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		
		list.add("Apple");
		list.add("Sapota");
		list.add("Orange");
		list.add("Mango");
		list.add("Guava");
		list.add("Guava");
		list.remove(0);
		list.remove(2);
		
		list.add("Pineapple");
		
		list.remove(0);
		list.add("KIwi");
		list.remove(3);
		long start = System.nanoTime();


		long end = System.nanoTime();

		System.out.println(end-start);
		
		System.out.println("Array List: "+list);
	// If we remove the elements in arraylist. The element will shift and left the elements until the empty index.	
	}
}
