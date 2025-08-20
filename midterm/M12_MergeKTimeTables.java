import java.io.*;
import java.util.*;

public class M12_MergeKTimeTables {
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
        int t, li, idx;
        Node(int t, int li, int idx) { this.t = t; this.li = li; this.idx = idx; }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int K = Integer.parseInt(fs.next());
        int[][] lists = new int[K][];
        boolean hhmm = false;

        for (int i = 0; i < K; i++) {
            int len = Integer.parseInt(fs.next());
            lists[i] = new int[len];
            for (int j = 0; j < len; j++) {
                String tok = fs.next();
                if (tok.indexOf(':') >= 0) hhmm = true;
                lists[i][j] = parseTime(tok);
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.t));
        for (int i = 0; i < K; i++) if (lists[i].length > 0) pq.offer(new Node(lists[i][0], i, 0));

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (!first) sb.append(' ');
            first = false;
            sb.append(hhmm ? fmt(cur.t) : Integer.toString(cur.t));
            int ni = cur.idx + 1;
            if (ni < lists[cur.li].length) pq.offer(new Node(lists[cur.li][ni], cur.li, ni));
        }
        System.out.println(sb.toString());
    }

    static int parseTime(String s) {
        int p = s.indexOf(':');
        if (p < 0) return Integer.parseInt(s);
        int h = Integer.parseInt(s.substring(0, p));
        int m = Integer.parseInt(s.substring(p + 1));
        return h * 60 + m;
    }

    static String fmt(int minutes) {
        int h = minutes / 60, m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }
}
