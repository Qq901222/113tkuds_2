import java.io.*;
import java.util.*;

public class LC23_MergeKLists_Hospitals {
    static class ListNode {
        long val;
        ListNode next;
        ListNode(long v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        if (first == null || first.trim().isEmpty()) { System.out.println(); return; }
        int k = Integer.parseInt(first.trim());
        ListNode[] lists = new ListNode[k];
        for (int i = 0; i < k; i++) {
            String line = br.readLine();
            if (line == null) { lists[i] = null; continue; }
            StringTokenizer st = new StringTokenizer(line);
            ListNode dummy = new ListNode(0), cur = dummy;
            while (st.hasMoreTokens()) {
                String t = st.nextToken();
                if (t.equals("-1")) break;
                cur.next = new ListNode(Long.parseLong(t));
                cur = cur.next;
            }
            lists[i] = dummy.next;
        }

        ListNode merged = mergeK(lists);
        print(merged);
    }

    static ListNode mergeK(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingLong(n -> n.val));
        for (ListNode node : lists) if (node != null) pq.offer(node);
        ListNode dummy = new ListNode(0), tail = dummy;
        while (!pq.isEmpty()) {
            ListNode x = pq.poll();
            tail.next = x;
            tail = tail.next;
            if (x.next != null) pq.offer(x.next);
        }
        return dummy.next;
    }

    static void print(ListNode head) {
        StringBuilder sb = new StringBuilder();
        for (ListNode p = head; p != null; p = p.next) {
            if (sb.length() > 0) sb.append(' ');
            sb.append(p.val);
        }
        System.out.println(sb.toString());
    }
}
