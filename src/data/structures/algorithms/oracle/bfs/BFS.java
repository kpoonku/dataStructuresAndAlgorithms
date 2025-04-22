package data.structures.algorithms.oracle.bfs;

import java.util.*;

public class BFS {
    public static List<List<Integer>> levelOrderTraversalZigZagReverse(Node<Integer> root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node<Integer>> queue = new ArrayDeque<>();

        queue.add(root);
        int rowNumber = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node<Integer> node = queue.poll();
                if (node != null) {
                    levelList.add(node.val);
                    if (node.right != null) queue.add(node.right);
                    if (node.left != null) queue.add(node.left);
                }
            }
            if (rowNumber % 2 == 0) {
                Collections.reverse(levelList);
            }
            System.out.println(levelList);
            result.add(levelList);
            rowNumber++;
        }
        return result;
    }

    public static int binaryTreeMinDepth(Node<Integer> root) {
        int depth = -1;
        if (root == null) {
            return depth;
        }
        Queue<Node<Integer>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            depth++;
            for (int i = 0; i < size; i++) {
                Node<Integer> node = queue.poll();
                if (node != null) {
                    if(node.left == null && node.right == null) {
                        return depth;
                    }
                    if (node.right != null) queue.add(node.right);
                    if (node.left != null) queue.add(node.left);
                }
            }
        }
        System.out.println("depth : " + depth);
        return depth;
    }

    public static List<Integer> levelOrderTraversalOnlyRightMostNode(Node<Integer> root) {
        List<Integer> levelList = new ArrayList<>();
        if (root == null) {
            return levelList;
        }
        Queue<Node<Integer>> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            levelList.add(queue.peek().val);
            for (int i = 0; i < size; i++) {
                Node<Integer> node = queue.poll();
                if (node != null) {
                    if (node.right != null) queue.add(node.right);
                    if (node.left != null) queue.add(node.left);
                }
            }
            System.out.println(levelList);
        }
        return levelList;
    }

    public static List<List<Integer>> levelOrderTraversal(Node<Integer> root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node<Integer>> queue = new ArrayDeque<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node<Integer> node = queue.poll();
                if (node != null) {
                    levelList.add(node.val);
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
            }
            System.out.println(levelList);
            result.add(levelList);
        }
        return result;
    }

    // this function builds a tree from input; you don't have to modify it
// learn more about how trees are encoded in https://algo.monster/problems/serializing_tree
    public static <T> Node<Integer> buildTree(List<String> list, int[] index) {
        if (index[0] >= list.size()) return null;  // Check if the iterator has a next element
        String val = list.get(index[0]++);
        if (val.equals("x")) return null;
        Node<Integer> left = buildTree(list, index);
        Node<Integer> right = buildTree(list, index);
        return new Node<>(Integer.parseInt(val), left, right);
    }

    public static List<String> splitWords(String s) {
        return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
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
        System.out.println("Depth : " + binaryTreeMinDepth(root));
       /* for (List<Integer> row : res) {
            System.out.println(row.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }*/
    }

    public static class Node<T> {
        public T val;
        public Node<T> left;
        public Node<T> right;

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
