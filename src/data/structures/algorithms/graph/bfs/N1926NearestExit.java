package data.structures.algorithms.graph.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class N1926NearestExit {
    public static void main(String[] args) {
        N1926NearestExit sol = new N1926NearestExit();
        char[][] maze = {{'+', '+', '.', '+'}, {'.', '.', '.', '+'}, {'+', '+', '+', '.'}};
        System.out.println("Minimal Steps : " + sol.nearestExit(maze, new int[]{1, 2}));
    }

    public int nearestExit(char[][] maze, int[] entrance) {
        int rowLength = maze.length;
        int colLength = maze[0].length;

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Set<String> visited = new HashSet<>();
        int sRow = entrance[0];
        int sCol = entrance[1];

        Queue<QValue> queue = new LinkedList<>();
        queue.offer(new QValue(sRow, sCol, 0));
        while (!queue.isEmpty()) {
            QValue node = queue.poll();
            visited.add("" + node.row + node.col);
            for (int[] direction : directions) {
                int row = node.row + direction[0];
                int col = node.col + direction[1];
                if (row >= 0 && row < rowLength
                        && col >= 0 && col < colLength
                        && maze[row][col] != '+'
                        && !visited.contains("" + row + col)) {
                    if (row == 0 || col == 0 || row == rowLength - 1 || col == colLength - 1) {
                        return node.steps + 1;
                    }
                    visited.add("" + row + col);
                    queue.offer(new QValue(row, col, node.steps + 1));
                }
            }
        }

        return -1;
    }

    class QValue {
        int row;
        int col;
        int steps;

        QValue(int row, int col, int steps) {
            this.row = row;
            this.col = col;
            this.steps = steps;
        }
    }
}
