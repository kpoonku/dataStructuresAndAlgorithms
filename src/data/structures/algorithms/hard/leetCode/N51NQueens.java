package data.structures.algorithms.hard.leetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class N51NQueens {
    public static void main(String[] args) {
        N51NQueens sol = new N51NQueens();
        System.out.println( " Result : " + sol.solveNQueens(4));
    }
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        // stack holds the column position for each row

        int row = 0;

        // Start from column 0 in the first row
        int[] colPositions = new int[n];
        // store the column index to try for each row
        Arrays.fill(colPositions, 0);

        while (true) {
            if (row == n) {
                // All rows are filled, we found a solution
                result.add(generateBoard(stack, n));
                // Backtrack
                row--;
                if (stack.isEmpty()) break;
                int lastCol = stack.pop();
                colPositions[row] = lastCol + 1;
                continue;
            }

            boolean placed = false;
            for (int col = colPositions[row]; col < n; col++) {
                if (isValid(stack, row, col)) {
                    stack.push(col);
                    colPositions[row] = col + 1;
                    // next time, try next column
                    row++;
                    placed = true;
                    break;
                }
            }

            if (!placed) {
                // Could not place queen, need to backtrack
                if (stack.isEmpty()) break;
                row--;
                int lastCol = stack.pop();
                colPositions[row] = lastCol + 1;
                // try next column on backtrack
            }
        }

        return result;
    }

    // Helper: check if a queen can be placed at (row, col)
    private boolean isValid(Stack<Integer> stack, int row, int col) {
        int r = 0;
        for (int c : stack) {
            if (c == col || Math.abs(c - col) == Math.abs(r - row)) {
                return false;
                // same column or diagonal
            }
            r++;
        }
        return true;
    }

    // Convert stack of queen positions into a board representation
    private List<String> generateBoard(Stack<Integer> stack, int n) {
        List<String> board = new ArrayList<>();
        for (int col : stack) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[col] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
/*
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no
two queens attack each other.

Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer
in any order.

Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.'
both indicate a queen and an empty space, respectively.

Example 1:
Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above
Example 2:
Input: n = 1
Output: [["Q"]]
 */