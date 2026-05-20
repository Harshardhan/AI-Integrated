package corejavapractice.collections;

import java.util.*;

public class CoinChange {

    // Method to find the fewest number of coins to make up the given amount
    public static int coinChange(int[] coins, int amount) {
        // DP array to store the minimum coins required for each amount
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // Fill with a value larger than the maximum possible coins
        dp[0] = 0; // Base case: 0 coins are needed to make amount 0

        // Fill the DP array
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        // If dp[amount] is still larger than amount, return -1 (not possible)
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // Main method
    public static void main(String[] args) {
        // Example input
        int[] coins = {1, 2, 5};
        int amount = 11;

        // Call the coinChange method and print the result
        int result = coinChange(coins, amount);
        System.out.println("Fewest number of coins to make amount " + amount + ": " + result);

        // Additional test cases
        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println("Fewest number of coins to make amount " + amount2 + ": " + coinChange(coins2, amount2));

        int[] coins3 = {186, 419, 83, 408};
        int amount3 = 6249;
        System.out.println("Fewest number of coins to make amount " + amount3 + ": " + coinChange(coins3, amount3));
    }

}
