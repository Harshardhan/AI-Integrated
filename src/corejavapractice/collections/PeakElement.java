package corejavapractice.collections;

import java.util.Scanner;

public class PeakElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the size of the array
        System.out.print("Enter the size of the array: ");
        int n = scanner.nextInt();

        int[] nums = new int[n];
        
        // Input the elements of the array
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // Find the peak element index
        int peakIndex = findPeakElement(nums);
        System.out.println("Index of a peak element: " + peakIndex);

        scanner.close();
    }

    public static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // Compare mid with its right neighbor
            if (nums[mid] > nums[mid + 1]) {
                // Peak lies on the left side (including mid)
                right = mid;
            } else {
                // Peak lies on the right side
                left = mid + 1;
            }
        }

        // At the end of the loop, left == right, pointing to a peak
        return left;
    }

}
