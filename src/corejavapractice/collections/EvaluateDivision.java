package corejavapractice.collections;

import java.util.*;

public class EvaluateDivision {
    
    // Graph class to handle the equations and queries
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // Step 1: Create an adjacency list to represent the graph
        Map<String, Map<String, Double>> graph = new HashMap<>();
        
        // Step 2: Build the graph from the equations and values
        for (int i = 0; i < equations.size(); i++) {
            String variable1 = equations.get(i).get(0);
            String variable2 = equations.get(i).get(1);
            double value = values[i];
            
            // Add edges in both directions (A/B = value) and (B/A = 1/value)
            graph.putIfAbsent(variable1, new HashMap<>());
            graph.putIfAbsent(variable2, new HashMap<>());
            graph.get(variable1).put(variable2, value);
            graph.get(variable2).put(variable1, 1 / value);
        }
        
        // Step 3: Process the queries
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);
            
            // Step 4: Perform DFS to find the path from start to end
            Set<String> visited = new HashSet<>();
            result[i] = dfs(graph, start, end, visited);
        }
        
        return result;
    }
    
    // DFS function to explore the graph and find the ratio
    private double dfs(Map<String, Map<String, Double>> graph, String start, String end, Set<String> visited) {
        // If the start or end variable doesn't exist in the graph, return -1
        if (!graph.containsKey(start) || !graph.containsKey(end)) {
            return -1.0;
        }
        
        // If start is the same as end, the ratio is 1
        if (start.equals(end)) {
            return 1.0;
        }
        
        // Mark the current node as visited
        visited.add(start);
        
        // Explore all neighbors of the current node
        for (String neighbor : graph.get(start).keySet()) {
            if (!visited.contains(neighbor)) {
                double result = dfs(graph, neighbor, end, visited);
                if (result != -1.0) {
                    // If we can find a path, return the product of the edge weight and the result
                    return graph.get(start).get(neighbor) * result;
                }
            }
        }
        
        // If no path is found, return -1
        return -1.0;
    }
    
    public static void main(String[] args) {
        EvaluateDivision solution = new EvaluateDivision();
        
        // Example test case 1
        List<List<String>> equations = new ArrayList<>();
        equations.add(Arrays.asList("a", "b"));
        equations.add(Arrays.asList("b", "c"));
        double[] values = {2.0, 3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        
        double[] result = solution.calcEquation(equations, values, queries);
        
        // Print result
        System.out.println(Arrays.toString(result));
        // Expected output: [6.0, 0.5, -1.0, 1.0]
    }
}

