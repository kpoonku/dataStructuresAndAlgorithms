package data.structures.algorithms.binary.tree.bfs;

import data.structures.algorithms.oracle.tree.BinaryTree;
import data.structures.algorithms.oracle.tree.Node;

import java.util.*;

public class N199RightSideView {
    public static List<Integer> rightSideView(Node<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node<Integer> currentNode = queue.poll();
                if (currentNode == null) {
                    return result;
                }
                if (i == size - 1) {
                    result.add(currentNode.value);
                }
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree(Arrays.asList("5", "3", "2", "1", "x", "x"), new int[]{0});
        BinaryTree.inOrderTraversal(root, "  ");
        List<Integer> result = rightSideView(root);
        System.out.println(result);
    }
}
/*
### Problem Breakdown

The problem is asking us to return the list of node values that are visible
 when we stand on the **right side** of the binary tree. The right side view of a
 binary tree consists of the rightmost node at each level. For example, the rightmost node
 on the first level is the root, and the rightmost node on the second level is the rightmost
 child of the root, and so on.

### Approach

To solve this problem, we can perform a **Level Order Traversal** (or **Breadth-First Search
(BFS)**) of the binary tree and keep track of the rightmost node at each level.
This ensures that, at each level, we will capture the last node we encounter,
which will be the node visible from the right side.

### Key Steps:

1. **Level Order Traversal (BFS)**:
   - Start from the root of the tree and explore all the nodes level by level.
   - For each level, we will explore all nodes at that level, and the **rightmost**
   node at each level will be visible from the right side.

2. **Track Rightmost Node at Each Level**:
   - As we explore each level, we add the last node we encounter at that level to our result.

3. **Edge Case**:
   - If the root is `null`, return an empty list as there are no nodes in the tree.

### Algorithm:

1. Use a **Queue** for level-order traversal (BFS).
2. For each level, track the rightmost node and add it to the result.
3. Process all nodes until the tree is fully traversed.

### Code Implementation (in Java):

```java
import java.util.*;

public class RightSideView {

    // TreeNode class definition
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        // Constructor for TreeNode
        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }

    // Function to get the right side view of the binary tree
    public static List<Integer> rightSideView(TreeNode root) {
        // List to store the right side view
        List<Integer> result = new ArrayList<>();

        // If the tree is empty, return an empty list
        if (root == null) {
            return result;
        }

        // Queue for level order traversal (BFS)
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);  // Start with the root node

        // Perform level order traversal
        while (!queue.isEmpty()) {
            int size = queue.size();  // Number of nodes at the current level

            // Traverse all nodes at the current level
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = queue.poll();

                // If it's the last node at this level, add it to the result
                if (i == size - 1) {
                    result.add(currentNode.val);
                }

                // Add the left and right children of the current node to the queue
                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(5);
        root1.right.right = new TreeNode(4);
        System.out.println("Right side view: " + rightSideView(root1));  // Output: [1, 3, 4]

        // Example 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(3);
        root2.left.left = new TreeNode(4);
        root2.right.right = new TreeNode(5);
        System.out.println("Right side view: " + rightSideView(root2));  // Output: [1, 3, 5]

        // Example 3
        TreeNode root3 = new TreeNode(1);
        root3.right = new TreeNode(3);
        System.out.println("Right side view: " + rightSideView(root3));  // Output: [1, 3]

        // Example 4 (Empty tree)
        TreeNode root4 = null;
        System.out.println("Right side view: " + rightSideView(root4));  // Output: []
    }
}
```

### **Explanation of Code:**

1. **TreeNode class**:
   - This class represents a single node in the binary tree. Each node has a value (`val`), a left child (`left`), and a right child (`right`).

2. **rightSideView method**:
   - The method takes the root node of the tree as input and returns a list of integers representing the right side view of the tree.
   - A `Queue` is used to perform level-order traversal (BFS). We process nodes level by level and, for each level, we add the last node (rightmost node) to the result list.

3. **Main method**:
   - We create four examples of binary trees with different structures:
     - In the first example, the binary tree is `[1,2,3,null,5,null,4]`.
     - In the second example, the binary tree is `[1,2,3,4,null,null,null,5]`.
     - In the third example, the binary tree is `[1,null,3]`.
     - In the fourth example, the tree is empty.
   - We call the `rightSideView` method on each example and print the result.

### **Time Complexity**:
- **O(N)** where **N** is the number of nodes in the tree. This is because we are visiting each node exactly once during the level-order traversal.

### **Space Complexity**:
- **O(N)** for storing the queue during level-order traversal. In the worst case, the queue can contain all the nodes at the last level of the tree, which can be half of the total number of nodes in a complete binary tree.

### **Summary**:
- The solution uses **Level Order Traversal (BFS)** to explore each level of the tree.
- The rightmost node at each level is added to the result list.
- This approach ensures that we capture the nodes visible from the right side of the tree.
 */
