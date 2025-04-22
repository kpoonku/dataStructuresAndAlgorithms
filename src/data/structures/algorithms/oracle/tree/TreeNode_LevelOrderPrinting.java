package data.structures.algorithms.oracle.tree;

import java.util.*;

class TreeNode {
    int val;
    List<TreeNode> children;

    // Constructor
    TreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}

class TreeBFS {

    public void dfs(TreeNode node) {
        if(node == null) {
            return;
        }
        System.out.print(node.val + " ");
        for(TreeNode child: node.children) {
            dfs(child);
        }
    }

    public void levelOrderPrinting(TreeNode root) {
        if(root == null) {
            return;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while(!deque.isEmpty()) {
            TreeNode node = deque.poll();
            System.out.print( node.val + " ");
            deque.addAll(node.children);
        }
    }

    // BFS Traversal on the Tree
    public void bfs(TreeNode root) {
        if (root == null) {
            return;
        }

        // Queue to store nodes for BFS
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            // Dequeue and process the current node
            TreeNode current = queue.poll();
            System.out.print(current.val + " ");  // Process node value

            // Add all children of the current node to the queue
            for (TreeNode child : current.children) {
                queue.offer(child);
            }
        }
    }

    // Create a tree from a list (assuming a list of child values for each node)
    public TreeNode createTreeFromList(List<List<Integer>> nodeList) {
        if (nodeList == null || nodeList.size() == 0) {
            return null;
        }

        // Create all tree nodes
        List<TreeNode> nodes = new ArrayList<>();
        for (int i = 0; i < nodeList.size(); i++) {
            nodes.add(new TreeNode(i + 1));  // Create node with value i+1 (1-indexed)
        }

        // Build tree structure by adding children to parent nodes
        for (int i = 0; i < nodeList.size(); i++) {
            TreeNode parent = nodes.get(i);
            for (Integer childVal : nodeList.get(i)) {
                parent.children.add(nodes.get(childVal - 1));  // Add child node to the parent
            }
        }

        // Return the root of the tree (node 1)
        return nodes.get(0);
    }

    public static void main(String[] args) {
        TreeBFS treeBFS = new TreeBFS();

        // Example: Tree represented as a list of child nodes (1-indexed)
        List<List<Integer>> treeList = new ArrayList<>();
        treeList.add(Arrays.asList(2, 3));      // Node 1 has children 2, 3
        treeList.add(Arrays.asList(4, 5));      // Node 2 has children 4, 5
        treeList.add(Arrays.asList(6));         // Node 3 has child 6
        treeList.add(new ArrayList<>());        // Node 4 has no children
        treeList.add(new ArrayList<>());        // Node 5 has no children
        treeList.add(new ArrayList<>());        // Node 6 has no children

        // Create tree from list
        TreeNode root = treeBFS.createTreeFromList(treeList);

        // Perform BFS traversal
        System.out.print("BFS Traversal: ");
        treeBFS.bfs(root);  // Output: 1 2 3 4 5 6
        System.out.print("\nBFS Traversal - Try : ");
        treeBFS.levelOrderPrinting(root);  // Output: 1 2 3 4 5 6
        System.out.print("\nDFS Traversal : ");
        treeBFS.dfs(root);
    }
}
