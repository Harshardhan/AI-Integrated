package corejavapractice.collections;

import java.util.*;

public class LetterCombinations {
    public static List<String> letterCombinations(String digits) {
        // Map of digits to corresponding letters
        String[] mapping = {
            "",    // 0
            "",    // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs",// 7
            "tuv", // 8
            "wxyz" // 9
        };
        
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) return result;
        
        backtrack(result, new StringBuilder(), digits, 0, mapping);
        return result;
    }

    private static void backtrack(List<String> result, StringBuilder current, String digits, int index, String[] mapping) {
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        
        String letters = mapping[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            current.append(c);
            backtrack(result, current, digits, index + 1, mapping);
            current.deleteCharAt(current.length() - 1); // Backtrack
        }
    }

    public static void main(String[] args) {
        // Example Input
        String digits = "23";
        System.out.println("Letter Combinations: " + letterCombinations(digits));
    }

}
