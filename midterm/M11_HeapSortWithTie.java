import java.io.*;
import java.util.*;

public class M11_HeapSortWithTie {
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

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        int n = Integer.parseInt(fs.next());
        int[] a = new int[n];
        int[] idx = new int[n];
        for (int i = 0; i < n; i++) { a[i] = fs.nextInt(); idx[i] = i; }

        heapSort(a, idx);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (i > 0) sb.append(' ');
            sb.append(a[i]);
        }
        System.out.println(sb.toString());
    }

    static void heapSort(int[] a, int[] idx) {
        int n = a.length;
        for (int i = n / 2 - 1; i >= 0; i--) siftDown(a, idx, i, n);
        for (int end = n - 1; end > 0; end--) {
            swap(a, idx, 0, end);
            siftDown(a, idx, 0, end);
        }
    }

    static void siftDown(int[] a, int[] idx, int i, int n) {
        while (true) {
            int l = i * 2 + 1, r = l + 1, best = i;
            if (l < n && greater(a, idx, l, best)) best = l;
            if (r < n && greater(a, idx, r, best)) best = r;
            if (best == i) break;
            swap(a, idx, i, best);
            i = best;
        }
    }

    static boolean greater(int[] a, int[] idx, int i, int j) {
        if (a[i] != a[j]) return a[i] > a[j];
        return idx[i] > idx[j]; 
    }

    static void swap(int[] a, int[] idx, int i, int j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
        int s = idx[i]; idx[i] = idx[j]; idx[j] = s;
    }
}

/*
時間複雜度：O(n log n)
說明：建堆 O(n)，接著做 n 次「取頂→下濾」各 O(log n)，合計 O(n log n)；平手規則不影響複雜度。
*/

