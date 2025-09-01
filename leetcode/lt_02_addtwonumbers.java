import java.util.*;

public class lt_02_addtwonumbers {
    static class ListNode {
        int val; ListNode next;
        ListNode(int v){ val=v; }
        ListNode(int v, ListNode n){ val=v; next=n; }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;
            int sum = x + y + carry;
            carry = sum / 10;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return dummy.next;
    }

    static ListNode build(int[] a){
        ListNode d = new ListNode(0), c = d;
        for(int v: a){ c.next = new ListNode(v); c = c.next; }
        return d.next;
    }

    static String toListString(ListNode n){
        List<String> out = new ArrayList<>();
        while(n!=null){ out.add(String.valueOf(n.val)); n=n.next; }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(toListString(addTwoNumbers(build(new int[]{2,4,3}), build(new int[]{5,6,4}))));
        System.out.println(toListString(addTwoNumbers(build(new int[]{0}), build(new int[]{0}))));
        System.out.println(toListString(addTwoNumbers(build(new int[]{9,9,9,9,9,9,9}), build(new int[]{9,9,9,9}))));
    }
}
