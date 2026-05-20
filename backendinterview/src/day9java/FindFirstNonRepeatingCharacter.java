package day9java;

public class FindFirstNonRepeatingCharacter {

    public static void main(String[] args) {
        String str = "swiss";
        char result = firstNonRepeatingCharacter(str);
        System.out.println("First non-repeating character: " + result);
    }

    private static char firstNonRepeatingCharacter(String str) {

        int[] freq = new int[26];

        // Step 1: Count frequency
        for (char ch : str.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Step 2: Find first non-repeating
        for (char ch : str.toCharArray()) {
            if (freq[ch - 'a'] == 1) {
                return ch;
            }
        }

        return '\0';
    }
}