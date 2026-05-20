package day9java;

public class SubArrayWithSum {

	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 7, 5 };
		int targetSum = 15;
		int[] result = hasSubArrayWithSum(arr, targetSum);
		System.out.println("Subarray with the given sum found at values: " + result[0] + " to " + result[1]);
	}

	private static int[] hasSubArrayWithSum(int[] arr, int targetSum) {

		int currentSum = 0;
		int left = 0;

		for (int right = 0; right < arr.length; right++) {
			currentSum = currentSum + arr[right];

			while (currentSum > targetSum) {
				currentSum = currentSum - arr[left];
				left++;

			}

			if (currentSum == targetSum) {
				return new int[] { left, right };
			}
		}

		return new int[] { -1, -1 };
	}
}
