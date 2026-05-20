package corejavapractice.collections;

import java.util.*;

public class CoreSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Build the graph and compute indegrees
        List<List<Integer>> graph = new ArrayList<>();
        int[] indegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int[] prereq : prerequisites) {
            int course = prereq[0];
            int pre = prereq[1];
            graph.get(pre).add(course); // Add edge pre -> course
            indegree[course]++; // Increase the indegree of the course
        }
        
        // Step 2: Perform Kahn's Algorithm (BFS)
        Queue<Integer> queue = new LinkedList<>();
        
        // Add all nodes with indegree 0 to the queue
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        int processedCourses = 0; // Counter for processed nodes
        
        while (!queue.isEmpty()) {
            int current = queue.poll();
            processedCourses++; // Mark this course as processed
            
            // Reduce the indegree of neighbors
            for (int neighbor : graph.get(current)) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor); // Add neighbor to the queue if its indegree becomes 0
                }
            }
        }
        
        // If all courses are processed, return true; otherwise, false
        return processedCourses == numCourses;
    }
    
    public static void main(String[] args) {
        CoreSchedule solution = new CoreSchedule();
        
        // Example test case 1
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println(solution.canFinish(numCourses1, prerequisites1)); // Expected output: true
        
        // Example test case 2
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println(solution.canFinish(numCourses2, prerequisites2)); // Expected output: false
    }

}
