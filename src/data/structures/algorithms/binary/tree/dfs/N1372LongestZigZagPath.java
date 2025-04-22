package data.structures.algorithms.binary.tree.dfs;

import data.structures.algorithms.oracle.tree.BinaryTree;
import data.structures.algorithms.oracle.tree.Node;

import java.util.Arrays;

public class N1372LongestZigZagPath {
    private static int maxZigZag = 0;

    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree(Arrays.asList("1", "x", "1", "1", "1", "x", "x", "1", "1", "x", "1", "x", "x", "x", "1"), new int[]{0});
        BinaryTree.inOrderTraversal(root, " ");
        int maxValue = longestZigZag(root);
        System.out.println("Max Value: " + maxValue);
        root = BinaryTree.buildBinaryTree(Arrays.asList("1", "1", "1", "x", "1", "x", "x", "1", "1", "x", "1", "1", "1"), new int[]{0});
        BinaryTree.inOrderTraversal(root, " ");
        maxValue = longestZigZag(root);
        System.out.println("Max Value: " + maxValue);
    }

    public static int longestZigZag(Node<Integer> root) {
        dfs(root, 0, false);
        dfs(root, 0, true);
        return maxZigZag;
    }

    public static void dfs(Node<Integer> root, int length, boolean isLeft) {
        if (root == null) {
            return;
        }
        maxZigZag = Math.max(maxZigZag, length);
        if (isLeft) {
            dfs(root.right, length + 1, false);
            dfs(root.left, 1, true);
        } else {
            dfs(root.left, length + 1, false);
            dfs(root.right, 1, true);
        }
    }
}
/*
You are given the root of a binary tree.

A ZigZag path for a binary tree is defined as follow:

Choose any node in the binary tree and a direction (right or left).
If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
Change the direction from right to left or from left to right.
Repeat the second and third steps until you can't move in the tree.
Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

Return the longest ZigZag path contained in that tree.

Example 1:
Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1]
Output: 3
Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
Example 2:
Input: root = [1,1,1,null,1,null,null,1,1,null,1]
Output: 4
Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
Example 3:
Input: root = [1]
Output: 0

Constraints:

The number of nodes in the tree is in the range [1, 5 * 104].
1 <= Node.val <= 100
 */

