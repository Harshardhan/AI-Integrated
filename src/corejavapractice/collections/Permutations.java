package corejavapractice.collections;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3}; // Example input
        List<List<Integer>> result = permute(nums);
        System.out.println("All permutations: " + result);
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, List<Integer> tempList, List<List<Integer>> result) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList)); // Add a copy of tempList to the result
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tempList.contains(nums[i])) continue; // Skip already used elements
            tempList.add(nums[i]); // Choose
            backtrack(nums, tempList, result); // Explore
            tempList.remove(tempList.size() - 1); // Un-choose
        }
    }

}
