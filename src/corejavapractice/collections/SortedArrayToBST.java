package corejavapractice.collections;


public class SortedArrayToBST {

    public static TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private static TreeNode helper(int[] nums, int left, int right) {
        // Base case: if the range is invalid, return null
        if (left > right) {
            return null;
        }

        // Choose the middle element as the root
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        // Recursively build the left and right subtrees
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);

        return root;
    }

    // Utility method to print the tree in-order (left-root-right)
    public static void printInOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        printInOrder(root.left);
        System.out.print(root.val + " ");
        printInOrder(root.right);
    }

    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9}; // Example input

        TreeNode root = sortedArrayToBST(nums);

        System.out.println("In-order traversal of the BST:");
        printInOrder(root);
    }

}
