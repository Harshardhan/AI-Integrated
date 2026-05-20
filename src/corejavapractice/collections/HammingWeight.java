package corejavapractice.collections;

public class HammingWeight {
    
    // Function to count the number of set bits in the binary representation of n
    public static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            count += n & 1; // Check the last bit
            n >>= 1; // Right shift n by 1 bit
        }
        return count;
    }

    // Main method
    public static void main(String[] args) {
        // Example input
        int n = 29; // Binary representation of 29 is 11101

        // Calculate and display the number of set bits
        int result = countSetBits(n);
        System.out.println("The number of set bits in " + n + " is: " + result);
    }

}
