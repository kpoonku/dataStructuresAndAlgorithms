package data.structures.algorithms.binary.tree.dfs;

import data.structures.algorithms.oracle.tree.Node;

import java.util.*;

import static data.structures.algorithms.oracle.tree.BinaryTree.buildBinaryTree;

public class N872LeafSimilar {
    Node<Integer> root;

    // Constructor
    public N872LeafSimilar() {
        root = null;
    }

    public static Set<Integer> getAllLeafNodes(Node<Integer> root) {
        Queue<Node<Integer>> queue = new LinkedList<>();
        queue.offer(root);
        Set<Integer> leafList = new HashSet<>();
        while (!queue.isEmpty()) {
            Node<Integer> node = queue.poll();
            Node<Integer> left = node.left;
            Node<Integer> right = node.right;
            if (left != null) {
                queue.offer(left);
            }
            if (right != null) {
                queue.offer(right);
            }
            if (left == null && right == null) {
                leafList.add(node.value);
            }
        }
        return leafList;
    }

    public static void main(String[] args) {
        Node<Integer> root = buildBinaryTree(Arrays.asList("3", "5", "7"), new int[]{0});
        inOrderTraversal(root);
        Node<Integer> root1 = buildBinaryTree(Arrays.asList("2", "3", "8"), new int[]{0});
        inOrderTraversal(root);
        System.out.println("Is Leaf Similar : " + leafSimilar(root, root1));
    }

    // In-order traversal (left, root, right)
    public static void inOrderTraversal(Node<Integer> node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.print(node.value + " ");
            inOrderTraversal(node.right);
        }
    }

    public static boolean leafSimilar(Node<Integer> root1, Node<Integer> root2) {
        Set<Integer> leaf1 = getAllLeafNodes(root1);
        Set<Integer> leaf2 = getAllLeafNodes(root2);
        return leaf2.containsAll(leaf1);
    }

    // Method to insert nodes into the tree (simple way of building a binary tree)
    public void insert(int value) {
        root = insertRec(root, value);
    }

    // A recursive function to insert a new node
    private Node<Integer> insertRec(Node<Integer> root, int value) {
        // If the tree is empty, return a new Node<Integer>
        if (root == null) {
            root = new Node<Integer>(value);
            return root;
        }

        // Otherwise, recur down the tree
        if (value < root.value) {
            root.left = insertRec(root.left, value);
        } else if (value > root.value) {
            root.right = insertRec(root.right, value);
        }

        // Return the (unchanged) node pointer
        return root;
    }

    // Pre-order traversal (root, left, right)
    public void preOrderTraversal(Node<Integer> node) {
        if (node != null) {
            System.out.print(node.value + " ");
            preOrderTraversal(node.left);
            preOrderTraversal(node.right);
        }
    }

    // Post-order traversal (left, right, root)
    public void postOrderTraversal(Node<Integer> node) {
        if (node != null) {
            postOrderTraversal(node.left);
            postOrderTraversal(node.right);
            System.out.print(node.value + " ");
        }
    }

    // To print the tree in a specific order, you can use one of the traversal methods.
    public void printTree() {
        System.out.println("In-order Traversal:");
        inOrderTraversal(root);
        System.out.println("\nPre-order Traversal:");
        preOrderTraversal(root);
        System.out.println("\nPost-order Traversal:");
        postOrderTraversal(root);
        System.out.println();
    }
}
/*
872. Leaf-Similar Trees
Consider all the leaves of a binary tree, from left to right order,
the values of those leaves form a leaf value sequence.

For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
Two binary trees are considered leaf-similar if their leaf value sequence is the same.
Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.

Example 1:
Input: root1 = [3,5,1,6,2,9,8,null,null,7,4], root2 = [3,5,1,6,7,4,2,null,null,null,null,null,null,9,8]
Output: true

Example 2:
Input: root1 = [1,2,3], root2 = [1,3,2]
Output: false

Constraints:
The number of nodes in each tree will be in the range [1, 200].
Both of the given trees will have values in the range [0, 200].
*/