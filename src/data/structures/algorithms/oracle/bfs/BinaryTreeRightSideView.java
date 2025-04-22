package data.structures.algorithms.oracle.bfs;

import java.util.*;

public class BinaryTreeRightSideView {
    public static List<Integer> binaryTreeRightSideView(Node<Integer> root) {
        List<Integer> nodeList = new ArrayList<>();
        if (root == null) {
            return nodeList;
        }
        Deque<Node<Integer>> deque = new ArrayDeque<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            nodeList.add(deque.peekLast().val);
            for (int i = 0; i < size; i++) {
                Node<Integer> node = deque.poll();
                if (node != null) {
                    if (node.left != null) deque.offer(node.left);
                    if (node.right != null) deque.offer(node.right);
                }
            }
        }
        return nodeList;
    }

    public static <T> Node<Integer> buildTree(List<String> list, int[] index) {
        if (index[0] >= list.size()) return null;  // Check if the iterator has a next element
        String val = list.get(index[0]++);
        if (val.equals("x")) return null;
        Node<Integer> left = buildTree(list, index);
        Node<Integer> right = buildTree(list, index);
        return new Node<>(Integer.parseInt(val), left, right);
    }

    public static void inOrderTraversal(Node<Integer> root, String indent_level) {
        String currentIndentLevel = "";
        if (root != null) {
            currentIndentLevel = indent_level + indent_level;
            inOrderTraversal(root.left, currentIndentLevel);
            System.out.println(currentIndentLevel + root.val);
            inOrderTraversal(root.right, currentIndentLevel);
        }
    }

    public static void main(String[] args) {
        Node<Integer> root = buildTree(Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5", "6", "7", "x", "x", "8", "x", "x", "9", "10"), new int[]{0});
        inOrderTraversal(root, "   ");
        //List<List<Integer>> res = levelOrderTraversal(root);
        //levelOrderTraversalOnlyRightMostNode(root);
        System.out.println("Right side view : " + binaryTreeRightSideView(root));
       /* for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }*/
    }

    public static class Node<T> {
        public final T val;
        public final Node<T> left;
        public final Node<T> right;

        public Node(T val) {
            this(val, null, null);
        }

        public Node(T val, Node<T> left, Node<T> right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
