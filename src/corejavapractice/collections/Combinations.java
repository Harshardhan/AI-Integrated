package corejavapractice.collections;

import java.util.*;

public class Combinations {
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), 1, n, k);
        return result;
    }

    private static void backtrack(List<List<Integer>> result, List<Integer> current, int start, int n, int k) {
        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i <= n; i++) {
            current.add(i); // Choose
            backtrack(result, current, i + 1, n, k); // Explore
            current.remove(current.size() - 1); // Unchoose
        }
    }

    public static void main(String[] args) {
        int n = 4; // Example input
        int k = 2;

        List<List<Integer>> combinations = combine(n, k);
        System.out.println("All Combinations: " + combinations);
    }

}
