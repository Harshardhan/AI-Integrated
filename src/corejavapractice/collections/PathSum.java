package corejavapractice.collections;


public class PathSum {

    public static boolean hasPathSum(TreeNode root, int targetSum) {
        // Base case: if the root is null, there's no path
        if (root == null) {
            return false;
        }

        // Check if it's a leaf node and the remaining targetSum equals the node's value
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }

        // Recursively check left and right subtrees with the updated targetSum
        int remainingSum = targetSum - root.val;
        return hasPathSum(root.left, remainingSum) || hasPathSum(root.right, remainingSum);
    }

    public static void main(String[] args) {
        // Create a binary tree for testing
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);

        int targetSum = 22;

        // Test if there is a root-to-leaf path with the given target sum
        boolean result = hasPathSum(root, targetSum);
        System.out.println("Does the tree have a root-to-leaf path with sum " + targetSum + "? " + result);
    }

}
