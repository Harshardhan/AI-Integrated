package corejavapractice.collections;

public class PowerFunction {
    public static void main(String[] args) {
        // Example: Replace 'x' and 'n' with your input values
        double x = 2.0; // Base
        int n = 10; // Exponent
        System.out.println(x + " raised to the power of " + n + " is: " + myPow(x, n));
    }

    public static double myPow(double x, int n) {
        if (n == 0) return 1.0;

        long exp = n; // Use long to handle cases where n is Integer.MIN_VALUE
        if (exp < 0) {
            x = 1 / x;
            exp = -exp;
        }

        double result = 1.0;
        double currentProduct = x;

        while (exp > 0) {
            if (exp % 2 == 1) {
                result *= currentProduct;
            }
            currentProduct *= currentProduct;
            exp /= 2;
        }

        return result;
    }

}
