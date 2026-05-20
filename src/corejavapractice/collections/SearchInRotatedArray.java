package corejavapractice.collections;

import java.util.Scanner;

public class SearchInRotatedArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the size of the array
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();

        int[] nums = new int[n];
        
        // Input the elements of the array
        System.out.println("Enter the elements of the rotated sorted array:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // Input the target
        System.out.print("Enter the target value: ");
        int target = scanner.nextInt();

        // Search for the target in the rotated array
        int result = search(nums, target);
        System.out.println("Index of the target: " + result);

        scanner.close();
    }

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if the middle element is the target
            if (nums[mid] == target) {
                return mid;
            }

            // Determine if the left half is sorted
            if (nums[left] <= nums[mid]) {
                // Target lies in the left sorted portion
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else { // Otherwise, search in the right portion
                    left = mid + 1;
                }
            } else { // Right half must be sorted
                // Target lies in the right sorted portion
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else { // Otherwise, search in the left portion
                    right = mid - 1;
                }
            }
        }

        // Target not found
        return -1;
    }

}
