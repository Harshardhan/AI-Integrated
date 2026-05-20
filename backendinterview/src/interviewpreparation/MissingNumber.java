package interviewpreparation;

public class MissingNumber {

	public static void main(String[] args) {

		int[] array = { 0, 1, 2, 4, 5 }; // Missing number is 3
		int missingNumber = findMissingNumber(array);
		System.out.println("The missing number is: " + missingNumber);
	}

	private static int findMissingNumber(int[] array) {
		int n = array.length; // Since one number is missing, the length is n
		int expectedSum = n * (n + 1) / 2; // Sum of first n natural numbers
		int actualSum = 0;
		for (int num : array) {
			actualSum += num; // Calculate the sum of the given numbers
		}
		return expectedSum - actualSum; // The difference is the missing number
}
}