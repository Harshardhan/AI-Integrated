package mayrevision;

public class SecondLargestElement {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5 };

		int largestElement = Integer.MIN_VALUE;

		int secondLargestElement = Integer.MIN_VALUE;

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > largestElement) {
				secondLargestElement = largestElement;
				largestElement = arr[i];
		} else if (arr[i] > secondLargestElement && arr[i] != largestElement) {
				secondLargestElement = arr[i];
			}
		}

		System.out.println("The second largest element is: " + secondLargestElement);
	}
}
