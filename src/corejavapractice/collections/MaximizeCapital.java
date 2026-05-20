package corejavapractice.collections;

import java.util.*;

public class MaximizeCapital {
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;

        // Store projects as pairs of (capital, profit) and sort by capital
        PriorityQueue<int[]> minCapitalQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for (int i = 0; i < n; i++) {
            minCapitalQueue.offer(new int[]{capital[i], profits[i]});
        }

        // Max-heap for selecting the most profitable project we can afford
        PriorityQueue<Integer> maxProfitQueue = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < k; i++) {
            // Add all projects that can be started with current capital to the max-profit heap
            while (!minCapitalQueue.isEmpty() && minCapitalQueue.peek()[0] <= w) {
                maxProfitQueue.offer(minCapitalQueue.poll()[1]);
            }

            // If no projects are affordable, break early
            if (maxProfitQueue.isEmpty()) {
                break;
            }

            // Select the most profitable project
            w += maxProfitQueue.poll();
        }

        return w;
    }

    public static void main(String[] args) {
        // Example input
        int k = 2;
        int w = 0;
        int[] profits = {1, 2, 3};
        int[] capital = {0, 1, 1};

        // Output the final maximized capital
        int result = findMaximizedCapital(k, w, profits, capital);
        System.out.println("Final maximized capital: " + result);
    }

}
