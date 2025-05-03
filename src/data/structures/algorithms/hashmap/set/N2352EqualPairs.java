package data.structures.algorithms.hashmap.set;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class N2352EqualPairs {
    // Test it
    public static void main(String[] args) {
        N2352EqualPairs obj = new N2352EqualPairs();
        int[][] grid1 = {{3, 2, 1}, {1, 7, 6}, {2, 7, 7}};
        System.out.println(obj.equalPairs(grid1)); // Output: 1

        int[][] grid2 = {{3, 1, 2, 2}, {1, 4, 4, 5}, {2, 4, 2, 2}, {2, 4, 2, 2}};
        System.out.println(obj.equalPairs(grid2)); // Output: 3
    }

    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> rowMap = new HashMap<>();
        int count = 0;

        // Convert each row to a string and count how many times it appears
        for (int i = 0; i < n; i++) {
            String rowKey = Arrays.toString(grid[i]);
            rowMap.put(rowKey, rowMap.getOrDefault(rowKey, 0) + 1);
        }

        // Now go column by column
        for (int col = 0; col < n; col++) {
            int[] colArr = new int[n];
            for (int row = 0; row < n; row++) {
                colArr[row] = grid[row][col];
            }
            String colKey = Arrays.toString(colArr);
            count += rowMap.getOrDefault(colKey, 0);
        }

        return count;
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
Let's break this down step by step in a **simple beginner-friendly way**, and then
I’ll show you how to write this in **Java**.
---
## ✅ Problem Explanation (Beginner Level)
You are given a **square matrix** — meaning it has the same number of rows and columns (n × n).
You need to find how many **pairs** of:
* Row `ri` and
* Column `cj`
...have **the exact same numbers in the same order**.
In simple words:
> You're matching each row with each column to see if they're the same!
---
## 🔍 Example:
```java
Input:
grid = [
  [3, 1, 2, 2],
  [1, 4, 4, 5],
  [2, 4, 2, 2],
  [2, 4, 2, 2]
]
```
Check each row against each column:
* Row 0 = \[3,1,2,2] = Column 0 → ✅
* Row 2 = \[2,4,2,2] = Column 2 → ✅
* Row 3 = \[2,4,2,2] = Column 2 → ✅
Total matches: `3`
---
## 🧑‍💻 Java Code Example
Here’s how you can write this in Java using maps for better efficiency:
```java
import java.util.*;
public class EqualRowColumnPairs {
    public int equalPairs(int[][] grid) {
        int n = grid.length;
        Map<String, Integer> rowMap = new HashMap<>();
        int count = 0;

        // Convert each row to a string and count how many times it appears
        for (int i = 0; i < n; i++) {
            String rowKey = Arrays.toString(grid[i]);
            rowMap.put(rowKey, rowMap.getOrDefault(rowKey, 0) + 1);
        }

        // Now go column by column
        for (int col = 0; col < n; col++) {
            int[] colArr = new int[n];
            for (int row = 0; row < n; row++) {
                colArr[row] = grid[row][col];
            }
            String colKey = Arrays.toString(colArr);
            count += rowMap.getOrDefault(colKey, 0);
        }

        return count;
    }

    // Test it
    public static void main(String[] args) {
        EqualRowColumnPairs obj = new EqualRowColumnPairs();
        int[][] grid1 = {
            {3, 2, 1},
            {1, 7, 6},
            {2, 7, 7}
        };
        System.out.println(obj.equalPairs(grid1)); // Output: 1

        int[][] grid2 = {
            {3, 1, 2, 2},
            {1, 4, 4, 5},
            {2, 4, 2, 2},
            {2, 4, 2, 2}
        };
        System.out.println(obj.equalPairs(grid2)); // Output: 3
    }
}
```
---
## 🧠 How It Works:
1. Convert each **row** to a string like `[3, 1, 2, 2]` and save it in a map.
2. For each **column**, build a similar array and check if that string exists in the map.
3. Count the number of matches.
---
*/