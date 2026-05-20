package corejavapractice.collections;

public class PalindromeCheck {
    public static boolean isPalindrome(int x) {
        // Negative numbers and numbers ending with 0 (except 0 itself) are not palindromes
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }

        int reversed = 0;
        int original = x;

        // Reverse the number
        while (x > 0) {
            int digit = x % 10;
            reversed = reversed * 10 + digit;
            x /= 10;
        }

        // Check if the original number is equal to its reverse
        return original == reversed;
    }

    public static void main(String[] args) {
        int x = 121; // Example input

        boolean result = isPalindrome(x);
        System.out.println("Is " + x + " a palindrome? " + result);
    }

}
