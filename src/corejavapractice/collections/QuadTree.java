package corejavapractice.collections;


class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {
    }

    public Node(boolean val, boolean isLeaf) {
        this.val = val;
        this.isLeaf = isLeaf;
    }

    public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
        this.val = val;
        this.isLeaf = isLeaf;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }
}

public class QuadTree {

    public static void main(String[] args) {
        // Example grid
        int[][] grid = {
            {1, 1, 0, 0},
            {1, 1, 0, 0},
            {0, 0, 1, 1},
            {0, 0, 1, 1}
        };

        // Build Quad-Tree
        Node root = construct(grid);

        // Print result (Serialized Quad-Tree)
        System.out.println(serializeQuadTree(root));
    }

    public static Node construct(int[][] grid) {
        return buildTree(grid, 0, 0, grid.length);
    }

    private static Node buildTree(int[][] grid, int x, int y, int size) {
        if (size == 1) {
            return new Node(grid[x][y] == 1, true);
        }

        int half = size / 2;

        // Recursively build four quadrants
        Node topLeft = buildTree(grid, x, y, half);
        Node topRight = buildTree(grid, x, y + half, half);
        Node bottomLeft = buildTree(grid, x + half, y, half);
        Node bottomRight = buildTree(grid, x + half, y + half, half);

        // If all quadrants are leaves and have the same value, merge them
        if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf
                && topLeft.val == topRight.val && topRight.val == bottomLeft.val && bottomLeft.val == bottomRight.val) {
            return new Node(topLeft.val, true);
        }

        // Otherwise, create an internal node
        return new Node(false, false, topLeft, topRight, bottomLeft, bottomRight);
    }

    // Serialize Quad-Tree to display it in level-order traversal format
    private static String serializeQuadTree(Node root) {
        StringBuilder result = new StringBuilder();
        serializeHelper(root, result);
        return result.toString();
    }

    private static void serializeHelper(Node node, StringBuilder result) {
        if (node == null) {
            result.append("null,");
            return;
        }

        result.append("[")
              .append(node.isLeaf ? 1 : 0).append(",")
              .append(node.val ? 1 : 0).append("],");

        if (!node.isLeaf) {
            serializeHelper(node.topLeft, result);
            serializeHelper(node.topRight, result);
            serializeHelper(node.bottomLeft, result);
            serializeHelper(node.bottomRight, result);
        }
    }
}

