package data.structures.algorithms.oracle.tree;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3}, {3, 4, 5}, {6, 7, 8}};
        System.out.println(" Spiral Order of matrix : " + SpiralOrder.spiralOrder(matrix));
        System.out.println(" Spiral Order of matrix - try : " + SpiralOrder.spiralOrderTry(matrix));
    }

    public static List<Integer> spiralOrderTry(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int rows = matrix.length, cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int[] rowDir = {0, 1, 0, -1};
        int[] colDir = {1, 0, -1, 0};
        int nextRow = 0, nextCol = 0, row = 0, col = 0, dir = 0;

        for (int i = 0; i < rows * cols; i++) {
            result.add(matrix[row][col]);
            visited[row][col] = true;

            nextRow = row + rowDir[dir];
            nextCol = col + colDir[dir];
            if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols || visited[nextRow][nextCol]) {
                dir = (dir + 1) % 4;
                nextRow = row + rowDir[dir];
                nextCol = col + colDir[dir];
            }
            row = nextRow;
            col = nextCol;
        }
        return result;
    }

    public static List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[] rowDir = {0, 1, 0, -1};
        int[] colDir = {1, 0, -1, 0};
        List<Integer> result = new ArrayList<>();
        boolean[][] visited = new boolean[rows][cols];
        int row = 0, col = 0, dir = 0;

        for (int i = 0; i < rows * cols; i++) {
            result.add(matrix[row][col]);
            visited[row][col] = true;

            int nextRow = row + rowDir[dir], nextCol = col + colDir[dir];
            if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols || visited[nextRow][nextCol]) {
                dir = (dir + 1) % 4;
                nextRow = row + rowDir[dir];
                nextCol = col + colDir[dir];
            }
            row = nextRow;
            col = nextCol;
        }
        return result;
    }
}

