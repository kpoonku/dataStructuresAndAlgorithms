package data.structures.algorithms.graph.dfs;

import java.util.*;

public class N841CanVisitAllRooms {
    public static void main(String[] args) {
        N841CanVisitAllRooms solution = new N841CanVisitAllRooms();

        // Example 1
        List<List<Integer>> rooms1 = new ArrayList<>();
        rooms1.add(List.of(1));        // Room 0 has key to room 1
        rooms1.add(List.of(2));        // Room 1 has key to room 2
        rooms1.add(List.of(3));        // Room 2 has key to room 3
        rooms1.add(new ArrayList<>());       // Room 3 has no keys
        System.out.println(solution.canVisitAllRooms(rooms1));  // Output: true

        // Example 2
        List<List<Integer>> rooms2 = new ArrayList<>();
        rooms2.add(Arrays.asList(1, 3));     // Room 0 has keys to rooms 1 and 3
        rooms2.add(Arrays.asList(3, 0, 1));  // Room 1 has keys to rooms 0, 1, and 3
        rooms2.add(List.of(2));        // Room 2 has key to room 2
        rooms2.add(List.of(0));        // Room 3 has key to room 0
        System.out.println(solution.canVisitAllRooms(rooms2));  // Output: false
        System.out.println();
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>(rooms.size());
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        visited.add(0);

        while (!stack.isEmpty()) {
            int room = stack.pop();
            List<Integer> roomsList = rooms.get(room);
            for (int toBeVisited : roomsList) {
                if (!visited.contains(toBeVisited)) {
                    visited.add(toBeVisited);
                    stack.push(toBeVisited);
                }
            }
        }
        System.out.println("visited : " + visited);
        return (visited.size() == rooms.size());
    }
}

/*
https://leetcode.com/problems/keys-and-rooms/description/
841. Keys and Rooms
There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all
the rooms. However, you cannot enter a locked room without having its key.

When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it
unlocks, and you can take all of them with you to unlock the other rooms.

Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you
can visit all the rooms, or false otherwise.

Example 1:
Input: rooms = [[1],[2],[3],[]]
Output: true
Explanation:
We visit room 0 and pick up key 1.
We then visit room 1 and pick up key 2.
We then visit room 2 and pick up key 3.
We then visit room 3.
Since we were able to visit every room, we return true.

Example 2:
Input: rooms = [[1,3],[3,0,1],[2],[0]]
Output: false
Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.

Constraints:
n == rooms.length
2 <= n <= 1000
0 <= rooms[i].length <= 1000
1 <= sum(rooms[i].length) <= 3000
0 <= rooms[i][j] < n
All the values of rooms[i] are unique.
 */
/*
Let's break this problem down in **very simple, beginner-level** terms — like a puzzle or adventure game.
---
## 🗝️ The Story (Beginner-Friendly)
Imagine you're in a hallway with many **locked rooms**, labeled from `0` to `n-1`.
* **Only room 0 is unlocked** at the start.
* When you go into a room, you might **find keys** to other rooms.
* Your goal is to use those keys to **unlock and enter all rooms**.
You’re given a list of rooms. Each room contains a **list of keys**.
You need to check:
> Is it possible to **visit all the rooms** using the keys you find along the way?
---
## 🧪 Example 1:
```
rooms = [[1], [2], [3], []]
```
Let’s walk through it:
* Start at room `0`, find key to room `1`
* Go to room `1`, find key to room `2`
* Go to room `2`, find key to room `3`
* Go to room `3`, done!
✅ All rooms visited — return `true`
---
## ❌ Example 2:
```
rooms = [[1,3], [3,0,1], [2], [0]]
```
* Start at room `0`, find keys to rooms `1` and `3`
* Go to room `1`, find more keys but not for room `2`
* Go to room `3`, no key to room `2`
* Room `2` is still locked, and we don’t have its key!
❌ Cannot visit all rooms — return `false`
---
## 🧠 How to Think About It
Think of this like:
* You're exploring a **maze of rooms**
* You collect keys from each room
* You use the keys to unlock **new rooms**
* Keep track of which rooms you’ve already visited
* Stop when either:
  * You've visited **all rooms** ✅
  * Or you run out of keys and **some rooms are still locked** ❌
---
## 🔍 Real-World Analogy
It’s like a **treasure hunt**:
* You start with one open door
* Inside, you find clues (keys) to more doors
* Your goal is to open **every door** using the clues you collect
---
## 🧠 Core Concepts Behind the Problem
This is actually a **graph traversal problem** in computer science:
* Rooms are **nodes**
* Keys represent **edges**
* You're trying to **visit all nodes** in a connected graph
You can solve it using:
* **Depth-First Search (DFS)**
* or **Breadth-First Search (BFS)**
---
 */