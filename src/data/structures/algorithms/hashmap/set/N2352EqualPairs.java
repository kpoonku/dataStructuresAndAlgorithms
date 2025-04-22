package data.structures.algorithms.hashmap.set;

import java.util.ArrayList;
import java.util.List;

public class N2352EqualPairs {
    public static int equalPairs(int[][] grid) {
        int n = grid.length;
        int count = 0;
        List<String> rows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < n; j++) {
                row.append(grid[i][j]).append(",");
            }
            rows.add(row.toString());
        }
        System.out.println(rows);
        for (int j = 0; j < n; j++) {
            StringBuilder col = new StringBuilder();
            for (int i = 0; i < n; i++) {
                col.append(grid[i][j]).append(",");
            }
            System.out.println("Col : " + col);
            if (rows.contains(col.toString())) {
                count++;
            }
            System.out.println(count);
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] grid = {{3, 2, 1}, {1, 7, 6}, {2, 7, 7}};
        System.out.println("Count : " + equalPairs(grid));
        grid = new int[][]{{3, 1, 2, 2}, {1, 4, 4, 5}, {2, 4, 2, 2}, {2, 4, 2, 2}};
        System.out.println("Count : " + equalPairs(grid));
    }
}
/*
Given a 0-indexed n x n integer matrix grid, return the number
of pairs (ri, cj) such that row ri and column cj are equal.

A row and column pair is considered equal if they contain the same
elements in the same order (i.e., an equal array).

Example 1:
Input: grid = [[3,2,1],[1,7,6],[2,7,7]]
Output: 1
Explanation: There is 1 equal row and column pair:
- (Row 2, Column 1): [2,7,7]
Example 2:
Input: grid = [[3,1,2,2],[1,4,4,5],[2,4,2,2],[2,4,2,2]]
Output: 3
Explanation: There are 3 equal row and column pairs:
- (Row 0, Column 0): [3,1,2,2]
- (Row 2, Column 2): [2,4,2,2]
- (Row 3, Column 2): [2,4,2,2]
*/
/*
To solve this problem, we need to find the number of pairs of rows and columns that are equal in terms of their content in the same order.

Steps to Approach:
Iterate over all rows: We can go through each row in the matrix.

Compare each row with every column: For each row, we compare it with every column to check if they are equal.

Count the equal pairs: If a row and column are equal, increment a counter.

Key Points:
We are comparing entire rows with entire columns, and they must be exactly the same (i.e., the same number of elements in the same order).

The time complexity can be high if the matrix is large, since we compare every row with every column.


 */