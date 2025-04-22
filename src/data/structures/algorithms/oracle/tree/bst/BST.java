package data.structures.algorithms.oracle.tree.bst;

public class BST {
    // Method to insert a value into the BST
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // If the tree is empty, create a new node and return it.
        if (root == null) {
            return new TreeNode(val);
        }

        // If the value is smaller, insert in the left subtree
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        }
        // If the value is greater, insert in the right subtree
        else {
            root.right = insertIntoBST(root.right, val);
        }

        // Return the root of the modified tree
        return root;
    }

    // Method to build the BST from an array
    public TreeNode insertFromArray(int[] arr) {
        TreeNode root = null; // Initially, the tree is empty

        for (int val : arr) {
            root = insertIntoBST(root, val); // Insert each element into the tree
        }

        return root; // Return the root of the BST after all insertions
    }

    // Method to perform an in-order traversal (for testing purposes)
    public void inorderTraversal(TreeNode root) {
        if (root == null) return;
        inorderTraversal(root.left);
        System.out.print(root.val + " ");
        inorderTraversal(root.right);
    }

    public int closestValue(TreeNode root, double target) {
        int closestValue = root.val;
        while (root != null) {
            double diff = Math.abs(root.val - target);
            if(Math.abs(closestValue - target) > diff) {
                closestValue = root.val;
            }
            System.out.println();
            if (root.val > target) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return closestValue;
    }

    public static void main(String[] args) {
        BST bst = new BST();
        int[] arr = {7, 3, 15, 9, 20}; // Sample array

        // Build BST from the array
        TreeNode root = bst.insertFromArray(arr);

        // Print the in-order traversal of the tree (which should be sorted)
        bst.inorderTraversal(root); // Output should be: 3 7 9 15 20
        System.out.println(bst.closestValue(root, 11));
    }

}
