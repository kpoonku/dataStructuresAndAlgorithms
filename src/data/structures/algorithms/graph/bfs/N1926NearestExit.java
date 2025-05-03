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

        int[][] directions = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // down, up, right, left
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

/*
1926. Nearest Exit from Entrance in Maze
You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls
(represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol]
denotes the row and column of the cell you are initially standing at.

In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a
wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance.
An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.

Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no
such path exists.

Example 1:
Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
Output: 1
Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
Initially, you are at the entrance cell [1,2].
- You can reach [1,0] by moving 2 steps left.
- You can reach [0,2] by moving 1 step up.
It is impossible to reach [2,3] from the entrance.
Thus, the nearest exit is [0,2], which is 1 step away.

Example 2:
Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
Output: 2
Explanation: There is 1 exit in this maze at [1,2].
[1,0] does not count as an exit since it is the entrance cell.
Initially, you are at the entrance cell [1,0].
- You can reach [1,2] by moving 2 steps right.
Thus, the nearest exit is [1,2], which is 2 steps away.

Example 3:
Input: maze = [[".","+"]], entrance = [0,0]
Output: -1
Explanation: There are no exits in this maze.

Constraints:
maze.length == m
maze[i].length == n
1 <= m, n <= 100
maze[i][j] is either '.' or '+'.
entrance.length == 2
0 <= entrancerow < m
0 <= entrancecol < n
entrance will always be an empty cell.
 */
/*
Absolutely! Let's explain the problem in **very simple, beginner-level** language, without using code.

---

## 🧭 What Is This Problem About?

You are inside a **maze** made of:

* **Empty paths** you can walk on, shown as a `.` (dot)
* **Walls** that block you, shown as a `+` (plus)

You're given a **starting point**, called the **entrance**.

Your goal is to find the **shortest way out of the maze**, by walking only **up, down, left,
or right** (no diagonals), without walking through walls.

---

## 🚪 What Is an Exit?

An **exit** is any empty space (`.`) that is **on the outer edge** (border) of the maze — like the outside
walls of a building.
But your **starting spot is NOT an exit**, even if it’s on the edge.

---

## 🎯 What Do You Have to Find?

You need to find out:

> How many steps does it take to reach the **closest exit**?

If there is **no way out**, you return **-1**.

---

## 🗺️ How Can You Move?

From any cell, you can try to move:

* **Up**
* **Down**
* **Left**
* **Right**

But you **cannot**:

* Move into walls (`+`)
* Step outside the maze
* Go back to the same place you already visited

---

## ✅ Let’s Understand with a Simple Example:

### Maze:

```
[ "+", "+", "." , "+" ]
[ ".", ".", "." , "+" ]
[ "+", "+", "+" , "." ]
```

### Entrance:

```
[1, 2] → means row 1, column 2 → which is the middle dot in the second row
```

You can move:

* **Left to \[1,1]**
* **Up to \[0,2]** (this is an **exit** — it's a dot on the top edge)

That means: ✅ You can escape in just **1 step**!

---

## 🧠 How to Think About Solving This:

Think of it like this:

1. You are dropped into the maze at the entrance.
2. You try to move in all directions.
3. Every time you move, you mark that spot so you don’t visit it again.
4. You keep moving, one step at a time, like ripples in water.
5. The first time you touch the edge of the maze (except where you started), **that’s your exit**!
6. Count how many steps it took to get there.

This way, you always find the **nearest exit first**, not a farther one.

---

## 🧱 Summary:

* Maze is made of empty spots (`.`) and walls (`+`)
* You start at one empty spot
* Move in 4 directions only (no diagonals)
* Find the **shortest** path to an **exit on the border**
* Return how many steps it takes
* Return `-1` if there’s **no way out**

---
Absolutely! Let's walk through a visual example to better understand how to find the **nearest exit** from the entrance in a maze.
---

## 🧭 Visualizing the Maze

Consider the following maze:

```
+ + . +
. . . +
+ + + .
```

* `+` represents a **wall**.
* `.` represents an **empty path**.
* The **entrance** is at position `[1, 2]` (row 1, column 2).

### Step-by-Step Movement:
1. **Starting Point**: You begin at `[1, 2]`, which is an empty cell (`.`).
2. **Possible Moves**: From your current position, you can move:
   * **Up** to `[0, 2]` (empty path).
   * **Down** to `[2, 2]` (wall).
   * **Left** to `[1, 1]` (empty path).
   * **Right** to `[1, 3]` (wall).
3. **Exploring Up**: Moving up to `[0, 2]` brings you to the top row, which is an edge of the maze. Since it's an empty cell (`.`), this is a valid **exit**.
### Conclusion:
* You reached an exit in **1 step** by moving **up** from `[1, 2]` to `[0, 2]`.
---
## 🧠 Key Takeaways
* **Exit Criteria**: An exit is any empty cell (`.`) located on the **border** of the maze.
* **Movement**: You can move up, down, left, or right, but not diagonally.
* **Objective**: Find the shortest path from the entrance to any exit.
---
 */
