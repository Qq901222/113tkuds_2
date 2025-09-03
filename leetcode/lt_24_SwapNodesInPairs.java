// File: lt_24_SwapNodesInPairs.java
// 題目：Swap Nodes in Pairs
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0, head), pre = dummy;
        while (pre.next != null && pre.next.next != null) {
            ListNode a = pre.next, b = a.next;
            a.next = b.next;
            b.next = a;
            pre.next = b;
            pre = a;
        }
        return dummy.next;
    }
}
/*
解題思路：
1. 每次選兩個節點 a、b，交換後 pre 指向 b，再移動到 a 繼續。
2. dummy 處理頭節點。
3. 時間 O(n)，空間 O(1)。
*/