package corejavapractice.collections;

public class SingleNumberII {

    // Function to find the single number
    public static int singleNumber(int[] nums) {
        int ones = 0, twos = 0;

        for (int num : nums) {
            // Update "ones" with the XOR of current number and existing "ones"
            // Mask "ones" with "~twos" to remove numbers counted in "twos"
            ones = (ones ^ num) & ~twos;

            // Update "twos" with the XOR of current number and existing "twos"
            // Mask "twos" with "~ones" to remove numbers counted in "ones"
            twos = (twos ^ num) & ~ones;
        }

        return ones; // "ones" holds the single number
    }

    // Main method
    public static void main(String[] args) {
        // Example input
        int[] nums = {2, 2, 3, 2};

        // Find and display the single number
        int result = singleNumber(nums);
        System.out.println("The single number is: " + result);
    }

}
