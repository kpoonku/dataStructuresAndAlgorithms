package data.structures.algorithms.oracle.tree;

import java.util.Arrays;

public class N104MaxDepthOfTheTree {

    public static int dfs(Node<Integer> root) {
        if (root == null) return 0;
        return Math.max(dfs(root.left), dfs(root.right)) + 1;
    }

    public static int treeMaxDepth(Node<Integer> root) {
        return (root != null) ? dfs(root) - 1 : 0;
    }

    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree(Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5", "6", "7", "x", "x", "8", "x", "x", "9", "10"), new int[]{0});
        BinaryTree.inOrderTraversal(root, "  ");
        System.out.println();
        System.out.println(N104MaxDepthOfTheTree.treeMaxDepth(root));
    }
}
/*
Problem:
You are trying to find the maximum depth of a binary tree. The depth of a binary tree is the longest path from the root to any leaf node.

In other words, it’s the number of nodes from the root node down to the farthest leaf node.

Key Concepts:
Binary Tree: A binary tree is a structure where each node has at most two children (a left child and a right child).

Depth of a Tree: The depth is how many levels of nodes there are in the tree. For example:

A tree with just one node (the root) has a depth of 1.

A tree with the root and two children has a depth of 2.

How the code works:
The code is trying to calculate the maximum depth of the binary tree using recursion. Let's break it down in detail:

Step 1: Understanding the dfs Function
The function dfs (Depth-First Search) is a recursive function. It starts from the root node and explores all the way down to the leaf nodes. It works as follows:

java
Copy
public static int dfs(Node<Integer> root) {
    if (root == null) return 0;  // If the node is null, return 0 (no depth).
    return Math.max(dfs(root.left), dfs(root.right)) + 1;  // Explore left and right children and get the max depth.
}
Base Case: if (root == null) return 0;

This means if we encounter an empty node (null), we return 0 because an empty node doesn’t add to the depth.

Recursive Step: return Math.max(dfs(root.left), dfs(root.right)) + 1;

We call dfs recursively on the left and right children (root.left and root.right).

The Math.max function gives us the maximum of the two depths (left and right subtrees). We add 1 to the result to account for the current node (because we're counting the depth starting from 1).

Step 2: Understanding treeMaxDepth
The function treeMaxDepth is the main function that calls dfs and adjusts the depth by subtracting 1.

java
Copy
public static int treeMaxDepth(Node<Integer> root) {
    return (root != null) ? dfs(root) - 1 : 0;
}
What it does:

If the tree is not null, we call dfs(root) to find the depth of the tree. Since the dfs function counts the nodes starting from 1 (for the root), we subtract 1 because we want the depth to be zero-based (i.e., root node at level 0).

If the tree is empty (i.e., root == null), we return 0 because an empty tree has no depth.

Step 3: The main Function
java
Copy
public static void main(String[] args) {
    Node<Integer> root = BinaryTree.buildBinaryTree(Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5", "6", "7", "x", "x", "8", "x", "x", "9", "10"), new int[]{0});
    BinaryTree.inOrderTraversal(root, "  ");
    System.out.println();
    System.out.println(MaxDepthOfTheTree.treeMaxDepth(root));
}
This part is constructing a binary tree using the buildBinaryTree function, which takes a list of node values and some initial data.

Then, it calls inOrderTraversal to print the tree nodes in some order.

Finally, it calculates and prints the maximum depth of the tree using treeMaxDepth(root).

Why Is This Recursive?
This solution is recursive because the dfs function keeps calling itself to go deeper and deeper into the tree. The idea behind recursion is that you solve smaller sub-problems (in this case, finding the depth of subtrees) and then combine the results to get the final answer.

Let me explain recursion with an example:

Imagine a tree with this structure:

markdown
Copy
    1
   / \
  2   3
 / \
4   5
First, dfs is called on the root (node 1).

dfs(1) calls dfs(2) and dfs(3).

dfs(2) calls dfs(4) and dfs(5).

dfs(4) and dfs(5) return 0 because they are leaf nodes with no children (i.e., dfs(null)).

We then calculate the depth of node 2 as max(0, 0) + 1 = 1.

Then we calculate the depth of node 1 as max(1, 1) + 1 = 2.

And the process repeats for the rest of the tree. By the time we reach the deepest leaf node, the recursion will have processed all the way down, and we can return the total depth.

Simple Recursion Example:
To make the concept clearer, here is a small example:

Let's say you have a simple tree like this:

css
Copy
    A
   / \
  B   C
Step 1: Start at node A. We need to check both its left (B) and right (C) children.

Step 2: Move to B, and check if it has any children. Since B is a leaf node (no children), its depth is 0.

Step 3: Move to C, and check if it has any children. Since C is also a leaf node (no children), its depth is 0.

Step 4: Now go back to A, and calculate its depth: max(0, 0) + 1 = 1. So, the depth of A is 1.

Recursion Simplified:
In simple words, recursion lets you solve a problem by breaking it into smaller sub-problems. You keep calling the same function for smaller parts of the problem until you reach the simplest case (base case), and then you combine the results to get the final answer.

Conclusion:
Recursive solution: You're breaking down the problem of finding the maximum depth into smaller parts, checking the left and right subtrees, and combining their depths.

dfs function: This function works by going deep into the tree, and every time it reaches a leaf, it starts returning the depths back up the tree.

Maximum Depth: The final result is the maximum depth of the tree, which is the longest path from the root to any leaf.

This approach efficiently calculates the maximum depth of a binary tree by utilizing recursion, which is a natural fit for problems involving tree traversal.
 */
