package data.structures.algorithms.oracle;

import static java.lang.Math.min;

public class MinPathSum {
    public static int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int[][] dp = new int[row][col];

        dp[0][0] = grid[0][0];

        //for first row
        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i - 1] + grid[0][i];
            System.out.println("dp[0][" + i + "]" + dp[0][i]);
        }

        //for first column
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
            System.out.println("dp[" + i + "][0]"   + dp[i][0]);
        }

        // for the rest of the cells
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                System.out.println("dp[" + i + "][" + j + "]" + dp[i][j]);
            }
        }

        return dp[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int[][] grid1 = {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println("Minimum Path Sum: " + minPathSum(grid1));  // Output: 7

        // Example 2:
        int[][] grid2 = {{1, 2, 3}, {4, 5, 6}};
        System.out.println("Minimum Path Sum: " + minPathSum(grid2));
    }
}
/*
64. Minimum Path Sum
Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
 which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

Example 1:
Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.

Example 2:
Input: grid = [[1,2,3],[4,5,6]]
Output: 12

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 200
 */
