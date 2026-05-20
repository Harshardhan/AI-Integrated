package corejavapractice.collections;

public class ReverseLinkedListII {

	public ListNode reverseBetween(ListNode head, int left, int right) {
		if(head == null || left ==right) return head;
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode prev = dummy;
		
		for(int i =1;i<left;i++) {
			prev = prev.next;
		}
		
		ListNode start = prev.next;
		
		ListNode then = start.next;
		
		for(int i =0;i<right-left;i++) {
			start.next = then.next;
			then.next = prev.next;
			prev.next = then;
			then = start.next;
		}
		
		return dummy.next;
	}
	
	public static void main(String[] args) {
	    ReverseLinkedListII solution = new ReverseLinkedListII();
	    ListNode head = new ListNode(1);
	    head.next = new ListNode(2);
	    head.next.next = new ListNode(3);
	    head.next.next.next = new ListNode(4);
	    head.next.next.next.next = new ListNode(5);

	    int left = 2, right = 4;
	    ListNode result = solution.reverseBetween(head, left, right);

	    // Print the reversed list
	    while (result != null) {
	        System.out.print(result.val + " ");
	        result = result.next;
	    }
	}

}
