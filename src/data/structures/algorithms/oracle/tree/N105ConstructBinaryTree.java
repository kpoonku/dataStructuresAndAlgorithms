package data.structures.algorithms.oracle.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N105ConstructBinaryTree {

    private static final StringBuilder treeNodeBuilder = new StringBuilder();

    public static Node<Integer> constructBinaryTree
            (List<Integer> preorder, List<Integer> inorder) {
        String preOrderString = preorder.toString()
                .replace("[", "")
                .replace("]", "");
        String inOrderString = inorder.toString()
                .replace("[", "")
                .replace("]", "");
        String[] result = constructBinaryTree(preOrderString, inOrderString)
                .toString().split(",\\s*");
        return BinaryTree.buildBinaryTree(Arrays.asList(result), new int[]{0});
    }

    public static StringBuilder constructBinaryTree
            (String preOrderString, String inOrderString) {
        System.out.println("PreOrder - " + preOrderString);
        System.out.println("InOrder - " + inOrderString);
        System.out.println();
        String[] sidesOfTree = inOrderString.split(preOrderString.charAt(0) + ",");
        if (sidesOfTree.length == 1) {
            return treeNodeBuilder.append(preOrderString);
        }
        sidesOfTree[0] += preOrderString.charAt(0);
        sidesOfTree[1] = sidesOfTree[1].substring(1);
        String tempString = preOrderString.substring(0, sidesOfTree[0].length());
        preOrderString = preOrderString.substring(sidesOfTree[0].length() + 1);
        treeNodeBuilder.append
                (tempString.replace(inOrderString.charAt(0) + "",
                        inOrderString.charAt(0) + ", x, x")).append(", x, x, ");
        return constructBinaryTree(preOrderString.trim(), sidesOfTree[1].trim());
    }

    public static List<Integer> inOrderTraversal
            (Node<Integer> root, List<Integer> inOrderList) {
        if (root != null) {
            inOrderTraversal(root.left, inOrderList);
            inOrderList.add(root.value);
            inOrderTraversal(root.right, inOrderList);
        }
        return inOrderList;
    }

    public static List<Integer> preOrderTraversal
            (Node<Integer> root, List<Integer> preOrderList) {
        if (root != null) {
            preOrderList.add(root.value);
            preOrderTraversal(root.left, preOrderList);
            preOrderTraversal(root.right, preOrderList);
        }
        return preOrderList;
    }

    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree(
                Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5",
                        "6", "7", "x", "x", "8", "x", "x", "9", "10", "11", "12"), new int[]{0});
        List<Integer> inOrderList = inOrderTraversal(root, new ArrayList<>());
        List<Integer> preOrderList = preOrderTraversal(root, new ArrayList<>());
        Node<Integer> root1 = N105ConstructBinaryTree.constructBinaryTree(preOrderList, inOrderList);
        System.out.println("New Pre Order Traversal : ");
        BinaryTree.preOrderTraversal(root1);
    }
}
/*
105. Construct Binary Tree from Preorder and Inorder Traversal
Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary
 tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

Example 1:
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]

Example 2:
Input: preorder = [-1], inorder = [-1]
Output: [-1]

Constraints:
1 <= preorder.length <= 3000
inorder.length == preorder.length
-3000 <= preorder[i], inorder[i] <= 3000
preorder and inorder consist of unique values.
Each value of inorder also appears in preorder.
preorder is guaranteed to be the preorder traversal of the tree.
inorder is guaranteed to be the inorder traversal of the tree.
 */
/*
Great! Let’s explain **Leetcode 105: Construct Binary Tree from Preorder and Inorder Traversal**
in a **very simple, beginner-friendly** way, step by step — as if you're just starting out with
binary trees.
---
## 🌳 What Is This Problem About?
You’re given two lists:
* `preorder`: This tells you the order of nodes visited in **preorder traversal** (root → left → right)
* `inorder`: This tells you the order of nodes visited in **inorder traversal** (left → root → right)
Your task is to **rebuild the original binary tree** from these two lists.
---
## 🧠 What Are Preorder and Inorder Traversals?
For a binary tree like this:
```
      3
     / \
    9   20
        / \
       15  7
```
* **Preorder** (Root → Left → Right): `3, 9, 20, 15, 7`
* **Inorder** (Left → Root → Right): `9, 3, 15, 20, 7`
---
## 🎯 What’s the Idea?
We will **build the tree recursively** using these rules:
### Rule 1: Preorder\[0] is always the **root** of the tree.
* In our example: `preorder[0] = 3` → this is the root of the tree.
### Rule 2: Find this root in the **inorder** list.
* In our example: `inorder = [9, 3, 15, 20, 7]`
* 3 is at index 1
### Rule 3: The elements **left of root in inorder** belong to the **left subtree**
The elements **right of root in inorder** belong to the **right subtree**
So:
* Left Inorder = `[9]`
* Right Inorder = `[15, 20, 7]`
### Rule 4: Use **preorder** to know the **order of nodes** to insert.
After removing the root (3), preorder becomes: `[9, 20, 15, 7]`
* Left Preorder = `[9]` → for left subtree
* Right Preorder = `[20, 15, 7]` → for right subtree
---
### 🔁 Then Repeat:
* Build root → split inorder → split preorder → build subtrees recursively
---
## 🔧 Visualizing the Example
Given:
```plaintext
Preorder = [3, 9, 20, 15, 7]
Inorder  = [9, 3, 15, 20, 7]
```
### Step-by-step:
1. `preorder[0] = 3` → Root
2. In `inorder`, 3 is at index 1:
   * Left: `inorder[0] = 9`
   * Right: `inorder[2:] = [15, 20, 7]`
3. Use preorder to get:
   * Left: `preorder[1] = 9`
   * Right: `preorder[2:] = [20, 15, 7]`
4. Repeat the process for 9, then for 20, and so on...
---
## ✅ Final Tree:
```
        3
       / \
      9   20
          / \
         15  7
```
---
## 📌 Beginner Tips:
* **Preorder** gives you the **root** at each level
* **Inorder** tells you **where the root splits** the left and right parts
* You keep **building subtrees** using these two rules
---
*/