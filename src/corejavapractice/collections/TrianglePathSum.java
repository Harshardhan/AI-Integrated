package corejavapractice.collections;

import java.util.List;
import java.util.ArrayList;

public class TrianglePathSum {
    public static void main(String[] args) {
        // Example triangle
        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(List.of(2));
        triangle.add(List.of(3, 4));
        triangle.add(List.of(6, 5, 7));
        triangle.add(List.of(4, 1, 8, 3));

        int result = minimumTotal(triangle);
        System.out.println("Minimum Path Sum: " + result);
    }

    public static int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        // Create a DP array to store the minimum path sums
        int[] dp = new int[n];
        
        // Initialize DP array with the last row of the triangle
        for (int i = 0; i < n; i++) {
            dp[i] = triangle.get(n - 1).get(i);
        }
        
        // Bottom-up calculation
        for (int row = n - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                dp[col] = triangle.get(row).get(col) + Math.min(dp[col], dp[col + 1]);
            }
        }
        
        // The top element now contains the minimum path sum
        return dp[0];
    }

}
