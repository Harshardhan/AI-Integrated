package corejavapractice.collections;

public class Trie {
    private class TrieNode {
        boolean isEndOfWord;
        TrieNode[] children;
        
        public TrieNode() {
            isEndOfWord = false;
            children = new TrieNode[26]; // Each index represents a letter a-z
        }
    }
    
    private TrieNode root;
    
    // Constructor
    public Trie() {
        root = new TrieNode();
    }
    
    // Inserts a word into the trie
    public void insert(String word) {
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
    
    // Returns true if the word is in the trie
    public boolean search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return current.isEndOfWord;
    }
    
    // Returns true if there is any word in the trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return true;
    }
    
    public static void main(String[] args) {
        Trie trie = new Trie();
        
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // Output: true
        System.out.println(trie.search("app"));     // Output: false
        System.out.println(trie.startsWith("app")); // Output: true
        
        trie.insert("app");
        System.out.println(trie.search("app"));     // Output: true
    }

}
