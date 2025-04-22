package data.structures.algorithms.oracle.tree;

import java.util.Arrays;

public class LowestCommonAncestorOnBst {
    public static int lcaOnBst(Node<Integer> bst, int p, int q) {
        if (p < bst.value && q < bst.value) {
            return lcaOnBst(bst.left, p, q);
        } else if (p > bst.value && q > bst.value) {
            return lcaOnBst(bst.right, p, q);
        } else {
            return bst.value;
        }
    }

    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree(Arrays.asList("5", "3", "2", "1", "x", "x"), new int[]{0});
        BinaryTree.inOrderTraversal(root, "  ");
        System.out.println();
        System.out.println("LCA of BST : " + lcaOnBst(root, 2, 3));
    }
}
