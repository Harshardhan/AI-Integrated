package corejavapractice.collections;

public class ReverseNodesinKGroup {

	public ListNode reverseNodesGroup(ListNode head, int k) {
		if(head == null || k ==1) return head;
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode current =  dummy,prevGroupEnd = dummy, nextGroupStart = dummy;
		
		int count = 0;
		while(current.next != null) {
			current =  current.next;
			count++;
		}
		
		while(count==k) {
			current = prevGroupEnd.next;
			nextGroupStart = current.next;
			
			for(int i = 1;i<k;i++) {
				current.next = nextGroupStart.next;
				nextGroupStart.next = prevGroupEnd.next;
				prevGroupEnd.next = nextGroupStart.next;
				nextGroupStart.next = current.next;
			}
			
            prevGroupEnd = current;
            count -= k;

		}
        return dummy.next;

	}
	
	public static void main(String[] args) {
	    ReverseNodesinKGroup solution = new ReverseNodesinKGroup();
	    ListNode head = new ListNode(1);
	    head.next = new ListNode(2);
	    head.next.next = new ListNode(3);
	    head.next.next.next = new ListNode(4);
	    head.next.next.next.next = new ListNode(5);

	    int k = 2;
	    ListNode result = solution.reverseNodesGroup(head, k);

	    // Print the reversed list
	    while (result != null) {
	        System.out.print(result.val + " ");
	        result = result.next;
	    }
	}
}
