package day9java;

public class SubArray {

	public static void main(String[] args) {
		
		int[] arr = {1, 2, 3, 7, 5};
		int targetSum = 12;
		
		int[] result = findSubArrayWithTargetSum(arr, targetSum);
		
		if (result != null) {
			System.out.println("Sub-array with target sum " + targetSum + ": [" + result[0] + ", " + result[1] + "]");
		} else {
			System.out.println("No sub-array found with target sum " + targetSum);
		}
	}

	private static int[] findSubArrayWithTargetSum(int[] arr, int targetSum) {

		int currentSum = 0;
		int start = 0;
		for (int end = 0; end < arr.length; end++) {
			currentSum += arr[end];
			
			while (currentSum > targetSum && start <= end) {
				currentSum -= arr[start];
				start++;
			}
			
			if (currentSum == targetSum) {
				return new int[] {start, end};
			}
		}
		return null;
	}
}
