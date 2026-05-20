package day9java;

public class FibonacciSeriesUsingRecursion {

	public static void main(String[] args) {
		
		int number = 11;
		
		for(int i = 0; i < number; i++) {
		System.out.println(fibonacci(i)+" ");
	}

	}
	private static int fibonacci(int number) {

		if(number <= 1) return number;
		return fibonacci(number - 1)+ fibonacci(number - 2);
	}
}
