package corejavapractice.collections;

public class Word {

    public static boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;

        // Traverse every cell as a potential starting point
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Start backtracking from the current cell
                if (backtrack(board, word, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean backtrack(char[][] board, String word, int row, int col, int index) {
        // If the entire word is matched, return true
        if (index == word.length()) {
            return true;
        }

        // Check boundaries and character match
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] != word.charAt(index)) {
            return false;
        }

        // Mark the cell as visited by altering the character
        char temp = board[row][col];
        board[row][col] = '#'; // Use a special marker to indicate the cell is visited

        // Explore all possible directions: up, down, left, right
        boolean found = backtrack(board, word, row + 1, col, index + 1) ||
                        backtrack(board, word, row - 1, col, index + 1) ||
                        backtrack(board, word, row, col + 1, index + 1) ||
                        backtrack(board, word, row, col - 1, index + 1);

        // Restore the cell's original value for backtracking
        board[row][col] = temp;

        return found;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E', 'E'}
        };

        String word = "ABCCED";
        System.out.println("Word exists: " + exist(board, word)); // Output: true

        word = "SEE";
        System.out.println("Word exists: " + exist(board, word)); // Output: true

        word = "ABCB";
        System.out.println("Word exists: " + exist(board, word)); // Output: false
    }

}
