package corejavapractice.collections;

public class NQueens {
    public static void main(String[] args) {
        int n = 8; // Example input
        int result = totalNQueens(n);
        System.out.println("Number of distinct solutions for " + n + "-queens: " + result);
    }

    public static int totalNQueens(int n) {
        int[] count = {0}; // To store the number of solutions
        boolean[] cols = new boolean[n]; // Track columns being attacked
        boolean[] diag1 = new boolean[2 * n - 1]; // Track major diagonals
        boolean[] diag2 = new boolean[2 * n - 1]; // Track minor diagonals

        solve(0, n, cols, diag1, diag2, count);
        return count[0];
    }

    private static void solve(int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2, int[] count) {
        if (row == n) {
            count[0]++; // Found a valid solution
            return;
        }

        for (int col = 0; col < n; col++) {
            int d1 = row - col + n - 1; // Major diagonal index
            int d2 = row + col;         // Minor diagonal index

            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue; // Skip if this position is attacked
            }

            // Place a queen
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;

            solve(row + 1, n, cols, diag1, diag2, count); // Recurse for the next row

            // Remove the queen (backtrack)
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }

}
