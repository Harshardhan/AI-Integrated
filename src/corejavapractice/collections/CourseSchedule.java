package corejavapractice.collections;

import java.util.*;

public class CourseSchedule {
    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        // Create an adjacency list
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }

        // Create an array to track in-degrees
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            adjList.get(prerequisiteCourse).add(course);
            inDegree[course]++;
        }

        // Initialize a queue and add all courses with in-degree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // List to store the course order
        List<Integer> courseOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int current = queue.poll();
            courseOrder.add(current);

            // Reduce the in-degree of adjacent nodes
            for (int neighbor : adjList.get(current)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // If the number of courses in the order list is less than numCourses, it means a cycle exists
        if (courseOrder.size() != numCourses) {
            return new int[0];
        }

        // Convert the courseOrder list to an array and return
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            result[i] = courseOrder.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        // Example Input
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};

        // Find course order
        int[] order = findOrder(numCourses, prerequisites);

        // Output the result
        if (order.length == 0) {
            System.out.println("It is impossible to complete all courses.");
        } else {
            System.out.println("Course order: " + Arrays.toString(order));
        }
    }

}
