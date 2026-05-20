package corejavapractice.collections;

public class PlusOne {
    public static int[] plusOne(int[] digits) {
        int n = digits.length;

        // Traverse the array from the last digit
        for (int i = n - 1; i >= 0; i--) {
            // If the digit is less than 9, increment it and return the array
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            // If the digit is 9, set it to 0
            digits[i] = 0;
        }

        // If all digits were 9, we need an extra digit at the beginning
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }

    public static void main(String[] args) {
        int[] digits = {9, 9, 9}; // Example input

        int[] result = plusOne(digits);
        System.out.print("Resulting array: ");
        for (int digit : result) {
            System.out.print(digit + " ");
        }
    }

}
