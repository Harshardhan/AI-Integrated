package corejavapractice.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            // Iterate through nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                // If it's the rightmost element at this level, add it to the result
                if (i == levelSize - 1) {
                    result.add(node.val);
                }

                // Add child nodes in the queue for next level
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Example binary tree:
        //        1
        //       / \
        //      2   3
        //       \   \
        //        5   4
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        RightSideView solution = new RightSideView();
        List<Integer> rightView = solution.rightSideView(root);

        System.out.println("Right Side View of the Binary Tree: " + rightView);
    }

}
