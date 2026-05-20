package corejavapractice.collections;

import java.util.ArrayList;
import java.util.List;

public class MinAbsDiffInBST {
    
    public static int getMinimumDifference(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        inOrderTraversal(root, values);
        
        int minDifference = Integer.MAX_VALUE;
        for (int i = 1; i < values.size(); i++) {
            int diff = values.get(i) - values.get(i - 1);
            minDifference = Math.min(minDifference, diff);
        }
        
        return minDifference;
    }
    
    // Helper method for in-order traversal to populate the list with sorted values
    private static void inOrderTraversal(TreeNode node, List<Integer> values) {
        if (node == null) return;
        
        inOrderTraversal(node.left, values);
        values.add(node.val);
        inOrderTraversal(node.right, values);
    }
    
    // Main method to test the code
    public static void main(String[] args) {
        // Example tree:
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        
        int minDifference = getMinimumDifference(root);
        System.out.println("Minimum Absolute Difference: " + minDifference);
    }

}
