package corejavapractice.collections;

import java.util.Scanner;

public class SearchMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input matrix dimensions
        System.out.print("Enter the number of rows (m): ");
        int m = scanner.nextInt();
        System.out.print("Enter the number of columns (n): ");
        int n = scanner.nextInt();

        int[][] matrix = new int[m][n];
        
        // Input matrix elements
        System.out.println("Enter the elements of the matrix (row-wise):");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Input target
        System.out.print("Enter the target value: ");
        int target = scanner.nextInt();
        
        // Search the target in the matrix
        boolean result = searchMatrix(matrix, target);
        System.out.println("Is the target present in the matrix? " + result);

        scanner.close();
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int left = 0, right = m * n - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int midValue = matrix[mid / n][mid % n]; // Map 1D index to 2D index

            if (midValue == target) {
                return true;
            } else if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
    }

}
