package practice;

public class PalindromeANumber {

    public static void main(String[] args) {

        int num = 123;
        int original = num;   // Store original number
        int reversed = 0;

        while (num > 0) {
            int digit = num % 10;        // Get last digit
            reversed = reversed * 10 + digit; // Build reversed number
            num = num / 10;              // Remove last digit
        }

        System.out.println("Original number: " + original);
        System.out.println("Reversed number: " + reversed);

        if (original == reversed) {
            System.out.println("The given number is a palindrome.");
        } else {
            System.out.println("The given number is NOT a palindrome.");
        }
    }
}
