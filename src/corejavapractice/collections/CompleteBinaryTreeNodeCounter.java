package corejavapractice.collections;


public class CompleteBinaryTreeNodeCounter {
    
    public int countNodes(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);

        if (leftDepth == rightDepth) {
            // Left subtree is perfect, and we calculate nodes in it directly
            return (1 << leftDepth) + countNodes(root.right);
        } else {
            // Right subtree is perfect, and we calculate nodes in it directly
            return (1 << rightDepth) + countNodes(root.left);
        }
    }

    // Helper function to get the depth of the tree
    private int getDepth(TreeNode node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.left;
        }
        return depth;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Construct a sample complete binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);

        CompleteBinaryTreeNodeCounter counter = new CompleteBinaryTreeNodeCounter();
        System.out.println("Number of nodes in the complete binary tree: " + counter.countNodes(root)); // Output: 6
    }

}
