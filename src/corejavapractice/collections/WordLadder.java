package corejavapractice.collections;

import java.util.*;

public class WordLadder {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        
        int result = ladderLength(beginWord, endWord, wordList);
        System.out.println("Shortest Transformation Sequence Length: " + result);
    }
    
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0; // If endWord is not in the wordList
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level = 1; // To count the transformation steps
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                
                // Try all possible single letter transformations
                for (int j = 0; j < currentWord.length(); j++) {
                    char[] charArray = currentWord.toCharArray();
                    
                    for (char c = 'a'; c <= 'z'; c++) {
                        charArray[j] = c;
                        String transformedWord = new String(charArray);
                        
                        if (transformedWord.equals(endWord)) {
                            return level + 1; // Found the endWord
                        }
                        
                        if (wordSet.contains(transformedWord)) {
                            queue.offer(transformedWord);
                            wordSet.remove(transformedWord); // Remove to avoid revisiting
                        }
                    }
                }
            }
            level++;
        }
        
        return 0; // No transformation sequence found
    }

}
