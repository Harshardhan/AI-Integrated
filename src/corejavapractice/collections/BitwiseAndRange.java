package corejavapractice.collections;

public class BitwiseAndRange {
    public static int rangeBitwiseAnd(int left, int right) {
        // Shift both numbers to the right until they are equal
        // The number of shifts corresponds to the common prefix of binary representation
        int shift = 0;
        while (left < right) {
            left >>= 1;
            right >>= 1;
            shift++;
        }
        // Shift the result back to the left to restore its position
        return left << shift;
    }

    public static void main(String[] args) {
        int left = 5; // Example input
        int right = 7; // Example input

        int result = rangeBitwiseAnd(left, right);
        System.out.println("Bitwise AND of range [" + left + ", " + right + "] is: " + result);
    }

}
