package corejavapractice.collections;


public class MaxPathSum {
    private static int maxSum = Integer.MIN_VALUE;

    public static int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE; // reset for each call to handle multiple test cases
        maxGain(root);
        return maxSum;
    }

    // Helper function to calculate the maximum gain from each node
    private static int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Recursively calculate the maximum gain from the left and right subtrees
        int leftGain = Math.max(maxGain(node.left), 0); // Ignore paths with negative sums
        int rightGain = Math.max(maxGain(node.right), 0);

        // Path with the current node as the highest node
        int currentPathSum = node.val + leftGain + rightGain;

        // Update the global maximum path sum if needed
        maxSum = Math.max(maxSum, currentPathSum);

        // Return the maximum gain if continuing the path from the current node
        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        // Create a sample binary tree:
        //       -10
        //       /  \
        //      9   20
        //         /  \
        //        15   7
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        // Expected output: 42 (the path is 15 -> 20 -> 7)
        int result = maxPathSum(root);
        System.out.println("Maximum path sum: " + result);
    }

}
