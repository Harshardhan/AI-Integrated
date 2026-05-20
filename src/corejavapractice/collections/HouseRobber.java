package corejavapractice.collections;

public class HouseRobber {

    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        int prev1 = 0; // Max money robbed up to the house before the last
        int prev2 = 0; // Max money robbed up to the house before that

        for (int num : nums) {
            int temp = prev1;
            prev1 = Math.max(prev1, prev2 + num);
            prev2 = temp;
        }

        return prev1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1}; // Example input
        System.out.println("Maximum amount of money you can rob: " + rob(nums));
    }

}
