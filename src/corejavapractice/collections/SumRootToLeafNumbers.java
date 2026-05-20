package corejavapractice.collections;

public class SumRootToLeafNumbers {

    public static int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    // Helper function to perform DFS and calculate the root-to-leaf sum
    private static int dfs(TreeNode node, int currentSum) {
        if (node == null) {
            return 0;
        }

        // Update the current sum for the path
        currentSum = currentSum * 10 + node.val;

        // Check if it's a leaf node
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        // Recursively calculate sum for left and right subtrees
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }

    public static void main(String[] args) {
        // Constructing a sample tree:
        //       1
        //      / \
        //     2   3
        //    / \
        //   4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        // Expected output: 124 + 125 + 13 = 262
        int result = sumNumbers(root);
        System.out.println("Total sum of all root-to-leaf numbers: " + result);
    }

}
