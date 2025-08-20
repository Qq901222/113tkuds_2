import java.io.*;
import java.util.*;

public class M08_BSTRangedSum {
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
        int L = fs.nextInt(), R = fs.nextInt();

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) if (a[i] != -1) nodes[i] = new Node(a[i]);
        for (int i = 0; i < n; i++) {
            if (nodes[i] == null) continue;
            int l = 2 * i + 1, r = l + 1;
            if (l < n) nodes[i].left = nodes[l];
            if (r < n) nodes[i].right = nodes[r];
        }
        Node root = n > 0 ? nodes[0] : null;

        long sum = rangeSum(root, L, R);
        System.out.println("Sum: " + sum);
    }

    static long rangeSum(Node node, int L, int R) {
        if (node == null) return 0;
        if (node.val < L) return rangeSum(node.right, L, R);
        if (node.val > R) return rangeSum(node.left, L, R);
        return node.val + rangeSum(node.left, L, R) + rangeSum(node.right, L, R);
    }
}
