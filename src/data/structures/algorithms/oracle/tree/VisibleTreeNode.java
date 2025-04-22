package data.structures.algorithms.oracle.tree;

import java.util.Arrays;

public class VisibleTreeNode {
    public static int visibleTreeNode(Node<Integer> root) {
        return dfs(root, Integer.MIN_VALUE);
    }

    public static int dfs(Node<Integer> root, int maxValue) {
        if (root == null) {
            return 0;
        }
        int total = 0;
        if (root.value >= maxValue) {
            total++;
            System.out.println(root.value + " - root, total : " + total);
        }
        total += dfs(root.left, Math.max(maxValue, root.value));
        total += dfs(root.right, Math.max(maxValue, root.value));
        return total;
    }

    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree(Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5", "6", "7", "x", "x", "8", "x", "x", "9", "10"), new int[]{0});
        //BinaryTree.inOrderTraversal(root, "  ");
        System.out.println();
        System.out.println(VisibleTreeNode.visibleTreeNode(root));
    }
}
