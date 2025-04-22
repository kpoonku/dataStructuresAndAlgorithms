package data.structures.algorithms.graph.dfs;

import java.util.*;

public class N1466MinReorder {
    public static void main(String[] args) {
        int[][] connections1 = {{0,1},{1,3},{2,3},{4,0},{4,5}};
        System.out.println(minReorder(6, connections1)); // Output: 3

        int[][] connections2 = {{1,0},{1,2},{3,2},{3,4}};
        System.out.println(minReorder(5, connections2)); // Output: 2
    }

    public static int minReorder(int n, int[][] connections) {
        int reOrderCount = 0;
        int capitolCity = 0;
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] route : connections) {
            graph.putIfAbsent(route[0], new ArrayList<>());
            graph.get(route[0]).add(new int[]{route[1], 1});
            graph.putIfAbsent(route[1], new ArrayList<>());
            graph.get(route[1]).add(new int[]{route[0], 0});
        }
        System.out.println(graph);
        stack.push(capitolCity);
        visited.add(capitolCity);
        while (!stack.isEmpty()) {
            int current = stack.pop();

            for(int[] neighbor: graph.get(current)) {
                int nextCity = neighbor[0];
                int direction = neighbor[1];
                if(!visited.contains(nextCity)) {
                    reOrderCount += direction;
                    stack.push(nextCity);
                    visited.add(nextCity);
                }
            }
        }
        return reOrderCount;
    }
}
/*
You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.

In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.

Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.



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
Absolutely! Let me explain this maze problem in a **very simple, human brain-friendly way**, like a story.

---

## 🧩 The Maze Problem – Explained Like You're 5

### 🎯 Goal:
You are **standing inside a maze**.
- The maze is made of **empty paths (`.`)** and **walls (`+`)**.
- You start from a certain **entrance position** inside the maze.

Your job is to find **the shortest path to get out** of the maze.

But here’s the rule:
> 🚪 You can only **exit** if you step on an **empty cell (.) on the edge** of the maze — not the entrance.

---

### 🛣️ How You Can Move:
- **UP**
- **DOWN**
- **LEFT**
- **RIGHT**

You **can’t walk through walls (`+`)**, and you **can’t leave the maze from the sides unless it’s an exit**.

---

### ✅ Example

```text
maze = [
  ["+","+",".","+"],
  [".",".",".","+"],
  ["+","+","+","."]
]
entrance = [1,2] ← you're starting here (row 1, column 2)
```

Let’s look at the maze with entrance marked:

```
+ + . +
. . E +
+ + + .
```

- `E` is where you are.
- You want to reach the nearest border `.` (exit).
- In this case:
  - Going **up** takes you to `[0,2]` → it's on the **edge** → ✅ exit!
  - It takes **1 move** to reach it.

So the output is `1`.

---

### ❌ When There's No Exit
If you are in a maze that’s surrounded by walls or you can't reach any edges — you return `-1` because there's **no way out**.

---

## 🧠 The Logic (Simple Version)

1. **Start from the entrance.**
2. Use something like a **queue (line)** to try each possible move.
3. Move to a spot only if:
   - It’s not a wall (`+`)
   - It hasn’t been visited before
4. If that spot is on the **edge of the maze**, and it’s not the entrance — boom, that's your **exit**.
5. If you find it, return the number of steps it took to get there.
6. If you finish checking everything and didn’t find an exit → return `-1`.

---

### 💡 Imagine This Like:
You're standing in a corn maze 🌽 and you're shouting:
> “Can I go left? Nope, wall. Can I go right? Yes! Oh hey, that spot touches the fence. I’m out!”

---

Would you like to see the Java or Python code using this logic in a way that’s also easy to follow?
 */
/*
Awesome question! Knowing **when to use BFS vs DFS** is super important for solving problems efficiently — and you're clearly thinking like a real problem-solver now 😎

---

## 🧠 BFS vs DFS – The Simple Brain Guide

Let’s first look at what each one does **in plain language**:

---

### 🌊 **BFS (Breadth-First Search)**
> “Explore level-by-level, closest stuff first”

**Think of**: Water spreading outward from a center point in waves 🌊

- ✅ Great for **finding the shortest path** (like in a maze)
- ✅ Good when you need **minimum steps or distance**
- ✅ Use when you're trying to **find something ASAP**
- ❌ Can use more memory (stores many nodes at once)

---

### 🕳️ **DFS (Depth-First Search)**
> “Explore one path deeply before trying another”

**Think of**: Crawling through a tunnel system and going all the way until you hit a wall, then backtracking 🧗‍♀️

- ✅ Great for checking if **a path exists**
- ✅ Good for **searching entire areas** or when solving **puzzles** (like Sudoku, backtracking)
- ✅ Often easier to implement recursively
- ❌ Might get stuck on the wrong path and not find the shortest route

---

## 🤝 When to Use Which?

| Situation                                      | Use BFS ✅ | Use DFS ✅ |
|-----------------------------------------------|------------|------------|
| Find **shortest path** (minimum steps)         | ✅          | ❌          |
| Explore **all possible paths**                 | ❌          | ✅          |
| Solve **puzzle or backtracking problems**      | ❌          | ✅          |
| Detect **cycles in a graph**                   | ✅/❌       | ✅          |
| Work with **deep trees** (avoid memory issues) | ❌          | ✅          |
| Want to find something **quickly near start**  | ✅          | ❌          |
| Want to **visit every node/room once**         | ✅          | ✅          |

---

### 🧩 Examples

| Problem                                         | BFS or DFS? | Why? |
|------------------------------------------------|-------------|------|
| Shortest path in a maze                        | ✅ BFS       | You need the **minimum steps** |
| Valid path exists between 2 nodes              | ✅ DFS       | You just want to **check if any path exists** |
| Solving Sudoku or N-Queens                     | ✅ DFS       | These need **backtracking** |
| Clone a graph                                  | ✅ BFS       | Visit all nodes |
| Word Ladder (word transformation sequence)     | ✅ BFS       | Shortest number of transformations |
| Find all combinations or subsets               | ✅ DFS       | Try every possible path |

---

### 🧠 Real-Life Analogy

- **BFS**: Like checking all the rooms in a building floor-by-floor
- **DFS**: Like walking down one hallway until the end, then coming back and trying another one

---

### Want a rule of thumb?

> **If you need the shortest path → use BFS.**
> **If you're exploring all options → use DFS.**

---

Want help turning this into a visual blog or cheat sheet? I’d be happy to help!
 */