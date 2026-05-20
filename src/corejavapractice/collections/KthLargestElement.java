package corejavapractice.collections;

import java.util.PriorityQueue;

public class KthLargestElement {
    public static int findKthLargest(int[] nums, int k) {
        // Create a min-heap with size k
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Iterate through the array
        for (int num : nums) {
            minHeap.add(num);
            // If heap size exceeds k, remove the smallest element
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // The root of the heap is the k-th largest element
        return minHeap.peek();
    }

    public static void main(String[] args) {
        // Example input
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;

        // Find the k-th largest element
        int result = findKthLargest(nums, k);
        System.out.println("The " + k + "-th largest element is: " + result);
    }

}
