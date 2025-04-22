package data.structures.algorithms.binary.tree.bfs;

import data.structures.algorithms.oracle.tree.Node;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static data.structures.algorithms.oracle.tree.BinaryTree.buildBinaryTree;
import static data.structures.algorithms.oracle.tree.BinaryTree.inOrderTraversal;

public class N1161MaxLevelSum {
    public static int maxLevelSum(Node<Integer> root) {
        int maxSum = Integer.MIN_VALUE;
        int level = 0;
        int maxLevel = 0;
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        int levelSum = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            levelSum = 0;
            for (int i = 0; i < levelSize; i++) {
                Node<Integer> node = queue.poll();
                if (node == null) {
                    continue;
                }
                levelSum += node.value;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (levelSum > maxSum) {
                maxSum = levelSum;
                maxLevel = level;
            }
            level++;
        }

        return maxLevel;
    }

    public static void main(String[] args) {
        Node<Integer> root = buildBinaryTree(Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5", "6", "7", "x", "x", "8", "x", "x", "9", "10"), new int[]{0});
        inOrderTraversal(root, "   ");
        System.out.println("Max Level Sum : " + maxLevelSum(root));
    }
}

/*
Given the root of a binary tree, the level of its root is 1, the level of its children is 2, and so on.

Return the smallest level x such that the sum of all the values of nodes at level x is maximal.



Example 1:


Input: root = [1,7,0,7,-8,null,null]
Output: 2
Explanation:
Level 1 sum = 1.
Level 2 sum = 7 + 0 = 7.
Level 3 sum = 7 + -8 = -1.
So we return the level with the maximum sum which is level 2.
Example 2:

Input: root = [989,null,10250,98693,-89388,null,null,null,-32127]
Output: 2


Constraints:

The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105
 */
/*
### Problem Understanding

In this problem, you are given a binary tree, and the task is to find the **level** in the tree where the sum of all the node values at that level is the largest.

- **Level 1** refers to the root.
- **Level 2** consists of the root's children.
- **Level 3** consists of the children of the nodes at level 2, and so on.

You need to find the level with the maximum sum of node values. In case of a tie (i.e., two or more levels having the same sum), you should return the smallest level number.

### Approach

The solution can be broken down into these steps:

1. **Level Order Traversal (BFS)**:
   - Perform a level-order traversal of the binary tree. During this traversal, we will compute the sum of node values at each level.

2. **Track Maximum Sum**:
   - At each level, calculate the sum of all nodes at that level.
   - Keep track of the maximum sum encountered, and also store the level that produced this sum.

3. **Handle Ties**:
   - If two levels have the same sum, return the smallest level (this is naturally handled by checking levels from top to bottom during BFS).

4. **Return the Result**:
   - After the traversal, the level with the maximum sum is returned.

### Plan

1. Use a queue to perform **Level Order Traversal (BFS)**.
2. For each level, calculate the sum of all nodes at that level.
3. Compare the sum of the current level with the maximum sum found so far.
4. If the sum is greater, update the maximum sum and the level.
5. If two levels have the same sum, return the smaller one (this will naturally occur as BFS proceeds level by level).

### Code Implementation in Java

```java
import java.util.*;

public class BinaryTreeMaxLevelSum {

    // Definition for a binary tree node
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    // Function to find the smallest level with the maximum sum
    public static int maxLevelSum(TreeNode root) {
        if (root == null) {
            return -1;  // Return -1 if the tree is empty
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        int maxLevel = 1;
        int maxSum = Integer.MIN_VALUE;

        // Level order traversal (BFS)
        while (!queue.isEmpty()) {
            int levelSize = queue.size();  // Number of nodes at the current level
            int currentLevelSum = 0;

            // Traverse all nodes at the current level
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevelSum += currentNode.val;

                // Add child nodes to the queue for the next level
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            // If the current level sum is greater than maxSum, update the result
            if (currentLevelSum > maxSum) {
                maxSum = currentLevelSum;
                maxLevel = level;
            }

            // Move to the next level
            level++;
        }

        return maxLevel;
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(7);
        root1.right = new TreeNode(0);
        root1.left.left = new TreeNode(7);
        root1.left.right = new TreeNode(-8);

        System.out.println("Level with max sum: " + maxLevelSum(root1));  // Output: 2

        // Example 2
        TreeNode root2 = new TreeNode(989);
        root2.right = new TreeNode(10250);
        root2.right.left = new TreeNode(98693);
        root2.right.right = new TreeNode(-89388);
        root2.right.left.left = new TreeNode(-32127);

        System.out.println("Level with max sum: " + maxLevelSum(root2));  // Output: 2
    }
}
```

### **Explanation of Code:**

1. **TreeNode class**:
   - This is a simple representation of a tree node, containing a `val` field for the node's value, and references to the left and right children.

2. **maxLevelSum function**:
   - This function takes the root of the binary tree and returns the smallest level with the maximum sum.
   - We use a **queue** to implement **Level Order Traversal (BFS)**, where we process nodes level by level.
   - For each level, we calculate the sum of the node values. If the sum of the current level exceeds the maximum sum encountered so far, we update the maximum sum and the level.
   - After processing all levels, we return the level with the maximum sum.

3. **Main method**:
   - We create sample binary trees and call the `maxLevelSum` method to get the level with the maximum sum for each tree.

### **Time Complexity:**
- **O(N)** where **N** is the number of nodes in the tree. This is because we visit each node exactly once in the BFS traversal.

### **Space Complexity:**
- **O(N)** where **N** is the number of nodes in the tree. The space complexity is determined by the size of the queue during level-order traversal, which can hold up to half of the nodes in the tree in the worst case.

### **Example Walkthrough:**

#### Example 1:
```plaintext
Input: root = [1, 7, 0, 7, -8, null, null]
```

1. **Level 1**:
   - Nodes: `[1]`
   - Sum: `1`

2. **Level 2**:
   - Nodes: `[7, 0]`
   - Sum: `7 + 0 = 7`

3. **Level 3**:
   - Nodes: `[7, -8]`
   - Sum: `7 + (-8) = -1`

   The maximum sum is `7` at level 2. Therefore, the result is `2`.

#### Example 2:
```plaintext
Input: root = [989, null, 10250, 98693, -89388, null, null, null, -32127]
```

1. **Level 1**:
   - Nodes: `[989]`
   - Sum: `989`

2. **Level 2**:
   - Nodes: `[10250]`
   - Sum: `10250`

   The maximum sum is `10250` at level 2. Therefore, the result is `2`.

### **Summary**:
- We use **Level Order Traversal (BFS)** to traverse the tree and compute the sum of node values at each level.
- We track the level with the highest sum, and return the smallest level in case of ties.
* */