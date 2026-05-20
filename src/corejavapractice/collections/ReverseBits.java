package corejavapractice.collections;

public class ReverseBits {

    /**
     * Reverses the bits of a given 32-bit unsigned integer.
     * @param n The input integer.
     * @return The integer with its bits reversed.
     */
    public static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result <<= 1; // Shift result to the left
            result |= (n & 1); // Add the least significant bit of n to result
            n >>= 1; // Shift n to the right
        }
        return result;
    }

    public static void main(String[] args) {
        int input = 0b00000010100101000001111010011100; // Example input
        System.out.println("Reversed: " + Integer.toBinaryString(reverseBits(input)));

        input = 0b11111111111111111111111111111101; // Example input
        System.out.println("Reversed: " + Integer.toBinaryString(reverseBits(input)));
    }

}
