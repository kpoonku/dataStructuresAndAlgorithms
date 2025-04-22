package data.structures.algorithms.oracle.tree;

import java.util.Arrays;

public class SubtreeOfAnotherTree {
    public static boolean subtreeOfAnotherTree(Node<Integer> root,
                                               Node<Integer> subRoot) {
        String order1 = inorderTraversal(root, new StringBuilder());
        System.out.println(order1);
        String order2 = inorderTraversal(subRoot, new StringBuilder());
        System.out.println(order2);
        return order1.contains(order2);
    }

    public static String inorderTraversal(Node<Integer> root, StringBuilder inOrderBuilder) {
        if(root == null) {
            return inOrderBuilder.toString();
        }
        inOrderBuilder.append(root.value);
        inorderTraversal(root.left, inOrderBuilder);
        inorderTraversal(root.right, inOrderBuilder);
        return inOrderBuilder.toString();
    }

    public static void main(String[] args) {
        Node<Integer> root1 = BinaryTree.buildBinaryTree(Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5", "6", "7", "x", "x", "8", "x", "x", "9", "10"), new int[]{0});
        Node<Integer> root2 = BinaryTree.buildBinaryTree(Arrays.asList("5", "6", "7", "x", "x", "8", "x", "x", "9", "10"), new int[]{0});
        System.out.println("is it a sub Tree : " + SubtreeOfAnotherTree.subtreeOfAnotherTree(root1, root2));
    }
}
