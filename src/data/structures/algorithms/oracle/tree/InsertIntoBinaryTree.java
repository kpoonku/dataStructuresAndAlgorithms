package data.structures.algorithms.oracle.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertIntoBinaryTree {

    public static Node<Integer> insertBst(Node<Integer> bst, int value) {
        if (bst == null) {
            return new Node<>(value);
        }
        if (value > bst.value) {
            bst.right = insertBst(bst.right, value);
        } else if (value < bst.value) {
            bst.left = insertBst(bst.left, value);
        }
        return bst;
    }

    public static <T> void formatTree(Node<T> node, List<String> out) {
        if (node == null) {
            out.add("x");
            return;
        }
        out.add(node.value.toString());
        formatTree(node.left, out);
        formatTree(node.right, out);
    }

    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree(
                Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5", "6", "7",
                        "x", "x", "8", "x", "x", "9", "10"), new int[]{0});
        BinaryTree.inOrderTraversal(root, "  ");
        System.out.println("New Line");
        //int treeHeight = CheckForBalancedTree.checkForBalancedTree(root);
        root = insertBst(root, 11);
        root = insertBst(root, 12);
        BinaryTree.inOrderTraversal(root, " ");
        ArrayList<String> resArr = new ArrayList<>();
        formatTree(root, resArr);
        System.out.println(String.join(" ", resArr));
    }
}
