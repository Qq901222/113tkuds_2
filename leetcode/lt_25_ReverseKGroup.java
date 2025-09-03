// File: lt_25_ReverseKGroup.java
// 題目：Reverse Nodes in k-Group
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head), pre = dummy, end = dummy;
        while (true) {
            int count = 0;
            while (count < k && end != null) { end = end.next; count++; }
            if (end == null) break;
            ListNode start = pre.next, next = end.next;
            end.next = null;
            pre.next = reverse(start);
            start.next = next;
            pre = start;
            end = pre;
        }
        return dummy.next;
    }
    private ListNode reverse(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return prev;
    }
}
/*
解題思路：
1. 每次找 k 個節點分組，斷開後調用反轉函數。
2. 反轉後重新連接前後部分。
3. 時間 O(n)，空間 O(1)。
*/