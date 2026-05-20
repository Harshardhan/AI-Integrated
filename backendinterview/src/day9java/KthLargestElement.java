package day9java;

import java.util.Arrays;

public class KthLargestElement {

		public static int findKthLargest(int[] nums, int k) {
		// Sort the array in descending order
		Arrays.sort(nums);
		// Return the k-1 indexed element from the end
		return nums[nums.length - k];
	}

	public static void main(String[] args) {
		int[] nums = {3, 2, 1, 5, 6, 4};
		int k = 3;
		int result = findKthLargest(nums, k);
		System.out.println("The " + k + "nd largest element is: " + result);
	}
}
