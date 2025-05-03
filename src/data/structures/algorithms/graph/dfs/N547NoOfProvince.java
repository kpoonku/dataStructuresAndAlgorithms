package data.structures.algorithms.graph.dfs;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class N547NoOfProvince {
    public static void main(String[] args) {
        N547NoOfProvince solution = new N547NoOfProvince();

        // Example 1:
        int[][] isConnected1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        // System.out.println(solution.findCircleNum(isConnected1));  // Output: 2

        // Example 2:
        int[][] isConnected2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        // System.out.println(solution.findCircleNum(isConnected2));  // Output: 3

        int[][] isConnected3 = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        System.out.println(solution.findCircleNum(isConnected3));  // Output: 2
    }

    public int findCircleNum(int[][] isconnected) {
        int n = isconnected.length;
        Set<Integer> visited = new HashSet<>(n);

        int province = 0;
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                province++;
                Stack<Integer> stack = new Stack<>();
                stack.push(i);
                while (!stack.isEmpty()) {
                    int city = stack.pop();
                    if (!visited.contains(city)) {
                        visited.add(city);
                        for (int j = 0; j < n; j++) {
                            if (isconnected[city][j] == 1 && !visited.contains(j)) {
                                stack.push(j);
                            }
                        }
                    }
                }
            }
        }
        return province;
    }
}
/*
https://leetcode.com/problems/number-of-provinces/?envType=study-plan-v2&envId=leetcode-75
There are n cities. Some of them are connected, while some are not. If city a is connected directly
with city b, and city b is connected directly with city c,
then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities
outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city
and the jth city
are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

Example 1:
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2

Example 2:
Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3

Constraints:
1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
*/
/*
Let’s explain this problem in **very simple, beginner-friendly** terms, without using code yet.
---
## 🏙️ What is the Problem About?
You have a number of **cities**, and some of them are **connected** (like by roads or transport).
Your job is to find how many **provinces** exist.
---
## ✅ What is a Province?
A **province** is a group of cities that are all connected — either:
* **Directly** (city A to city B)
* Or **indirectly** (A to B through C, like A–B–C)
Cities in one province are connected to each other **somehow**, and they are **not
connected** to cities in **another province**.
---
## 📊 Input: A Matrix
You're given a 2D grid called `isConnected`.
If `isConnected[i][j] == 1`, it means:
* City `i` is directly connected to city `j`
If it’s `0`, they’re not directly connected.
Also:
* Every city is connected to itself (so diagonal values like `isConnected[i][i]` are always 1)
---
## 🎯 What Do You Have to Find?
You have to count **how many groups (provinces)** of connected cities exist.
---
## 🔍 Let's Understand With Examples:
### 🧪 Example 1:
```
Input:
[[1,1,0],
 [1,1,0],
 [0,0,1]]
```
We have 3 cities:
* City 0 and City 1 are connected directly (1s at \[0]\[1] and \[1]\[0])
* City 2 is only connected to itself — no connection to others
So:
* Province 1: City 0 and City 1
* Province 2: City 2
✅ Answer: **2 provinces**
---
### 🧪 Example 2:
```
Input:
[[1,0,0],
 [0,1,0],
 [0,0,1]]
```
Each city is only connected to itself — no one is connected to anyone else.
So:
* Province 1: City 0
* Province 2: City 1
* Province 3: City 2
✅ Answer: **3 provinces**
---
## 🧠 How Do We Solve It?
This is like finding **connected groups** in a network or map. You can use:
* **DFS (Depth-First Search)** or
* **BFS (Breadth-First Search)** or
* **Union-Find** (a structure to group connected items)
Basically, we explore all cities connected to one starting city, mark them as visited,
then move on to the next unvisited city to start a new province.
---
## 🌍 Real-Life Analogy
Imagine cities as people and roads as friendships:
* If Alice knows Bob, and Bob knows Charlie, then Alice is part of the same friend group
as Charlie — even if she doesn’t know him directly.
* You're counting how many **separate friend groups** exist.
---
*/