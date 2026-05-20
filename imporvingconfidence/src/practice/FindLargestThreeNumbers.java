package practice;

public class FindLargestThreeNumbers {
	public static int findLargest(int a, int b, int c) {
	    if (a >= b && a >= c) return a;
	    if (b >= a && b >= c) return b;
	    return c;
	}


	public static void main(String[] args) {
		
		
		int result = findLargest(5, 7, 6);
		System.out.println("Largest number is: " + result);

	}
	}
