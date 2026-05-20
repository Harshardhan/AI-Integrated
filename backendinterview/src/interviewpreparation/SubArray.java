package interviewpreparation;

public class SubArray {

	public static void main(String[] args) {

		int[] array = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };

		int result = findMaxSubArraySum(array);
		System.out.println("Maximum subarray sum: " + result );
	}

	private static int findMaxSubArraySum(int[] array) {

		int maxSum = Integer.MIN_VALUE;
		int currentSum = 0;


		for (int num : array) {
			currentSum += num;

			if (currentSum > maxSum) {
				maxSum = currentSum;
			}

			if (currentSum < 0) {
				currentSum = 0;
			}
		}
		return maxSum;
	}
}
