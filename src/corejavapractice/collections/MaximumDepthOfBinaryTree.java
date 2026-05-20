package corejavapractice.collections;

//Definition for a binary tree node.

public class MaximumDepthOfBinaryTree {
 public int maxDepth(TreeNode root) {
     if (root == null) {
         return 0; // Base case: an empty tree has a depth of 0
     }
     
     // Recursive case: 1 (current node) + maximum of left and right subtree depths
     int leftDepth = maxDepth(root.left);
     int rightDepth = maxDepth(root.right);
     
     return 1 + Math.max(leftDepth, rightDepth);
 }

 public static void main(String[] args) {
     // Creating a sample binary tree:
     //      3
     //     / \
     //    9  20
     //       / \
     //      15  7

     TreeNode root = new TreeNode(3);
     root.left = new TreeNode(9);
     root.right = new TreeNode(20);
     root.right.left = new TreeNode(15);
     root.right.right = new TreeNode(7);

     // Calculate maximum depth
     MaximumDepthOfBinaryTree solution = new MaximumDepthOfBinaryTree();
     int maxDepth = solution.maxDepth(root);

     // Print result
     System.out.println("The maximum depth of the binary tree is: " + maxDepth);
 }

}
