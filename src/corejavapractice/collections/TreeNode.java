package corejavapractice.collections;

public class TreeNode {

	int val;
	TreeNode left;
	TreeNode right;

    TreeNode(int x) {
        val = x;
    }

	TreeNode(int val, int x) {
		this.val = val;
		this.left = null;
		this.right = null;
	}
}
