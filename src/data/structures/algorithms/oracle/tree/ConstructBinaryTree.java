package data.structures.algorithms.oracle.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstructBinaryTree {

    private static StringBuilder treeNodeBuilder = new StringBuilder();
    public static Node<Integer> constructBinaryTree(List<Integer> preorder, List<Integer> inorder) {
        String preOrderString = preorder.toString().replace("[", "").replace("]", "");
        String inOrderString = inorder.toString().replace("[", "").replace("]", "");
        String[] result = constructBinaryTree(preOrderString, inOrderString).toString().split(",\\s*");
        return BinaryTree.buildBinaryTree(Arrays.asList(result), new int[]{0});
    }

    public static StringBuilder constructBinaryTree(String preOrderString, String inOrderString) {
        System.out.println("PreOrder - " + preOrderString);
        System.out.println("InOrder - " + inOrderString);
        System.out.println();
        String[] sidesOfTree = inOrderString.split(preOrderString.charAt(0) + ",");
        if(sidesOfTree.length == 1) {
            return treeNodeBuilder.append(preOrderString);
        }
        sidesOfTree[0] += preOrderString.charAt(0);
        sidesOfTree[1] = sidesOfTree[1].substring(1);
        String tempString = preOrderString.substring(0, sidesOfTree[0].length());
        preOrderString = preOrderString.substring(sidesOfTree[0].length()+1);
        treeNodeBuilder.append(tempString.replace(inOrderString.charAt(0)+"",
                inOrderString.charAt(0) + ", x, x"))
                .append(", x, x, ");
        return constructBinaryTree(preOrderString.trim(), sidesOfTree[1].trim());
    }

    public static List<Integer> inOrderTraversal(Node<Integer> root, List<Integer> inOrderList) {
        if (root != null) {
            inOrderTraversal(root.left, inOrderList);
            inOrderList.add(root.value);
            inOrderTraversal(root.right, inOrderList);
        }
        return inOrderList;
    }

    public static List<Integer> preOrderTraversal(Node<Integer> root, List<Integer> preOrderList) {
        if (root != null) {
            preOrderList.add(root.value);
            preOrderTraversal(root.left, preOrderList);
            preOrderTraversal(root.right, preOrderList);
        }
        return preOrderList;
    }

    public static void main(String[] args) {
        Node<Integer> root = BinaryTree.buildBinaryTree(Arrays.asList("1", "2", "3", "x", "x", "4", "x", "x", "5", "6", "7", "x", "x", "8", "x", "x", "9", "10", "11", "12"), new int[]{0});
        List<Integer> inOrderList = inOrderTraversal(root, new ArrayList<>());
        List<Integer> preOrderList = preOrderTraversal(root, new ArrayList<>());
        Node<Integer> root1 = ConstructBinaryTree.constructBinaryTree(preOrderList, inOrderList);
        System.out.println("New Pre Order Traversal : ");
        BinaryTree.preOrderTraversal(root1);
    }
}
