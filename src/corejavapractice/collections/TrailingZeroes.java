package corejavapractice.collections;

public class TrailingZeroes {
    public static void main(String[] args) {
        // Example: Replace 'n' with your input
        int n = 100; // You can change this value to test other inputs
        System.out.println("Number of trailing zeroes in " + n + "! is: " + trailingZeroes(n));
    }

    public static int trailingZeroes(int n) {
        int count = 0;
        // Count the number of factors of 5 in n!
        while (n >= 5) {
            n /= 5;
            count += n;
        }
        return count;
    }

}
