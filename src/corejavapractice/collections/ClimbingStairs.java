package corejavapractice.collections;

public class ClimbingStairs {

    public static int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int first = 1;
        int second = 2;

        for (int i = 3; i <= n; i++) {
            int current = first + second;
            first = second;
            second = current;
        }

        return second;
    }

    public static void main(String[] args) {
        int n = 10; // Example input
        System.out.println("Number of distinct ways to climb to the top: " + climbStairs(n));
    }

}
