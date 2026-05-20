package corejavapractice.collections;

public class SquareRoot {
    public static void main(String[] args) {
        // Example: Replace 'x' with your input
        int x = 16; // You can change this value to test other inputs
        System.out.println("The square root of " + x + " rounded down is: " + mySqrt(x));
    }

    public static int mySqrt(int x) {
        if (x == 0 || x == 1) return x;

        int left = 0, right = x, result = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid <= x / mid) {
                result = mid; // Store the result as it might be the answer
                left = mid + 1; // Try for a larger value
            } else {
                right = mid - 1; // Try for a smaller value
            }
        }

        return result;
    }

}
