package corejavapractice.collections;

public class MaximumCircularSubarraySum {
    public static int maxSubarraySumCircular(int[] nums) {
        int n = nums.length;

        // Step 1: Find the maximum subarray sum using Kadane's algorithm
        int maxSum = kadane(nums);
        
        // Step 2: Find the minimum subarray sum and total sum
        int totalSum = 0;
        for (int i = 0; i < n; i++) {
            totalSum += nums[i];
            nums[i] = -nums[i]; // Invert the elements to find the minimum subarray sum
        }
        // Minimum subarray sum is the negative of the maximum subarray sum of the inverted array
        int minSum = -kadane(nums);
        
        // Step 3: Handle the special case where all elements are negative
        if (totalSum == minSum) {
            return maxSum; // In this case, the circular subarray doesn't contribute
        }
        
        // Step 4: Return the maximum of the non-circular and circular cases
        return Math.max(maxSum, totalSum - minSum);
    }

    // Helper method to calculate maximum subarray sum using Kadane's algorithm
    private static int kadane(int[] nums) {
        int currentSum = nums[0], maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        // Example input
        int[] nums = {5, -3, 5};

        // Find and print the maximum circular subarray sum
        System.out.println("The maximum circular subarray sum is: " + maxSubarraySumCircular(nums));
    }

}
