package corejavapractice.collections;

public class InterleavingString {
    public static void main(String[] args) {
        // Test the function with example strings
        String s1 = "aab";
        String s2 = "axy";
        String s3 = "aaxaby";

        System.out.println("Is s3 formed by interleaving s1 and s2? " + isInterleave(s1, s2, s3));
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();

        // If lengths don't match, it's not interleaving
        if (m + n != s3.length()) {
            return false;
        }

        // DP table to store results of subproblems
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Initialize the DP table
        dp[0][0] = true; // Empty strings interleave to form an empty string

        // Fill the table for s1 only
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        // Fill the table for s2 only
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        // Fill the table for the rest
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c3 = s3.charAt(i + j - 1);
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == c3) ||
                           (dp[i][j - 1] && s2.charAt(j - 1) == c3);
            }
        }

        return dp[m][n];
    }

}
