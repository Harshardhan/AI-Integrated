package corejavapractice.collections;

import java.util.Stack;

public class BSTIterator {
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        pushAllLeft(root);
    }

    // Returns the next smallest number in the BST
    public int next() {
        TreeNode node = stack.pop();
        // If the node has a right child, push all its left descendants to the stack
        if (node.right != null) {
            pushAllLeft(node.right);
        }
        return node.val;
    }

    // Returns true if there exists a number in the traversal to the right of the pointer
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    // Helper function to push all left descendants of a node to the stack
    private void pushAllLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        // Construct a sample BST
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        // Initialize BSTIterator
        BSTIterator iterator = new BSTIterator(root);

        // Test the iterator
        System.out.println(iterator.next());    // Output: 3
        System.out.println(iterator.next());    // Output: 7
        System.out.println(iterator.hasNext()); // Output: true
        System.out.println(iterator.next());    // Output: 9
        System.out.println(iterator.hasNext()); // Output: true
        System.out.println(iterator.next());    // Output: 15
        System.out.println(iterator.hasNext()); // Output: true
        System.out.println(iterator.next());    // Output: 20
        System.out.println(iterator.hasNext()); // Output: false
    }

}
