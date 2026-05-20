package corejavapractice.collections;


public class KthSmallestInBST {
    
    private static int count = 0;
    private static int result = -1;
    
    public static int kthSmallest(TreeNode root, int k) {
        count = 0; // Reset the count for each function call
        result = -1; // Reset the result for each function call
        inOrder(root, k);
        return result;
    }
    
    private static void inOrder(TreeNode node, int k) {
        if (node == null) {
            return;
        }
        
        // Traverse the left subtree
        inOrder(node.left, k);
        
        // Increment the count and check if this is the kth smallest element
        count++;
        if (count == k) {
            result = node.val;
            return;
        }
        
        // Traverse the right subtree
        inOrder(node.right, k);
    }
    
    // Main method to test the code
    public static void main(String[] args) {
        // Example BST:
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.left.left.left = new TreeNode(1);
        
        int k = 3;
        int kthSmallestValue = kthSmallest(root, k);
        System.out.println("The " + k + "-th smallest value is: " + kthSmallestValue);
    }

}
