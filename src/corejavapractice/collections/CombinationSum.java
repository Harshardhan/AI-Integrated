package corejavapractice.collections;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7}; // Example input
        int target = 7;
        List<List<Integer>> result = combinationSum(candidates, target);
        System.out.println("Unique combinations: " + result);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] candidates, int target, int start, List<Integer> tempList, List<List<Integer>> result) {
        if (target < 0) {
            return; // Exceeded the target, stop the exploration
        }
        if (target == 0) {
            result.add(new ArrayList<>(tempList)); // Valid combination found
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            tempList.add(candidates[i]); // Choose the candidate
            backtrack(candidates, target - candidates[i], i, tempList, result); // Explore further with the same candidate
            tempList.remove(tempList.size() - 1); // Un-choose
        }
    }

}
