package data.structures.algorithms.graph.dfs;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class N547NoOfProvince {
    public static void main(String[] args) {
        N547NoOfProvince solution = new N547NoOfProvince();

        // Example 1:
        int[][] isConnected1 = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution.findCircleNum(isConnected1));  // Output: 2

        // Example 2:
        int[][] isConnected2 = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        System.out.println(solution.findCircleNum(isConnected2));  // Output: 3
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
with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city
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