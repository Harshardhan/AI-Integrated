package corejavapractice.collections;

	
	import java.util.*;

	public class WordDictionary {
	    private class TrieNode {
	        TrieNode[] children;
	        boolean isEndOfWord;

	        public TrieNode() {
	            children = new TrieNode[26]; // Each index represents a letter a-z
	            isEndOfWord = false;
	        }
	    }

	    private TrieNode root;

	    // Constructor
	    public WordDictionary() {
	        root = new TrieNode();
	    }

	    // Add a word to the data structure
	    public void addWord(String word) {
	        TrieNode current = root;
	        for (char ch : word.toCharArray()) {
	            int index = ch - 'a';
	            if (current.children[index] == null) {
	                current.children[index] = new TrieNode();
	            }
	            current = current.children[index];
	        }
	        current.isEndOfWord = true;
	    }

	    // Search for a word in the data structure
	    public boolean search(String word) {
	        return searchInNode(word, 0, root);
	    }

	    private boolean searchInNode(String word, int index, TrieNode node) {
	        if (node == null) {
	            return false;
	        }

	        if (index == word.length()) {
	            return node.isEndOfWord;
	        }

	        char ch = word.charAt(index);

	        if (ch == '.') {
	            // Try all possible children
	            for (TrieNode child : node.children) {
	                if (child != null && searchInNode(word, index + 1, child)) {
	                    return true;
	                }
	            }
	            return false;
	        } else {
	            int childIndex = ch - 'a';
	            return searchInNode(word, index + 1, node.children[childIndex]);
	        }
	    }

	    public static void main(String[] args) {
	        WordDictionary wordDictionary = new WordDictionary();

	        // Add words to the dictionary
	        wordDictionary.addWord("bad");
	        wordDictionary.addWord("dad");
	        wordDictionary.addWord("mad");

	        // Search words in the dictionary
	        System.out.println(wordDictionary.search("pad")); // Output: false
	        System.out.println(wordDictionary.search("bad")); // Output: true
	        System.out.println(wordDictionary.search(".ad")); // Output: true
	        System.out.println(wordDictionary.search("b..")); // Output: true
	    }
	

}
