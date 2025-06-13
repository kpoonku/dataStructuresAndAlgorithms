package data.structures.algorithms.binary.tree.dfs;

import data.structures.algorithms.oracle.tree.BinaryTree;
import data.structures.algorithms.oracle.tree.Node;

import java.util.Arrays;
import java.util.HashMap;

public class N437PathSumIII {
    // HashMap to store the frequency of prefix sums (sums of paths so far)
    private static final HashMap<Long, Integer> prefixSumCount = new HashMap<>();
    private static int result = 0;

    // The main function that will start DFS traversal
    public static int pathSum(Node<Integer> root, int targetSum) {
        prefixSumCount.put(0L, 1);  // To handle the case where a path itself is equal to targetSum
        dfs(root, 0, targetSum);  // Start DFS from the root
        return result;
    }

    // DFS helper function
    private static void dfs(Node<Integer> node,
                            long currentSum,
                            int targetSum) {
        if (node == null) {
            return;
        }
        // Update the running sum
        currentSum += node.value;

        // Check if we've seen (currentSum - targetSum) before
        // If so, that means there are some paths that sum to targetSum
        if (prefixSumCount.containsKey(currentSum - targetSum)) {
            result += prefixSumCount.get(currentSum - targetSum);
        }

        // Store the current sum in the hash map (prefixSumCount)
        prefixSumCount.put(currentSum,
                prefixSumCount.getOrDefault(currentSum, 0) + 1);

        // Explore the left and right subtrees recursively
        dfs(node.left, currentSum, targetSum);
        dfs(node.right, currentSum, targetSum);

        // Backtrack: after exploring left and right, remove the current sum from the map
        prefixSumCount.put(currentSum, prefixSumCount.get(currentSum) - 1);
    }

    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree
                (Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5", "6", "7",
                        "x", "x", "8", "x", "x", "9", "10"), new int[]{0});
        BinaryTree.inOrderTraversal(root, "   ");
        System.out.println("Root : " + root.value);
        System.out.println();
        System.out.println("Path Sum of BST : " + pathSum(root, 12));
    }
}
/*
Given the root of a binary tree and an integer targetSum, return the number of
paths where the sum of the values along the path equals targetSum.

The path does not need to start or end at the root or a leaf, but it must go
downwards (i.e., traveling only from parent nodes to child nodes).

Example 1:
Input: root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
Output: 3
Explanation: The paths that sum to 8 are shown.
Example 2:
Input: root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
Output: 3
Constraints:

The number of nodes in the tree is in the range [0, 1000].
-109 <= Node.val <= 109
-1000 <= targetSum <= 1000
 */
