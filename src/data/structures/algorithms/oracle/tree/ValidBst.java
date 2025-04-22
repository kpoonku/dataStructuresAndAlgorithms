package data.structures.algorithms.oracle.tree;

import java.util.Arrays;

public class ValidBst {
    private static boolean dfs(Node<Integer> root, int min, int max) {
        // empty nodes are always valid
        if (root == null) return true;
        if (!(min < root.value && root.value < max)) {
            return false;
        }
        return dfs(root.left, min, root.value) && dfs(root.right, root.value, max);
    }

    public static boolean validBst(Node<Integer> root) {
        // root is always valid
        return dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree(Arrays.asList("5", "3", "2", "1", "x", "x"), new int[]{0});
        BinaryTree.inOrderTraversal(root, "  ");
        System.out.println();
        System.out.println("Is a valid BST : " + validBst(root));
    }
}
