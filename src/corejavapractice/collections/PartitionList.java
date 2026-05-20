package corejavapractice.collections;


public class PartitionList {
    
    public ListNode partition(ListNode head, int x) {
        // Dummy nodes to start the less and greater lists
        ListNode lessHead = new ListNode(0);
        ListNode greaterHead = new ListNode(0);

        // Pointers for building the less and greater lists
        ListNode less = lessHead;
        ListNode greater = greaterHead;

        // Traverse the original list and partition nodes
        while (head != null) {
            if (head.val < x) {
                // Append to the less list
                less.next = head;
                less = less.next;
            } else {
                // Append to the greater list
                greater.next = head;
                greater = greater.next;
            }
            head = head.next;
        }

        // Connect the less list to the greater list
        less.next = greaterHead.next;
        // End the greater list
        greater.next = null;

        // The head of the partitioned list is the next of lessHead
        return lessHead.next;
    }

    // Helper method to print the linked list
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Main method to test the partition function
    public static void main(String[] args) {
        // Create a sample linked list: 1 -> 4 -> 3 -> 2 -> 5 -> 2
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(2);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(2);

        // Link the nodes together
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        // Partition the list around x
        PartitionList solution = new PartitionList();
        int x = 3;
        ListNode result = solution.partition(node1, x);

        // Print the partitioned list
        System.out.println("Partitioned list around " + x + ":");
        solution.printList(result);
    }

}
