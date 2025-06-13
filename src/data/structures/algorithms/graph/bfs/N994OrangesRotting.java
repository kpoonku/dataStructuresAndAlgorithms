package data.structures.algorithms.graph.bfs;

import java.util.Deque;
import java.util.LinkedList;

public class N994OrangesRotting {
    public static int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int freshOranges = 0; // Counter for fresh oranges
        Deque<int[]> queue = new LinkedList<>(); // Queue to store rotten oranges' positions

        // Preprocess grid: count fresh oranges and add rotten ones to the queue
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j}); // Rotten orange
                } else if (grid[i][j] == 1) {
                    freshOranges++; // Fresh orange
                }
            }
        }
        // down, up, right, left
        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        int minutesElapsed = 0; // Counter for minutes

        // BFS to rot the fresh oranges
        while (!queue.isEmpty() && freshOranges > 0) {
            minutesElapsed++; // Increase time for each BFS level
            int size = queue.size(); // Number of rotten oranges at current time

            for (int i = 0; i < size; ++i) {
                int[] current = queue.poll(); // Get the current rotten orange position
                for (int[] direction : directions) {
                    assert current != null;
                    int x = current[0] + direction[0];
                    int y = current[1] + direction[1];

                    // Check if the adjacent position is within bounds and fresh
                    if (x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 1) {
                        grid[x][y] = 2; // Rot the fresh orange
                        freshOranges--; // Decrease fresh orange count
                        queue.offer(new int[]{x, y}); // Add to queue for next rot
                    }
                }
            }
        }

        // If there are still fresh oranges,
        // return -1, else return the elapsed minutes
        return freshOranges == 0 ? minutesElapsed : -1;
    }

    public static void main(String[] args) {
        int[][] grid = {{2, 1, 0}, {1, 1, 0}, {1, 0, 0}};
        int count = orangesRotting(grid);
        System.out.println(count);
        grid = new int[][] {{2,1,1}, {0,1,1}, {1, 0, 1}};
        count = orangesRotting(grid);
        System.out.println(count);
    }
}
/*
You are given an m x n grid where each cell can have one of three values:
0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange.
If this is impossible, return -1.

Example 1:
Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Example 2:
Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten,
 because rotting only happens 4-directionally.

Example 3:
Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
*/
/*
Absolutely! Let’s break this down so it’s **super beginner-friendly**, even if you're just starting out with coding and don’t know much about algorithms yet.

---

## 🍊 Problem in Simple Words:

You’re given a **box of oranges** arranged in rows and columns (a 2D grid).

Each cell can have:

* `0` → an empty space (no orange)
* `1` → a fresh orange 🍊 (good, not rotten)
* `2` → a rotten orange 🤢 (already bad)

### 🦠 What Happens:

* Every **minute**, the **rotten oranges infect** any **fresh oranges** that are **next to them (up, down, left, or right)**.
* This **keeps happening every minute** until either:

  * All fresh oranges are rotten, or
  * Some fresh oranges are **stuck** and can never be reached.

Your job is to figure out:

> 🕒 How many minutes it takes to rot **all the fresh oranges** — or return `-1` if it’s **impossible**.

---

## 🎨 Visual Example:

```
Input: [[2,1,1],
        [1,1,0],
        [0,1,1]]

Time 0:
[2,1,1]
[1,1,0]
[0,1,1]

Time 1:
[2,2,1]
[2,1,0]
[0,1,1]

Time 2:
[2,2,2]
[2,2,0]
[0,1,1]

Time 3:
[2,2,2]
[2,2,0]
[0,2,1]

Time 4:
[2,2,2]
[2,2,0]
[0,2,2] → all fresh are rotten ✅

Answer: 4 minutes
```

---

## 🧠 Simple Plan (Step by Step)

Think of this like a **wave** spreading from the rotten oranges.

### Step 1: Find all rotten oranges

* Put their positions in a list (like a to-do list for spreading rot)

### Step 2: Count how many fresh oranges are in the grid

### Step 3: Start a timer (minutes = 0)

### Step 4: While there are rotten oranges left to process:

* Go through each rotten orange
* Check **up, down, left, right** — if there’s a fresh orange 🍊, rot it 🤢
* Add those new rotten oranges to the list for the next minute
* Increase the timer (only if we rotted new ones)

### Step 5: When you're done:

* If all oranges are rotten → return how many minutes it took
* If some fresh oranges couldn’t be rotted → return `-1`

---

## 🧑‍💻 Code You Can Understand (Java)

```java
import java.util.*;

public class RottenOranges {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int fresh = 0;

        // A queue to keep track of rotten oranges
        Queue<int[]> queue = new LinkedList<>();

        // 1. Count fresh and store all rotten positions
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j}); // add rotten orange
                } else if (grid[i][j] == 1) {
                    fresh++; // count fresh oranges
                }
            }
        }

        // If no fresh oranges, return 0
        if (fresh == 0) return 0;

        int minutes = 0;

        // Directions to move (up, down, left, right)
        int[][] directions = {{-1,0}, {1,0}, {0,-1}, {0,1}};

        // 2. Spread rot each minute
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean rottedThisMinute = false;

            for (int i = 0; i < size; i++) {
                int[] pos = queue.poll();
                int r = pos[0], c = pos[1];

                for (int[] dir : directions) {
                    int newRow = r + dir[0];
                    int newCol = c + dir[1];

                    // Check if it's in bounds and a fresh orange
                    if (newRow >= 0 && newRow < rows &&
                        newCol >= 0 && newCol < cols &&
                        grid[newRow][newCol] == 1) {

                        grid[newRow][newCol] = 2; // rot it
                        fresh--; // one less fresh orange
                        queue.offer(new int[]{newRow, newCol});
                        rottedThisMinute = true;
                    }
                }
            }

            if (rottedThisMinute) {
                minutes++; // we only count the minute if something rotted
            }
        }

        // 3. Final check
        return fresh == 0 ? minutes : -1;
    }
}
```

---

## 🧪 Try It Out:

```java
public class Main {
    public static void main(String[] args) {
        RottenOranges ro = new RottenOranges();

        int[][] grid1 = {
            {2,1,1},
            {1,1,0},
            {0,1,1}
        };
        System.out.println(ro.orangesRotting(grid1)); // Output: 4

        int[][] grid2 = {
            {2,1,1},
            {0,1,1},
            {1,0,1}
        };
        System.out.println(ro.orangesRotting(grid2)); // Output: -1

        int[][] grid3 = {
            {0,2}
        };
        System.out.println(ro.orangesRotting(grid3)); // Output: 0
    }
}
```

---

## 🧠 Key Concepts You Used:

* 2D arrays (grids)
* Loops
* Queues (to process things step by step)
* BFS (exploring neighbors)
* Timer logic

Would you like a small diagram or animation-like explanation of how the rot spreads?

 */