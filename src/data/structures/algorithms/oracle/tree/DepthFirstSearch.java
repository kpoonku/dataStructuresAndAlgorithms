package data.structures.algorithms.oracle.tree;

import java.util.Arrays;

public class DepthFirstSearch {
    public static Node<Integer> dfs(Node<Integer> root, int target) {
        if (root == null) {
            return null;
        }
        System.out.println("visited Nodes: " + root.value);
        if (root.value == target) {
            return root;
        }
        Node<Integer> left = dfs(root.left, target);
        if (left != null) {
            return left;
        }
        return dfs(root.right, target);
    }

    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree(Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5", "6", "7", "x", "x", "8", "x", "x", "9", "10"), new int[]{0});
        BinaryTree.inOrderTraversal(root, "  ");
        System.out.println();
        System.out.println(DepthFirstSearch.dfs(root, 7));
    }
}
