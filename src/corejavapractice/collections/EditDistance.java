package corejavapractice.collections;

public class EditDistance {
    public static void main(String[] args) {
        // Test the function with example inputs
        String word1 = "horse";
        String word2 = "ros";

        System.out.println("Minimum operations to convert word1 to word2: " + minDistance(word1, word2));
    }

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // DP table to store minimum operations
        int[][] dp = new int[m + 1][n + 1];

        // Fill the base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Deleting all characters from word1
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Inserting all characters into word1
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // No operation needed
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], // Replace
                                Math.min(dp[i - 1][j],     // Delete
                                         dp[i][j - 1]));   // Insert
                }
            }
        }

        return dp[m][n];
    }
}
