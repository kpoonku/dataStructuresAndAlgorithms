package data.structures.algorithms.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class N216CombinationSumIII_Iterative {

    public static void main(String[] args) {
        N216CombinationSumIII_Iterative solver = new N216CombinationSumIII_Iterative();

        System.out.println(solver.combinationSum3(3, 7)); // [[1,2,4]]
        System.out.println(solver.combinationSum3(3, 9)); // [[1,2,6],[1,3,5],[2,3,4]]
        System.out.println(solver.combinationSum3(4, 1)); // []
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        Stack<Node> stack = new Stack<>();

        // Start with empty combination
        stack.push(new Node(new ArrayList<>(), 0, 1));

        while (!stack.isEmpty()) {
            Node current = stack.pop();

            // If we found a valid combination
            if (current.combo.size() == k && current.sum == n) {
                result.add(new ArrayList<>(current.combo));
                continue;
            }

            // If invalid (too many numbers or too big sum), skip
            if (current.combo.size() >= k || current.sum >= n) continue;

            for (int i = current.start; i <= 9; i++) {
                if (current.sum + i > n) {
                    break;
                }
                List<Integer> newCombo = new ArrayList<>(current.combo);
                newCombo.add(i);
                stack.push(new Node(newCombo, current.sum + i, i + 1));
            }
        }

        return result;
    }

    // Helper class to represent a state in the stack
    static class Node {
        List<Integer> combo;
        int sum;
        int start;

        Node(List<Integer> combo, int sum, int start) {
            this.combo = combo;
            this.sum = sum;
            this.start = start;
        }
    }
}

/*
Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

Example 1:
Input: k = 3, n = 7
Output: [[1,2,4]]
Explanation:
1 + 2 + 4 = 7
There are no other valid combinations.

Example 2:
Input: k = 3, n = 9
Output: [[1,2,6],[1,3,5],[2,3,4]]
Explanation:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.

Example 3:
Input: k = 4, n = 1
Output: []
Explanation: There are no valid combinations.
Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.

Constraints:

2 <= k <= 9
1 <= n <= 60
*/
/*

 */