package day2java;

import java.util.LinkedList;
import java.util.List;

public class LinkedListPractice {

	public static void main(String[] args) {
		
	List<String> list = new LinkedList<>();
	
	list.add("Apple");
	list.add("Sapota");
	list.add("Orange");
	list.add("Mango");
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
	
	System.out.println("LinkedList: "+list);
	
	// when we are frequently insertion or delete in middle. then we should go LinkedList.
	}
}
