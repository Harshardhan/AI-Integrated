package day9java;

public class LargestThreeNumbersUsingArrayAndLoop {

	public static void main(String[] args) {

		int[] numbers = { -25, -32, -18, -9, -5, -15 };

		int result = findLargestNumber(numbers);
		System.out.println("Largest number: " + result);
	}

	private static int findLargestNumber(int[] numbers) {
		int largest = Integer.MIN_VALUE;
		

		for (int i = 0; i < numbers.length; i++) {
			if(numbers[i] > largest) {
				largest = numbers[i];
			}
			
		}
		return largest;
	}
}
