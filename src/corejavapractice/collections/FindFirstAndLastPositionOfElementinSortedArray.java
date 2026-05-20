package corejavapractice.collections;

public class FindFirstAndLastPositionOfElementinSortedArray {

	public static void main(String[] args) {
		int[] nums = { 5, 7, 7, 8, 8, 10 };
		int target = 8;

		int[] result = searchRange(nums, target);
		System.out.println("[" + result[0] + ", " + result[1] + "]");
	}

	public static int[] searchRange(int[] nums, int target) {
		int[] result = { -1, -1 };

		// Find the leftmost (first) index of the target
		result[0] = findBound(nums, target, true);

		// If the leftmost index is -1, the target is not in the array
		if (result[0] == -1) {
			return result;
		}

		// Find the rightmost (last) index of the target
		result[1] = findBound(nums, target, false);

		return result;
	}

	private static int findBound(int[] nums, int target, boolean isFirst) {
		int left = 0;
		int right = nums.length - 1;
		int bound = -1;

		while (left <= right) {
			int mid = left + (right - left) / 2;

			if (nums[mid] == target) {
				bound = mid;
				if (isFirst) {
					// Narrow down to the left part
					right = mid - 1;
				} else {
					// Narrow down to the right part
					left = mid + 1;
				}
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return bound;
	}

}
