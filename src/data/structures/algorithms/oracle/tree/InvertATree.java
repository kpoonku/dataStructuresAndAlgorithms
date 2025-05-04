package data.structures.algorithms.oracle.tree;

import java.util.Arrays;

public class InvertATree {
    public static void mirror(Node<Integer> root) {
        if( root == null) return;
        mirror(root.left);
        mirror(root.right);
        Node<Integer> tempNode = root.left;
        root.left = root.right;
        root.right = tempNode;
    }
    public static void inorderTraversal(Node<Integer> root) {
        if(root == null) {
            return ;
        }
        inorderTraversal(root.left);
        System.out.print(root.value + " ");
        inorderTraversal(root.right);
    }
    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree(Arrays.asList("1", "2", "3", "x", "x",
                "4", "x", "x", "5", "6", "7", "x", "x", "8", "x", "x", "9", "10"), new int[]{0});
        inorderTraversal(root);
        System.out.println();
        BinaryTree.preOrderTraversal(root);
        System.out.println();
        mirror(root);
        inorderTraversal(root);
    }
}
