package corejavapractice.collections;

import java.util.*;

public class SmallestPairsAlt {
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();

        // Min-heap to store elements as (sum, index1, index2)
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Add first `k` elements from nums1 combined with the first element of nums2
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            minHeap.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }

        // Extract the smallest sums from the heap
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] current = minHeap.poll();
            int i = current[1];
            int j = current[2];

            // Convert int[] to List<Integer> and add to the result
            result.add(Arrays.asList(nums1[i], nums2[j]));

            // If there's a next element in nums2 for nums1[i], add it to the heap
            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{nums1[i] + nums2[j + 1], i, j + 1});
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example input
        int[] nums1 = {1, 7, 11};
        int[] nums2 = {2, 4, 6};
        int k = 3;

        // Output the k smallest pairs
        List<List<Integer>> result = kSmallestPairs(nums1, nums2, k);
        System.out.println("The " + k + " pairs with the smallest sums are:");
        for (List<Integer> pair : result) {
            System.out.println(pair);
        }
    }

}
