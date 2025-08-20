import java.io.*;
import java.util.*;

public class M09_AVLValidate {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
    }

    static class Node {
        int val; Node left, right;
        Node(int v) { val = v; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int n = fs.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextInt();

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) if (a[i] != -1) nodes[i] = new Node(a[i]);
        for (int i = 0; i < n; i++) {
            if (nodes[i] == null) continue;
            int l = 2 * i + 1, r = l + 1;
            if (l < n) nodes[i].left = nodes[l];
            if (r < n) nodes[i].right = nodes[r];
        }
        Node root = n > 0 ? nodes[0] : null;

        if (!isBST(root, Long.MIN_VALUE, Long.MAX_VALUE)) {
            System.out.println("Invalid BST");
        } else if (checkAVL(root) == Integer.MIN_VALUE) {
            System.out.println("Invalid AVL");
        } else {
            System.out.println("Valid");
        }
    }

    static boolean isBST(Node node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) return false;
        return isBST(node.left, min, node.val) && isBST(node.right, node.val, max);
    }

    static int checkAVL(Node node) {
        if (node == null) return 0;
        int hl = checkAVL(node.left);
        if (hl == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int hr = checkAVL(node.right);
        if (hr == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        if (Math.abs(hl - hr) > 1) return Integer.MIN_VALUE;
        return Math.max(hl, hr) + 1;
    }
}

/*
時間複雜度：O(n)
說明：一次 DFS 同時檢查 BST（上下界）與 AVL（後序取高度），每個節點只訪一次。
*/

