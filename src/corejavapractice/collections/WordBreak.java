package corejavapractice.collections;

import java.util.*;

public class WordBreak {

    // Method to check if the string can be segmented
    public static boolean wordBreak(String s, List<String> wordDict) {
        // Convert wordDict to a Set for faster lookups
        Set<String> wordSet = new HashSet<>(wordDict);
        
        // Dynamic programming table
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true; // Base case: empty string can always be segmented

        // Fill the DP table
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    // Main method
    public static void main(String[] args) {
        // Example input
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");

        // Call the wordBreak method and print the result
        boolean result = wordBreak(s, wordDict);
        System.out.println("Can the string '" + s + "' be segmented? " + result);

        // Additional test case
        String s2 = "applepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen");
        System.out.println("Can the string '" + s2 + "' be segmented? " + wordBreak(s2, wordDict2));

        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println("Can the string '" + s3 + "' be segmented? " + wordBreak(s3, wordDict3));
    }

}
