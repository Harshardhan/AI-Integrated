package day5java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {

	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newCachedThreadPool();
		
		for(int i =1; i<=5; i++) {
			int task = i;
			
			executor.submit(() -> System.out.println("Task: "+task+" executed by "+Thread.currentThread().getName()));
		}
		executor.shutdown();
	}
}
