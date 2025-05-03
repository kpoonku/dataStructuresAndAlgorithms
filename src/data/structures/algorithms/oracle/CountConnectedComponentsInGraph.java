package data.structures.algorithms.oracle;

import java.util.*;

public class CountConnectedComponentsInGraph {
    public int countComponents(int n, int[][] edges) {
        int connectedComponents = 0;
        Map<Integer, List<Integer>> graph = new HashMap<>(n);
        Set<Integer> visited = new HashSet<>(n);
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }

        for (int node: graph.keySet()) {
            System.out.println("Node1 : "+ node);
            if(!visited.contains(node)) {
                connectedComponents++;
                dfs(node, graph, visited);
            }
        }
        return connectedComponents;
    }

    public void dfs(int node, Map<Integer,
                            List<Integer>> graph,
                    Set<Integer> visited) {
        visited.add(node);
        System.out.println("Node2 : "+ node);
        for(int neighbor : graph.get(node)) {
            if(!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited);
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0,1},{1,2},{3,4}};
        CountConnectedComponentsInGraph componentsInGraph = new CountConnectedComponentsInGraph();
        int result = componentsInGraph.countComponents(n, edges);
        System.out.println(result);
        edges = new int[][]{{0,1},{1,2},{2,3},{3,4}};
        result = componentsInGraph.countComponents(n, edges);
        System.out.println(result);
    }


}
