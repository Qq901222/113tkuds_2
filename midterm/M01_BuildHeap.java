import java.io.*;

public class M01_BuildHeap {
    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        String type = fs.next();
        int n = Integer.parseInt(fs.next());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(fs.next());

        boolean isMax = "max".equalsIgnoreCase(type);
        buildHeap(a, isMax);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb.toString());
    }

    static void buildHeap(int[] a, boolean isMax) {
        int n = a.length;
        for (int i = (n / 2) - 1; i >= 0; i--) heapifyDown(a, i, n, isMax);
    }

    static void heapifyDown(int[] a, int i, int n, boolean isMax) {
        while (true) {
            int l = i * 2 + 1, r = l + 1, best = i;
            if (l < n && (isMax ? a[l] > a[best] : a[l] < a[best])) best = l;
            if (r < n && (isMax ? a[r] > a[best] : a[r] < a[best])) best = r;
            if (best == i) break;
            int t = a[i]; a[i] = a[best]; a[best] = t;
            i = best;
        }
    }

    static final class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = read()) != -1 && c <= ' ') {}
            if (c == -1) return null;
            do { sb.append((char)c); } while ((c = read()) != -1 && c > ' ');
            return sb.toString();
        }
    }
}

/*
時間複雜度：O(n)
說明：自底向上從最後一個非葉節點做 heapify，所有節點的下沉次數加總為線性。
*/
