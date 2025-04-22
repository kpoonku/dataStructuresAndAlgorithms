package data.structures.algorithms.oracle.tree.bst;

public class N700SearchBST {
    public static TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (root.val == val) {
            return root;
        }
        if (root.val > val) {
            return searchBST(root.left, val);
        }
        return searchBST(root.right, val);
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] arr = {7, 3, 15, 9, 20}; // Sample array

        // Build BST from the array
        TreeNode root = bst.insertFromArray(arr);

        // Print the in-order traversal of the tree (which should be sorted)
        bst.inorderTraversal(root); // Output should be: 3 7 9 15 20
        System.out.println("Closest Value : " + bst.closestValue(root, 11));
        System.out.println("Searched Node : " + searchBST(root, 15).val);
    }
}