/*
Sure! Let's break it down step by step so it's easier to understand:

### Problem Explanation:
We are given a **binary tree**, and we need to find the **number of paths** that add up to a given **target
sum**. A path is defined as going from a node down to its descendants, and the sum of the nodes along that path
should equal the target.

### Key Points to Understand:

1. **What is a binary tree?**
   - A binary tree is a type of tree structure where each node has at most two children: a **left child** and
   a **right child**. For example, if a tree looks like this:
     ```
         10
        /  \
       5    -3
      / \     \
     3   2    11
     ```
     - The **root** is the node with the value `10`, which has two children: `5` (left) and `-3` (right).
     - Each of these children may have their own children, like `3`, `2`, `11` under `5` and `-3`.

2. **What are paths?**
   - A **path** is a sequence of nodes where you start at one node and go down to its children (left or right),
   and you keep following until you reach a leaf (a node with no children).
   - Example paths: From node `10` → `5` → `3` (This is a valid path because we followed a parent-child
   relationship).
   - Paths don't need to start from the root, but they must go **downwards** (to the left or right child).

3. **How do we find paths that sum to the target?**
   - We need to check each path to see if the sum of its node values equals a given `targetSum`.

### The Approach We Use:

#### **Depth-First Search (DFS)**:
We will use a **DFS** approach to explore all the paths in the tree, starting from the root node. During this
exploration, we keep track of the sum of the node values along the path we're currently on.

#### **Prefix Sum Idea**:
While going down a path, we can store the sum of all node values from the root to the current node. If, at any
point, the difference between the current sum and `targetSum` matches a sum we've encountered before, we know
we've found a valid path.

### The Solution, Step by Step:

1. **Starting from the root**, we begin to explore the tree recursively (DFS).
2. As we go down, we maintain a **running total** of the sum of all nodes on the current path (`currentSum`).
3. At each node, we check if there is a path from **any previous node** (in the path so far) that sums up to
`targetSum`. We do this by checking if `currentSum - targetSum` has been encountered before.
4. We **record the count** of every cumulative sum (`currentSum`) in a **hash map** (or dictionary), so we
can easily check if we’ve encountered that sum before.
5. Once we reach a leaf node (a node with no children), we backtrack, removing the current node’s sum from
the map.

### Why Use a Hash Map?
- The hash map helps us **remember** which sums we've seen in the past as we traverse the tree.
- This makes the process **more efficient** because we don't have to recompute sums from the beginning every
time we explore a new node.

### Code Breakdown:

Here’s a simplified version of the core logic, explaining how we check if paths sum to the target:

```java
import java.util.HashMap;

public class PathSumIII {

    // HashMap to store the frequency of prefix sums (sums of paths so far)
    private HashMap<Long, Integer> prefixSumCount = new HashMap<>();
    private int result = 0;

    // The main function that will start DFS traversal
    public int pathSum(TreeNode root, int targetSum) {
        prefixSumCount.put(0L, 1);  // To handle the case where a path itself is equal to targetSum
        dfs(root, 0, targetSum);  // Start DFS from the root
        return result;
    }

    // DFS helper function
    private void dfs(TreeNode node, long currentSum, int targetSum) {
        if (node == null) {
            return;
        }

        // Update the running sum
        currentSum += node.val;

        // Check if we've seen (currentSum - targetSum) before
        // If so, that means there are some paths that sum to targetSum
        if (prefixSumCount.containsKey(currentSum - targetSum)) {
            result += prefixSumCount.get(currentSum - targetSum);
        }

        // Store the current sum in the hash map (prefixSumCount)
        prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);

        // Explore the left and right subtrees recursively
        dfs(node.left, currentSum, targetSum);
        dfs(node.right, currentSum, targetSum);

        // Backtrack: after exploring left and right, remove the current sum from the map
        prefixSumCount.put(currentSum, prefixSumCount.get(currentSum) - 1);
    }

    // TreeNode definition
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }
}
```

### What Happens During the DFS?

1. **At each node**: We add the value of the current node to `currentSum`.
2. **Checking for valid paths**: We check if the difference `currentSum - targetSum` has been encountered before. If it has, we know that there is a path (from a previous node to the current node) that sums to `targetSum`.
3. **Tracking sums**: After checking for valid paths, we update our hash map to store the current sum. This helps us keep track of all sums we've encountered as we continue traversing.
4. **Backtracking**: When we're done exploring both left and right children, we backtrack and remove the current sum from the map to ensure we don't use it for other paths.

### Why does this work?
- **Prefix Sum**: We use the prefix sum technique to avoid rechecking all paths from the root every time we reach a new node. By tracking the cumulative sum along the path, we can efficiently determine if there are any valid paths that sum to `targetSum`.

### Example:

Consider the tree:

```
      10
     /  \
    5    -3
   / \     \
  3   2    11
```

And the target sum is `8`. The possible paths are:
- Path from `10 → 5 → 3`, which gives a sum of `10 + 5 + 3 = 8`
- Path from `5 → 3`, which gives a sum of `5 + 3 = 8`
- Path from `-3 → 11`, which gives a sum of `-3 + 11 = 8`

The algorithm will efficiently find these paths without having to traverse the same paths repeatedly.

### Conclusion:

This approach helps us efficiently count paths that sum to the target by using DFS and storing cumulative sums along the way. By leveraging a hash map for prefix sums, we can solve the problem in linear time, making it a very efficient solution for this type of tree traversal problem.

**********************

To solve the problem of finding the number of paths where the sum of the values along the path equals `targetSum` in a binary tree, we'll use a **recursive approach** combined with a **prefix sum** strategy. This allows us to efficiently check all possible paths in the tree without redundantly visiting the same paths multiple times.

### Approach Overview:

1. **Recursive Depth-First Search (DFS)**: We will traverse the tree using DFS, starting from each node, and at each node, check if there are any paths that sum to the target value.

2. **Prefix Sum Concept**: As we traverse the tree, we keep track of the current sum of the path. We will also track the cumulative sum from the root to the current node. At each node, we check if there is a path that sums to `targetSum` using the prefix sum.

3. **Efficient Calculation**: Instead of recomputing the sum for each possible path in every DFS, we store the prefix sums encountered so far in a hash map (or dictionary) to avoid redundant calculations and achieve optimal performance.

### Detailed Steps:

1. **DFS function**: For each node, we attempt to find all paths starting from that node and check if the sum of values along the path equals `targetSum`.
2. **HashMap (prefix sum)**: We use a hash map to store the cumulative sum at each node, which allows us to efficiently check if there is a previously encountered sum that would complete a valid path.

### Code Implementation:

```java
import java.util.HashMap;

public class PathSumIII {
    // HashMap to store the frequency of prefix sums
    private HashMap<Long, Integer> prefixSumCount;
    private int result;

    public int pathSum(TreeNode root, int targetSum) {
        prefixSumCount = new HashMap<>();
        prefixSumCount.put(0L, 1);  // To handle the case where a path itself is equal to targetSum
        result = 0;
        dfs(root, 0, targetSum);  // Start DFS with initial sum 0
        return result;
    }

    private void dfs(TreeNode node, long currentSum, int targetSum) {
        if (node == null) {
            return;
        }

        // Update the current sum
        currentSum += node.val;

        // Check if there is a valid path that ends at the current node
        if (prefixSumCount.containsKey(currentSum - targetSum)) {
            result += prefixSumCount.get(currentSum - targetSum);
        }

        // Update the prefix sum map
        prefixSumCount.put(currentSum, prefixSumCount.getOrDefault(currentSum, 0) + 1);

        // Recur for left and right child nodes
        dfs(node.left, currentSum, targetSum);
        dfs(node.right, currentSum, targetSum);

        // After returning from recursion, remove the current node's sum from the map
        // (this ensures that the sum is not counted for other paths)
        prefixSumCount.put(currentSum, prefixSumCount.get(currentSum) - 1);
    }

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
```

### Explanation:

1. **Helper Function `dfs`**:
   - The `dfs` function is the core of our solution. It traverses the binary tree recursively.
   - We maintain a running sum (`currentSum`) that tracks the sum of values from the root to the current node.
   - For each node, we check if there exists a prefix sum such that `currentSum - targetSum` is found in our `prefixSumCount` map. This indicates that there is a path that sums to the target value, and we increase our result count accordingly.

2. **Prefix Sum Map**:
   - The `prefixSumCount` hash map stores the frequency of each cumulative sum encountered along the path. The key is the sum, and the value is the number of times that sum has been encountered.
   - Initially, we put `0 -> 1` in the map to handle the case where the sum from the root to a node equals `targetSum` directly.

3. **Backtracking**:
   - After exploring the left and right subtrees, we backtrack by decrementing the count of the `currentSum` in the map. This ensures that we don't reuse the same sum in different paths.

### Example Walkthrough:

For a tree like:

```
      10
     /  \
    5    -3
   / \     \
  3   2    11
 / \   \
3  -2   1
```

If `targetSum = 8`, the function would return the number of valid paths where the sum of node values along the path is 8.

### Time Complexity:
- The time complexity is **O(n)**, where `n` is the number of nodes in the binary tree. This is because each node is visited once, and the lookup and insertion in the hash map takes constant time.

### Space Complexity:
- The space complexity is **O(n)** due to the space used by the recursion stack and the hash map to store the prefix sums.

### Conclusion:
This approach efficiently computes the number of paths with the given sum using depth-first search (DFS) and a hash map to track prefix sums. It avoids redundant calculations and ensures the solution runs in linear time relative to the number of nodes in the tree.
 */