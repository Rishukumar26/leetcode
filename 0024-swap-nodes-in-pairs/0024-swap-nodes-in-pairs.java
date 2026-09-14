class Solution {
    public ListNode swapPairs(ListNode head) {
        // Dummy node acts as a prev marker before the head
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        
        while (prev.next != null && prev.next.next != null) {
            ListNode first = prev.next;
            ListNode second = prev.next.next;
            
            // Re-point pointers to swap adjacent pair
            first.next = second.next;
            second.next = first;
            prev.next = second;
            
            // Advance prev to prepare for next pair
            prev = first;
        }
        
        return dummy.next;
    }
}