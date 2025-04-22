package data.structures.algorithms.oracle.tree;

import java.util.Arrays;

import static java.lang.Integer.MIN_VALUE;

public class FindMaxValueInBinaryTree {
    private static Integer maxValue = MIN_VALUE;
    public static Integer findMaxValue(Node<Integer> root) {
        if (root == null) {
            return maxValue;
        }
        if( root.value > maxValue) {
           maxValue =  root.value;
        }
        findMaxValue(root.left);
        findMaxValue(root.right);
        return maxValue;
    }

    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree(Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5", "6", "7", "x", "x", "8", "x", "x", "9", "10"), new int[]{0});
        //BinaryTree.inOrderTraversal(root, "  ");
        System.out.println();
        System.out.println(FindMaxValueInBinaryTree.findMaxValue(root));
    }
}
