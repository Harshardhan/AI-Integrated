package corejavapractice.collections;

import java.util.*;

public class LongestIncreasingSubsequence {

    // Method to find the length of the longest increasing subsequence
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // Every element is a subsequence of length 1

        int maxLength = 1;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        return maxLength;
    }

    // Main method
    public static void main(String[] args) {
        // Example input
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        // Call the lengthOfLIS method and print the result
        int result = lengthOfLIS(nums);
        System.out.println("Length of the longest increasing subsequence: " + result);

        // Additional test cases
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("Length of the longest increasing subsequence: " + lengthOfLIS(nums2));

        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println("Length of the longest increasing subsequence: " + lengthOfLIS(nums3));
    }

}
