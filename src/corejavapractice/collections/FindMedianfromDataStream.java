package corejavapractice.collections;

import java.util.PriorityQueue;

public class FindMedianfromDataStream {

	// Max-heap to store the smaller half of the numbers
	private PriorityQueue<Integer> maxHeap;

	// Min-heap to store the larger half of the numbers
	private PriorityQueue<Integer> minHeap;

	/**
	 * Initializes the MedianFinder object.
	 */
	public FindMedianfromDataStream() {
		maxHeap = new PriorityQueue<>((a, b) -> b - a); // Max-heap
		minHeap = new PriorityQueue<>(); // Min-heap
	}

	/**
	 * Adds a number to the data structure.
	 * 
	 * @param num The integer to add.
	 */
	public void addNum(int num) {
		if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
			maxHeap.offer(num);
		} else {
			minHeap.offer(num);
		}

		// Balance the heaps to ensure their sizes differ by at most 1
		if (maxHeap.size() > minHeap.size() + 1) {
			minHeap.offer(maxHeap.poll());
		} else if (minHeap.size() > maxHeap.size()) {
			maxHeap.offer(minHeap.poll());
		}
	}

	/**
	 * Returns the median of all elements so far.
	 * 
	 * @return The median as a double.
	 */
	public double findMedian() {
		if (maxHeap.size() == minHeap.size()) {
			// Even number of elements: median is the average of the two middle elements
			return (maxHeap.peek() + minHeap.peek()) / 2.0;
		} else {
			// Odd number of elements: median is the top of the larger heap
			return maxHeap.peek();
		}
	}

	public static void main(String[] args) {
		FindMedianfromDataStream medianFinder = new FindMedianfromDataStream();

		medianFinder.addNum(2);
		medianFinder.addNum(3);
		System.out.println("Median: " + medianFinder.findMedian()); // Output: 2.5

		medianFinder.addNum(4);
		System.out.println("Median: " + medianFinder.findMedian()); // Output: 3.0
	}

}
