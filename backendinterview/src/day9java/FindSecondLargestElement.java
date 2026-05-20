package day9java;

public class FindSecondLargestElement {

	public static void main(String[] args) {

		int[] array = { -2,5,-1,-3,-4};

		int result = secondLargestElementInteger(array);

		System.out.println("Find second largest element in a given integer: " + result);

	}

private static int secondLargestElementInteger(int[] array) {
	int largest = Integer.MIN_VALUE;
	int secondLargest = Integer.MIN_VALUE;
	
	for (int i = 0; i < array.length; i++) {
		if (array[i] > largest) {
			secondLargest = largest;
			largest = array[i];
		} else if (array[i] > secondLargest && array[i] != largest) {
			secondLargest = array[i];
		}
	}
	return secondLargest;
}

}