/*
1. Understand the Problem Conceptually
What is a ZigZag path?
Imagine you're walking through a tree, starting from the root node.

From each node, you have two choices: move to the left child or move to the right child.

A ZigZag path is defined as a path where you alternate directions (i.e., go left and then right, or right and then left).

The key part is alternating directions at each step.

Path Definition:
You can choose any node in the tree and start moving either left or right.

The path stops if you reach a leaf node, or if there are no more children to move to in the direction you’re supposed to go.

The length of the path is the number of nodes in the path minus 1.

In this tree:

If you start at node 3, and move right, you can then move left, and then again move right.

This forms a path like 3 → 4 → 5, which is a ZigZag.

Steps to Solve:
We need to figure out:

How to traverse the tree.

How to alternate directions (left and right) at each step.

How to keep track of the longest ZigZag path we encounter

3. Key Concepts to Implement the Solution
1. Depth-First Search (DFS):
We need to explore every possible path in the tree from each node.

DFS is a good fit because it helps us explore paths down the tree and keep track of the current state (whether we're moving left or right).

2. Track ZigZag Length:
For each node, we will track two potential ZigZag paths:

One where we move left (then right, then left, etc.).

One where we move right (then left, then right, etc.).

3. Recursive Function:
We will use recursion to keep track of how long we can go in each direction (left or right) from any node.

We will update the longest ZigZag path each time we explore a node.


To help the human brain understand and create the logic for solving the **longest ZigZag path in a binary tree** problem, it is essential to break the problem down step-by-step in a way that is digestible. Let’s do this with the following approach:

### 1. **Understand the Problem Conceptually**

#### What is a ZigZag path?
- Imagine you're walking through a tree, starting from the root node.
- From each node, you have two choices: move to the left child or move to the right child.
- A ZigZag path is defined as a path where you **alternate directions** (i.e., go left and then right, or right and then left).
- The key part is **alternating** directions at each step.

#### Path Definition:
- You can choose any node in the tree and start moving either left or right.
- The path stops if you reach a leaf node, or if there are no more children to move to in the direction you’re supposed to go.
- The length of the path is the number of nodes in the path minus 1.

---

### 2. **Breaking the Problem Into Smaller Pieces**

#### Example:
Let's use a simple example:

```
      3
     / \
    1   4
   /     \
  3       5
```

In this tree:
- If you start at node 3, and move **right**, you can then move **left**, and then again move **right**.
- This forms a path like `3 → 4 → 5`, which is a ZigZag.

#### Steps to Solve:
We need to figure out:
- **How to traverse the tree.**
- **How to alternate directions (left and right) at each step.**
- **How to keep track of the longest ZigZag path we encounter.**

---

### 3. **Key Concepts to Implement the Solution**

#### 1. **Depth-First Search (DFS)**:
- We need to explore every possible path in the tree from each node.
- DFS is a good fit because it helps us explore paths down the tree and keep track of the current state (whether we're moving left or right).

#### 2. **Track ZigZag Length**:
- For each node, we will track two potential ZigZag paths:
  - One where we move **left** (then right, then left, etc.).
  - One where we move **right** (then left, then right, etc.).

#### 3. **Recursive Function**:
- We will use recursion to keep track of how long we can go in each direction (left or right) from any node.
- We will update the longest ZigZag path each time we explore a node.

---

### 4. **Simplified Step-by-Step Plan**

1. **Start at the Root Node**:
   - We can start by assuming two possibilities:
     - Start by moving **right** (direction).
     - Start by moving **left** (direction).

2. **Move to Children**:
   - If we are currently moving **right**, the next move should be **left**, and vice versa.
   - Keep exploring down the tree.

3. **Track the ZigZag Length**:
   - At each step, if we continue the path, increase the length of the ZigZag path.
   - Once we can’t go further in a direction, check if this is the longest ZigZag path encountered so far.

4. **Return the Maximum ZigZag Path**:
   - After exploring all possible paths, the result will be the maximum length of all ZigZag paths encountered.

---

### 5. **The Logical Flow (Simplified for the Brain)**

#### **Start at the Root Node**:
You are standing at the root of the tree. You can go left or right. Choose one direction (left or right).

#### **Move to a Child**:
Once you move to a child, you change the direction. If you started by going **left**, you now go **right**, and if you started by going **right**, you go **left**.

#### **Count the Steps**:
Every time you move to a new node, count how many steps you’ve taken so far in your current direction.

#### **When You Can't Move, Stop**:
If you can't move further (either because you reached the end of the tree or there are no children in the direction you're going), stop and check how long your ZigZag path was.

#### **Remember the Longest Path**:
If the current ZigZag path is the longest you’ve seen, remember it.

#### **Repeat**:
Repeat this process for every node, starting the ZigZag path in both directions (left and right).

---

### 6. **Relating It to Everyday Experience**

Imagine you’re playing a game where you’re allowed to walk through a forest, but there are rules:
- Every time you make a move, you must change the direction (left, then right, then left, etc.).
- The goal is to walk as far as you can without breaking the direction rule.
- After exploring, you measure the longest path you walked.

This game analogy helps in visualizing the problem and understanding how alternating between left and right while counting the steps forms the ZigZag pattern.

---

### 7. **Use Pseudo Code to Further Clarify**

This pseudo-code will show how the logic flows:

```
function longestZigZag(root):
    maxZigZag = 0
    function dfs(node, length, isLeft):
        if node is null:
            return
        maxZigZag = max(maxZigZag, length)
        if isLeft:
            dfs(node.right, length + 1, false) // move right next
            dfs(node.left, 1, true)           // start moving left
        else:
            dfs(node.left, length + 1, true)  // move left next
            dfs(node.right, 1, false)         // start moving right

    dfs(root, 0, true)   // start moving right
    dfs(root, 0, false)  // start moving left

    return maxZigZag
```

---

### 8. **Final Summary for Understanding**

- **ZigZag path**: It’s a path where you alternate directions.
- **DFS**: We recursively explore the tree, alternating between left and right.
- **Max Path Length**: Keep track of the longest ZigZag path as you explore.
- **Recursion helps**: DFS works well here because it allows you to explore one path all the way down before trying another.

The brain needs to focus on the **alternating direction rule** and how you can explore every node while switching directions, and then comparing the length of every possible ZigZag path. By following this step-by-step approach, you will be able to intuitively understand the problem and solution.
 */