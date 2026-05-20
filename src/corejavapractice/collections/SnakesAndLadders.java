package corejavapractice.collections;

import java.util.*;

public class SnakesAndLadders {
    public static int snakesAndLadders(int[][] board) {
        int n = board.length;
        int[] flattenedBoard = new int[n * n + 1];
        boolean isLeftToRight = true;

        // Flatten the board
        int index = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (isLeftToRight) {
                for (int j = 0; j < n; j++) {
                    flattenedBoard[index++] = board[i][j];
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    flattenedBoard[index++] = board[i][j];
                }
            }
            isLeftToRight = !isLeftToRight;
        }

        // BFS for the shortest path
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0}); // Start from square 1 with 0 moves
        boolean[] visited = new boolean[n * n + 1];
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int square = current[0];
            int moves = current[1];

            if (square == n * n) {
                return moves; // Reached the last square
            }

            for (int nextSquare = square + 1; nextSquare <= Math.min(square + 6, n * n); nextSquare++) {
                int destination = (flattenedBoard[nextSquare] == -1) ? nextSquare : flattenedBoard[nextSquare];
                if (!visited[destination]) {
                    visited[destination] = true;
                    queue.offer(new int[]{destination, moves + 1});
                }
            }
        }

        return -1; // Not possible to reach the last square
    }

    public static void main(String[] args) {
        int[][] board = {
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 35, -1, -1, 13, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 15, -1, -1, -1, -1}
        };

        int result = snakesAndLadders(board);
        if (result != -1) {
            System.out.println("Minimum number of dice rolls required: " + result);
        } else {
            System.out.println("It is not possible to reach the last square.");
        }
    }

}
