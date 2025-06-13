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
        Map<Integer, List<List<Integer>>> graph = new HashMap<>();
        for (int[] route : connections) {
            graph.putIfAbsent(route[0], new ArrayList<>());
            graph.get(route[0]).add(Arrays.asList(route[1], 1));
            graph.putIfAbsent(route[1], new ArrayList<>());
            graph.get(route[1]).add(Arrays.asList(route[0], 0));
        }
        System.out.println(graph);
        stack.push(capitolCity);
        visited.add(capitolCity);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            for(List<Integer> neighbor: graph.get(current)) {
                int nextCity = neighbor.getFirst();
                int direction = neighbor.getLast();
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
There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to
travel between two different cities (this network form a tree). Last year, The ministry of
transport decided to orient the roads in one direction because they are too narrow.
Roads are represented by connections where connections[i] = [ai, bi] represents a road from city
ai to city bi.
This year, there will be a big event in the capital (city 0), and many people want to travel to
this city.
Your task consists of reorienting some roads such that each city can visit the city 0.
Return the minimum number of edges changed.
It's guaranteed that each city can reach city 0 after reorder.

Example 1:
Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0
(capital).

Example 2:
Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0
(capital).
Example 3:
Input: n = 3, connections = [[1,0],[2,0]]
Output: 0

Constraints:
2 <= n <= 5 * 104
connections.length == n - 1
connections[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
*/
/*
We are given a **tree** with `n` nodes and `n-1` edges, but the edges have **direction**.
The goal is to **reorient the minimum number of edges** so that **every node can reach city 0**.
---
### 🔍 **Key Insight**
We want **every node to be able to reach node 0**, so we treat the problem as **building
a reverse path from each node back to node 0**.
Each edge is directed. If an edge goes **away from 0** (i.e., `0 -> 1`),
it needs to be reversed because we need to go **towards 0**.
---
### ✅ **Approach**
1. Build a **graph** with direction information:
   * For each connection `[a, b]`, add:
     * `(b, 0)` to `graph[a]`: meaning `a → b` (outgoing edge)
     * `(a, 1)` to `graph[b]`: meaning `b ← a` (incoming edge)
   The second element in the tuple indicates whether the edge needs to be
   **reversed** (1) or not (0).
2. Do a **DFS/BFS from node 0**, and for each outgoing edge (i.e., `0 → 1`),
**count it as a reversal needed**.
### 💡 Time and Space Complexity
* **Time:** `O(n)` — each edge is visited once.
* **Space:** `O(n)` — for graph and visited list.
*/
/*
Awesome question! Knowing **when to use BFS vs DFS** is super important for solving problems efficiently —
and you're clearly thinking like a real problem-solver now 😎
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
 */