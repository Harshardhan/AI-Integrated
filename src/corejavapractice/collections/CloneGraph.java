package corejavapractice.collections;

import java.util.*;


public class CloneGraph {

    // This method will clone the graph
    public Nodes cloneGraph(Nodes node) {
        if (node == null) {
            return null;
        }

        // Map to store the already created nodes
        Map<Nodes, Nodes> map = new HashMap<>();
        
        // Start DFS to clone the graph
        return dfs(node, map);
    }

    // Helper method to perform DFS and clone the nodes
    private Nodes dfs(Nodes node, Map<Nodes, Nodes> map) {
        if (map.containsKey(node)) {
            return map.get(node);
        }

        // Create a new node for the current node
        Nodes clonedNode = new Nodes(node.val);
        map.put(node, clonedNode);

        // Recursively clone all neighbors
        for (Nodes neighbor : node.neighbors) {
            clonedNode.neighbors.add(dfs(neighbor, map));
        }

        return clonedNode;
    }

    // Main method to test the functionality
    public static void main(String[] args) {
        // Create a simple graph for testing: 
        // 1 -- 2
        // |    |
        // 4 -- 3
        
        Nodes node1 = new Nodes(1);
        Nodes node2 = new Nodes(2);
        Nodes node3 = new Nodes(3);
        Nodes node4 = new Nodes(4);
        
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        
        CloneGraph solution = new CloneGraph();
        Nodes clonedGraph = solution.cloneGraph(node1);
        
        // Print the cloned graph to check if it works
        printGraph(clonedGraph);
    }

    // Helper method to print the graph structure
    private static void printGraph(Nodes node) {
        Set<Nodes> visited = new HashSet<>();
        dfsPrint(node, visited);
    }

    private static void dfsPrint(Nodes node, Set<Nodes> visited) {
        if (node == null || visited.contains(node)) {
            return;
        }
        visited.add(node);
        System.out.print("Node " + node.val + " -> ");
        for (Nodes neighbor : node.neighbors) {
            System.out.print(neighbor.val + " ");
        }
        System.out.println();
        for (Nodes neighbor : node.neighbors) {
            dfsPrint(neighbor, visited);
        }
    }

}
