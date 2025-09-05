import java.io.*;
import java.util.*;

public class LC15_3Sum_THSRStops {
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
        long nextLong() throws IOException { return Long.parseLong(next()); }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        String t = fs.next();
        if (t == null) return;
        int n = Integer.parseInt(t);
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextLong();

        Arrays.sort(a);
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n - 2; i++) {
            if (i > 0 && a[i] == a[i - 1]) continue;
            if (a[i] > 0) break;
            int l = i + 1, r = n - 1;
            while (l < r) {
                long sum = a[i] + a[l] + a[r];
                if (sum == 0) {
                    out.append(a[i]).append(' ').append(a[l]).append(' ').append(a[r]).append('\n');
                    long lv = a[l], rv = a[r];
                    while (l < r && a[l] == lv) l++;
                    while (l < r && a[r] == rv) r--;
                } else if (sum < 0) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        System.out.print(out.toString());
    }
}
