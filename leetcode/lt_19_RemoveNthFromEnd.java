// File: lt_19_RemoveNthFromEnd.java
// 題目：Remove Nth Node From End of List
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i < n + 1; i++) fast = fast.next;
        while (fast != null) { fast = fast.next; slow = slow.next; }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
/*
解題思路：
1. 使用 dummy 節點簡化刪除頭節點情況。
2. 兩指針：fast 先走 n+1 步，再同步移動 fast/slow，使 slow 停刪除目標前一個節點。
3. 刪除 slow.next，返回 dummy.next。
4. 時間 O(L)，空間 O(1)。
*/