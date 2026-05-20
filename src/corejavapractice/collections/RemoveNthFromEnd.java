package corejavapractice.collections;

public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // Dummy node to simplify edge cases, such as removing the head node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // Initialize two pointers
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        // Move `fast` pointer `n + 1` steps ahead to maintain a gap of `n` between `fast` and `slow`
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        
        // Move both pointers until `fast` reaches the end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        
        // `slow.next` is the node to be removed, so skip it
        slow.next = slow.next.next;
        
        // Return the new head (in case the head was removed, `dummy.next` will point to the new head)
        return dummy.next;
    }
    public static void main(String[] args) {
    	RemoveNthFromEnd solution = new RemoveNthFromEnd();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        int n = 2;
        ListNode result = solution.removeNthFromEnd(head, n);

        // Print the modified list
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

}
