package data.structures.algorithms.oracle.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstructBinaryTreeRecursive {

    private static Node<Integer> buildTreeRecursive(List<Integer> preorder, Map<Integer, Integer> inorderIndexMap,
                                                    int preorderStart, int inorderStart, int size) {
        System.out.println("1 : preorderStart : " + preorderStart + " , inorderStart : " + inorderStart + " , size : " + size);
        if (size <= 0) {
            return null;
        }
        int rootValue = preorder.get(preorderStart);
        int inorderIndex = inorderIndexMap.get(rootValue);
        int leftSize = inorderIndex - inorderStart;

        System.out.println(" preorderStart :: " + (preorderStart + 1) + " , inorderStart :: "
                + inorderStart + " , leftSize ::  " + leftSize);
        Node<Integer> left = buildTreeRecursive(preorder, inorderIndexMap, preorderStart + 1, inorderStart,
                leftSize);
        System.out.println(" 2 preorderStart :: " + (preorderStart + 1 + leftSize) +
                " , inorderIndex ::" + (inorderIndex + 1) + " , leftSize :: " + (size - 1 - leftSize));
        Node<Integer> right = buildTreeRecursive(preorder, inorderIndexMap, preorderStart + 1 + leftSize,
                inorderIndex + 1, size - 1 - leftSize);

        return new Node<Integer>(rootValue, left, right);
    }

    public static Node<Integer> constructBinaryTree(List<Integer> preorder, List<Integer> inorder) {
        Map<Integer, Integer> inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.size(); ++i) {
            inorderIndexMap.put(inorder.get(i), i);
        }
        return buildTreeRecursive(preorder, inorderIndexMap, 0, 0, inorder.size());
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
        data.structures.algorithms.oracle.tree.Node<Integer> root = BinaryTree.buildBinaryTree(Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5", "6", "7", "x", "x", "8", "x", "x", "9", "10", "11", "12"), new int[]{0});
        List<Integer> inOrderList = N105ConstructBinaryTree.inOrderTraversal(root, new ArrayList<>());
        List<Integer> preOrderList = N105ConstructBinaryTree.preOrderTraversal(root, new ArrayList<>());
        System.out.println("InOrder : " + inOrderList.toString());
        System.out.println("PreOrder : " + preOrderList.toString());
        Node<Integer> res = constructBinaryTree(preOrderList, inOrderList);
        ArrayList<String> resArr = new ArrayList<>();
        formatTree(res, resArr);
        System.out.println(String.join(" ", resArr));
    }
}