package corejavapractice.collections;

import java.util.PriorityQueue;

public class MergeKSortedLists {

    public static void main(String[] args) {
        // Create test data
        ListNode[] lists = new ListNode[3];

        lists[0] = new ListNode(1, new ListNode(4, new ListNode(5)));
        lists[1] = new ListNode(1, new ListNode(3, new ListNode(4)));
        lists[2] = new ListNode(2, new ListNode(6));

        // Merge k sorted lists
        ListNode result = mergeKLists(lists);

        // Print the result
        System.out.println("Merged Linked List:");
        printList(result);
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Priority queue to store the nodes based on their values
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head of each list to the priority queue
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        // Dummy node to start the merged list
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // Process the priority queue
        while (!minHeap.isEmpty()) {
            // Remove the smallest element from the heap
            ListNode minNode = minHeap.poll();
            current.next = minNode;
            current = current.next;

            // If the removed node has a next node, add it to the heap
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }

        return dummy.next;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

}
