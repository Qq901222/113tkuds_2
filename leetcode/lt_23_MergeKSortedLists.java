// File: lt_23_MergeKSortedLists.java
// 題目：Merge k Sorted Lists
import java.util.*;
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a,b) -> a.val - b.val);
        for (ListNode node : lists) if (node != null) pq.offer(node);
        ListNode dummy = new ListNode(0), cur = dummy;
        while (!pq.isEmpty()) {
            ListNode t = pq.poll();
            cur.next = t; cur = cur.next;
            if (t.next != null) pq.offer(t.next);
        }
        return dummy.next;
    }
}
/*
解題思路：
1. 用最小堆存每條鏈表的當前節點，取最小接到新串列。
2. 有後繼則入堆，直到堆空。
3. 時間 O(N log k)，空間 O(k)。
*/