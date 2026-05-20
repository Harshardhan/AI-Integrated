package corejavapractice.collections;

public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        // Dummy node to handle edge cases easily
        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        
        while (head != null) {
            // If we have duplicates, skip them
            if (head.next != null && head.val == head.next.val) {
                // Skip all nodes with the same value
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // Link prev node to the next distinct node
                prev.next = head.next;
            } else {
                // No duplicates detected; move prev pointer to current node
                prev = prev.next;
            }
            // Move head pointer to the next node
            head = head.next;
        }
        
        // Return the modified list
        return dummy.next;
    }
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    
    public static void main(String[] args) {
        // Create a sample sorted linked list: 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(5);

        // Link the nodes together
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        // Call the deleteDuplicates method
        RemoveDuplicatesFromSortedListII solution = new RemoveDuplicatesFromSortedListII();
        ListNode result = solution.deleteDuplicates(node1);

        // Print the modified list
        System.out.println("Modified list after deleting duplicates:");
        solution.printList(result);
    }

}
