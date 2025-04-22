package data.structures.algorithms.oracle.bfs;

import data.structures.algorithms.oracle.bfs.BFS.Node;

import java.util.*;

import static data.structures.algorithms.oracle.bfs.BFS.inOrderTraversal;

public class SymmetricTree {
    public static boolean isSymmetric(Node<Integer> node) {
        Deque<Node<Integer>> deque = new ArrayDeque<>();
        deque.offer(node);
        if (node.left != null) deque.offer(node.left);
        if (node.right != null) deque.offer(node.right);
        while (!deque.isEmpty()) {
            Node<Integer> left = deque.poll();
            Node<Integer> right = deque.poll();
            if (left == null && right == null) {
                return false;
            }
            if (left == null || right == null) {
                return false;
            }
            if (!Objects.equals(left.val, right.val)) {
                return false;
            }
            deque.add(left.left);
            deque.add(right.right);
            deque.add(left.right);
            deque.add(right.left);
        }
        return true;
    }

    public static <T> Node<Integer> buildSymmetricTree(List<String> list, int[] index) {
        if (index[0] >= list.size()) return null;  // Check if the iterator has a next element
        String val = list.get(index[0]++);

        if (val.equals("x")) return null;

        // Build the left and right subtree, ensuring symmetry
        Node<Integer> left = buildSymmetricTree(list, index);   // Left child
        Node<Integer> right = buildSymmetricTree(list, index);  // Right child

        // Build the node and return it
        Node<Integer> node = new Node<>(Integer.parseInt(val), left, right);

        // Now enforce the mirror symmetry
        if (left != null && right != null) {
            // Ensure the left subtree mirrors the right subtree
            swap(left, right);
        }

        return node;
    }

    private static <T> void swap(Node<Integer> left, Node<Integer> right) {
        // Swap the left and right children of these nodes
        Node<Integer> temp = left.left;
        left.left = right.right;
        right.right = temp;
    }

    public static void main(String[] args) {
        Node<Integer> root = buildSymmetricTree(Arrays.asList("1", "2", "x", "x", "2"), new int[]{0});
        inOrderTraversal(root, "   ");
        System.out.println("Is it symmetric : " + isSymmetric(root));
    }
}
