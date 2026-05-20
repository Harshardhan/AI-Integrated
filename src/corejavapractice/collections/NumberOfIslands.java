package corejavapractice.collections;

public class NumberOfIslands {
    // Method to count the number of islands
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int numberOfIslands = 0;

        // Iterate through the grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // If the cell is land ('1'), start a DFS
                if (grid[i][j] == '1') {
                    numberOfIslands++;
                    dfs(grid, i, j);
                }
            }
        }

        return numberOfIslands;
    }

    // DFS to mark all connected land as visited
    private void dfs(char[][] grid, int i, int j) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Boundary check and skip if water ('0') or already visited ('2')
        if (i < 0 || i >= rows || j < 0 || j >= cols || grid[i][j] != '1') return;

        // Mark the current cell as visited
        grid[i][j] = '2';

        // Visit all 4 adjacent cells
        dfs(grid, i - 1, j); // Up
        dfs(grid, i + 1, j); // Down
        dfs(grid, i, j - 1); // Left
        dfs(grid, i, j + 1); // Right
    }

    // Main method to run the program
    public static void main(String[] args) {
        // Sample input grid
        char[][] grid = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };

        // Create an instance and calculate the number of islands
        NumberOfIslands solution = new NumberOfIslands();
        int result = solution.numIslands(grid);

        // Print the result
        System.out.println("Number of islands: " + result);
    }

}
