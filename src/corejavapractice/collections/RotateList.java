package corejavapractice.collections;

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        // Step 1: Compute the length of the list and connect the tail to head
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        tail.next = head;  // Form a circular list

        // Step 2: Find the effective number of rotations
        k = k % length;
        if (k == 0) { // No rotation needed if k is a multiple of length
            tail.next = null; // Break the circular list
            return head;
        }

        // Step 3: Find the new tail and new head
        int stepsToNewTail = length - k - 1;
        ListNode newTail = head;
        for (int i = 0; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;

        // Step 4: Break the circular link
        newTail.next = null;

        return newHead;
    }

    // Helper method to print the linked list
    public void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    // Main method to test the rotateRight function
    public static void main(String[] args) {
        // Create a sample linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        // Link the nodes together
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        // Rotate the list by k places
        RotateList solution = new RotateList();
        int k = 2;  // Number of places to rotate
        ListNode result = solution.rotateRight(node1, k);

        // Print the rotated list
        System.out.println("Rotated list after rotating by " + k + " places:");
        solution.printList(result);
    }

}
