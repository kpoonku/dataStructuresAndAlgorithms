package data.structures.algorithms.oracle.tree.bst;

public class N450BSTDeleteNode {
    public static TreeNode deleteNodeTry(TreeNode node, int key) {
        if (node == null) {
            return null;
        }
        if (node.val > key) {
            node = deleteNodeTry(node.left, key);
        } else if (node.val < key) {
            node = deleteNodeTry(node.right, key);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            TreeNode successor = findMin(node.right);
            node.val = successor.val;
            node.right = deleteNodeTry(node.right, successor.val);
        }
        return node;
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;  // Base case: root is null

        if (key < root.val) {
            root.left = deleteNode(root.left, key);  // Go left
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);  // Go right
        } else {  // Node to delete is found
            // Case 1: No left child
            if (root.left == null) return root.right;
            // Case 2: No right child
            if (root.right == null) return root.left;

            // Case 3: Both children present
            TreeNode successor = findMin(root.right);  // Find in-order successor
            root.val = successor.val;  // Replace root with successor value
            root.right = deleteNode(root.right, successor.val);  // Delete successor
        }
        return root;
    }

    private static TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public static void inOrderTraversal(TreeNode root, String indent_level) {
        //Left, Node/Root, Right (LNR)
        String currentIndentLevel = " ";
        if (root != null) {
            currentIndentLevel = indent_level + indent_level;
            inOrderTraversal(root.left, currentIndentLevel);
            System.out.println(currentIndentLevel + root.val);
            inOrderTraversal(root.right, currentIndentLevel);
        }
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] arr = {7, 2, 3, 1, 15, 9, 20}; // Sample array
        //int[] arr = {2, 3, 1, 4, 5, 6, 7, 8, 9, 10, 11};

        // Build BST from the array
        TreeNode root = bst.insertFromArray(arr);
        inOrderTraversal(root, "  ");
       // System.out.println(bst.closestValue(root, 11));
        TreeNode node = deleteNode(root, 3);
        inOrderTraversal(root, "  ");
    }
}

