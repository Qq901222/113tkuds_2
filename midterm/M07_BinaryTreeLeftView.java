import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class M07_BinaryTreeLeftView {
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

        StringBuilder sb = new StringBuilder();
        sb.append("LeftView:");
        if (root != null) {
            ArrayDeque<Node> q = new ArrayDeque<>();
            q.add(root);
            while (!q.isEmpty()) {
                int sz = q.size();
                for (int i = 0; i < sz; i++) {
                    Node cur = q.poll();
                    if (i == 0) sb.append(' ').append(cur.val);
                    if (cur.left != null) q.add(cur.left);
                    if (cur.right != null) q.add(cur.right);
                }
            }
        }
        System.out.println(sb.toString());
    }
}
