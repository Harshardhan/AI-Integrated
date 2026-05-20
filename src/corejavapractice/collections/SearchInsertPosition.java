package corejavapractice.collections;

public class SearchInsertPosition {
    public static int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoid overflow
            if (nums[mid] == target) {
                return mid; // Target found
            } else if (nums[mid] < target) {
                left = mid + 1; // Move right
            } else {
                right = mid - 1; // Move left
            }
        }
        // Return the position where target should be inserted
        return left;
    }

    public static void main(String[] args) {
        // Example input
        int[] nums = {1, 3, 5, 6};
        int target = 5;

        // Find and print the index or insertion point
        System.out.println("The target position is: " + searchInsert(nums, target));

        // Test case where target is not in the array
        target = 2;
        System.out.println("The target position is: " + searchInsert(nums, target));

        // Test case where target is larger than all elements
        target = 7;
        System.out.println("The target position is: " + searchInsert(nums, target));

        // Test case where target is smaller than all elements
        target = 0;
        System.out.println("The target position is: " + searchInsert(nums, target));
    }

}
