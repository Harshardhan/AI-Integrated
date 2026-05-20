package corejavapractice.collections;

public class MaximumSubarraySum {
    public static int maxSubArray(int[] nums) {
        // Initialize current and maximum sum
        int currentSum = nums[0];
        int maxSum = nums[0];

        // Traverse through the array
        for (int i = 1; i < nums.length; i++) {
            // Update current sum: either add the current number or start a new subarray
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            // Update the maximum sum if needed
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        // Example input
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        // Find and print the maximum subarray sum
        System.out.println("The maximum subarray sum is: " + maxSubArray(nums));
    }

}
