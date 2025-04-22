package data.structures.algorithms.oracle.tree;

import java.util.Arrays;
import java.util.List;

public class BinaryTree {
    public static Node<Integer> buildBinaryTree(List<String> list, int[] index) {
        if (index[0] >= list.size()) return null;  // Check if the iterator has a next element
        String val = list.get(index[0]++);
        if (val.equals("x")) return null;
        Node<Integer> left = buildBinaryTree(list, index);
        Node<Integer> right = buildBinaryTree(list, index);
        return new Node<>(Integer.parseInt(val), left, right);
    }

    public static void inOrderTraversal(Node<Integer> root, String indent_level) {
        //Left, Node/Root, Right (LNR)
        String currentIndentLevel = "";
        if (root != null) {
            currentIndentLevel = indent_level + indent_level;
            inOrderTraversal(root.left, currentIndentLevel);
            System.out.println(currentIndentLevel + root.value);
            inOrderTraversal(root.right, currentIndentLevel);
        }
    }

    public static void inOrderTraversal(Node<Integer> root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(" " + root.value);
            inOrderTraversal(root.right);
        }
    }

    public static void preOrderTraversal(Node<Integer> root) {
        //Root, Left, Right
        if (root != null) {
            System.out.print(root.value + " ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    public static void postOrderTraversal(Node<Integer> root) {
        // Right, Left, Root
        if (root != null) {
            postOrderTraversal(root.right);
            postOrderTraversal(root.left);
            System.out.print(root.value + " ");
        }
    }

    public static void main(String[] args) {
        Node<Integer> root = buildBinaryTree(Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5", "6", "7", "x", "x", "8", "x", "x", "9", "10"), new int[]{0});
        inOrderTraversal(root, "   ");
        System.out.println("Inorder");
        preOrderTraversal(root);
        System.out.println("PreOrder");
        postOrderTraversal(root);
        System.out.println("PostOrder");
    }
}
