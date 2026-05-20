package corejavapractice.collections;

public class FindMinimumInRotatedSortedArray {

	public static void main(String[] args) {
		int[] nums = { 4, 5, 6, 7, 0, 1, 2 };
		int min = findMin(nums);
		System.out.println("Minimum element: " + min);
	}

	public static int findMin(int[] nums) {
		int left = 0;
		int right = nums.length - 1;

		while (left < right) {
			int mid = left + (right - left) / 2;

			// If mid element is greater than the rightmost element, the minimum is in the
			// right half
			if (nums[mid] > nums[right]) {
				left = mid + 1;
			} else { // Otherwise, the minimum is in the left half (including mid)
				right = mid;
			}
		}

		// At the end of the loop, left == right, pointing to the minimum element
		return nums[left];
	}

}
