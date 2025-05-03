package data.structures.algorithms.binary.tree.dfs;

import data.structures.algorithms.oracle.tree.BinaryTree;
import data.structures.algorithms.oracle.tree.Node;

import java.util.Arrays;

public class N236LeastCommonAncestor {
    public static Node<Integer> lowestCommonAncestor
            (Node<Integer> root, int p, int q) {
        if (root == null) {
            return root;
        }
        if (root.value == p || root.value == q) {
            return root;
        }
        Node<Integer> left = lowestCommonAncestor(root.left, p, q);
        Node<Integer> right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree
                (Arrays.asList("5", "3", "x", "2", "1", "x", "x"), new int[]{0});
        BinaryTree.inOrderTraversal(root, "  ");
        System.out.println();
        System.out.println("LCA of BST : " + lowestCommonAncestor(root, 2, 3).value);
    }
}
/*
Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined
between two nodes p and q as the lowest node in T that has both p and q as descendants
(where we allow a node to be a descendant of itself).”

Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself
according to the LCA definition.

Example 3:
Input: root = [1,2], p = 1, q = 2
Output: 1
Constraints:
The number of nodes in the tree is in the range [2, 105].
-109 <= Node.val <= 109
All Node.val are unique.
p != q
p and q will exist in the tree.
*/
/*
Let's break down the logic to help you understand the concept of finding the
**Lowest Common Ancestor (LCA)** in a binary tree, and how we can create the function
step by step in Java. We'll explain it in a way that makes it easier for you to follow and
implement.

### **What is the Lowest Common Ancestor (LCA)?**
- The **Lowest Common Ancestor** of two nodes in a binary tree is the lowest node in the tree
that has both of the given nodes as descendants.
- In other words, it's the most recent common node that both nodes share as you travel upward
in the tree from both nodes.

### **How can we find the LCA?**
To find the LCA, we can use the **Recursive approach**. The idea is to traverse the tree and
check if the nodes we are looking for are in the current node's left or right subtree.

### **Steps for the Recursive Approach:**

1. **Base Case**:
   - If the current node is **null**, it means we've reached a leaf node (or the tree is empty).
   In this case, return `null` because there is no LCA here.
   - If the current node is **equal to either `p` or `q`**, it means we've found one of the nodes.
   In this case, return the current node because it's part of the path toward the LCA.

2. **Recursive Exploration**:
   - Call the function **recursively** on the **left subtree** and **right subtree**.
   - We do this because we want to find `p` and `q` somewhere in the tree. We need to check both
   subtrees to figure out where they are.

3. **Determine the LCA**:
   - If both left and right subtrees return a non-null value, that means `p` and `q` are located
   in different subtrees. So, the current node must be the **LCA** because it's the first node
   where their paths diverge.
   - If only one of the subtrees returns a non-null value, it means both `p` and `q` are located
   in the same subtree. So, we return the non-null value because that will be the ancestor.

### **Example Walkthrough**:
Let's walk through the process using an example:

Consider the binary tree:

```
       3
     /   \
    5     1
   / \   / \
  6   2 0   8
     / \
    7   4
```

We want to find the LCA of nodes `5` and `1`.

1. Start at the root node, `3`. Check if `3` is equal to `5` or `1`. It's not, so continue the
search.
2. Recursively search the left subtree of `3` (node `5`):
   - Check if `5` is `p` or `q`. It matches `5`, so return `5` as a potential LCA.
3. Now, recursively search the right subtree of `3` (node `1`):
   - Check if `1` is `p` or `q`. It matches `1`, so return `1` as a potential LCA.
4. Now both left and right subtrees of `3` have returned non-null values (`5` from the left and
`1` from the right). This means `5` and `1` are in different subtrees of `3`, so `3` is the
**LCA**.

### **Code Implementation:**

```java
public class BinaryTree {

    // Define the TreeNode class
    public static class TreeNode {
        int val;
        TreeNode left, right;

        // Constructor for TreeNode
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    // Recursive function to find the LCA
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // Base case: If the root is null, return null
        if (root == null) {
            return null;
        }

        // If we find either p or q, return the current node
        if (root == p || root == q) {
            return root;
        }

        // Recur for the left and right subtree
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // If both left and right are non-null, the current node is the LCA
        if (left != null && right != null) {
            return root;
        }

        // If only one of the sides is non-null, return the non-null side
        return left != null ? left : right;
    }

    public static void main(String[] args) {
        // Build a simple binary tree
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        TreeNode p = root.left;  // Node 5
        TreeNode q = root.right; // Node 1

        // Find the LCA of nodes 5 and 1
        TreeNode lca = lowestCommonAncestor(root, p, q);
        System.out.println("LCA of 5 and 1: " + lca.val); // Output should be 3
    }
}
```

### **Explanation of Code:**

1. **TreeNode class**: Represents each node in the binary tree. It contains a value (`val`),
and pointers to the left and right child nodes.

2. **lowestCommonAncestor method**:
   - If the `root` is `null`, there is no ancestor, so we return `null`.
   - If the `root` matches either `p` or `q`, we return the root node because we've found one
   of the target nodes.
   - We recursively call `lowestCommonAncestor` on the left and right subtrees.
   - If both left and right subtrees return non-null values, the current node is the **LCA**,
   so we return the root node.
   - If only one subtree returns a non-null value, return that value because it is the common
   ancestor.

3. **main method**:
   - Creates a binary tree manually by linking nodes.
   - Calls the `lowestCommonAncestor` method with two nodes (`p` and `q`).
   - Prints the LCA value.

### **Time Complexity**:
- The time complexity is **O(N)**, where **N** is the number of nodes in the tree because we
are visiting each node exactly once.

### **Space Complexity**:
- The space complexity is **O(H)**, where **H** is the height of the tree due to the recursive
stack.

### **Summary**:
- This approach uses recursion to efficiently search for the lowest common ancestor by checking
both subtrees for the given nodes.
- The function works by recursively dividing the tree into smaller subtrees until the nodes are
found.
- The algorithm has a time complexity of **O(N)** and space complexity of **O(H)**, which is
efficient for large trees.

By understanding this process, you can now implement the function to find the Lowest Common
Ancestor in a binary tree.
 */