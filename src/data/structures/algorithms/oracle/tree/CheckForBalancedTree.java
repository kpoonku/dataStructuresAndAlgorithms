package data.structures.algorithms.oracle.tree;

import java.util.Arrays;

public class CheckForBalancedTree {
    public static int checkForBalancedTree(Node<Integer> root) {
        if(root == null) {
            return 0;
        }
        int leftHeight = checkForBalancedTree(root.left);
        int rightHeight = checkForBalancedTree(root.right);
        if(leftHeight == -1 || rightHeight == -1) {
            return -1;
        }
        if(Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static boolean isBalanced(Node<Integer> tree) {
        return checkForBalancedTree(tree) != -1;
    }

    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree(Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5", "6", "7", "x", "x", "8", "x", "x", "9", "10"), new int[]{0});
        //BinaryTree.inOrderTraversal(root, "  ");
        System.out.println();
        //int treeHeight = CheckForBalancedTree.checkForBalancedTree(root);
        System.out.println("Is it balanced : " + isBalanced(root));
    }
}
