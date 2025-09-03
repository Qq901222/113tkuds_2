// File: lt_21_MergeTwoSortedLists.java
// 題目：Merge Two Sorted Lists
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1), cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) { cur.next = l1; l1 = l1.next; }
            else { cur.next = l2; l2 = l2.next; }
            cur = cur.next;
        }
        cur.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }
}
/*
解題思路：
1. 用 dummy 節點 + 指標歸併兩鏈表。
2. 每次比較選小的接到結果串列尾。
3. 時間 O(m+n)，空間 O(1)。
*/