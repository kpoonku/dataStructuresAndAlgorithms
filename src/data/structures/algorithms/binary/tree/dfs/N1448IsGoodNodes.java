package data.structures.algorithms.binary.tree.dfs;

import data.structures.algorithms.oracle.tree.Node;

public class N1448IsGoodNodes {
    public static void main(String[] args) {
        N872LeafSimilar tree = new N872LeafSimilar();
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);
        tree.printTree();
        System.out.println("Good Nodes Count : " + goodNodes(tree.root));
    }

    public static int goodNodes(Node<Integer> root) {
        // Start DFS from the root with an initial maximum value of Integer.MIN_VALUE
        return dfs(root, Integer.MIN_VALUE);
    }

    // Helper DFS function that returns the number of good nodes
    private static int dfs(Node<Integer> node, int maxVal) {
        // Base case: if the node is null, return 0
        if (node == null) {
            return 0;
        }

        // Check if the current node is a good node (if its value is >= maxVal so far)
        int isGood = node.value >= maxVal ? 1 : 0;

        // Update the maximum value on the path to the current node
        int newMax = Math.max(maxVal, node.value);

        // Recursively count the good nodes in the left and right subtrees
        int leftGood = dfs(node.left, newMax);
        int rightGood = dfs(node.right, newMax);

        // Total good nodes is the sum of the current good node and
        // the good nodes from the left and right subtrees
        return isGood + leftGood + rightGood;
    }
}
/*
Given a binary tree root, a node X in the tree is named good if in the path from root to X
there are no nodes with a value greater than X.

Return the number of good nodes in the binary tree.

Example 1:

Input: root = [3,1,4,3,null,1,5]
Output: 4
Explanation: Nodes in blue are good.
Root Node (3) is always a good node.
Node 4 -> (3,4) is the maximum value in the path starting from the root.
Node 5 -> (3,4,5) is the maximum value in the path
Node 3 -> (3,1,3) is the maximum value in the path.

Example 2:
Input: root = [3,3,null,4,2]
Output: 3
Explanation: Node 2 -> (3, 3, 2) is not good, because "3" is higher than it.
Example 3:

Input: root = [1]
Output: 1
Explanation: Root is considered as good.

Constraints:

The number of nodes in the binary tree is in the range [1, 10^5].
Each node's value is between [-10^4, 10^4].
 */