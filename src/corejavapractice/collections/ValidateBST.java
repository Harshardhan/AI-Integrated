package corejavapractice.collections;


public class ValidateBST {
    // Helper function to validate BST with range constraints
    private boolean isValidBST(TreeNode node, Long min, Long max) {
        if (node == null) return true;

        // Check if the node value violates the BST property
        if (node.val <= min || node.val >= max) return false;

        // Recursively validate left and right subtrees
        return isValidBST(node.left, min, (long) node.val) &&
               isValidBST(node.right, (long) node.val, max);
    }

    // Main function to be called to validate BST
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Create a sample binary tree
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        // Check if it's a valid BST
        ValidateBST validateBST = new ValidateBST();
        boolean result = validateBST.isValidBST(root);

        // Print the result
        System.out.println("Is the tree a valid BST? " + result);
    }
}
