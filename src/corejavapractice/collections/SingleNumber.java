package corejavapractice.collections;

import java.util.*;

public class SingleNumber {

    // Function to find the single number
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num; // XOR operation
        }
        return result;
    }

    // Main method
    public static void main(String[] args) {
        // Example input
        int[] nums = {4, 1, 2, 1, 2};

        // Find and display the single number
        int result = singleNumber(nums);
        System.out.println("The single number is: " + result);
    }

}
