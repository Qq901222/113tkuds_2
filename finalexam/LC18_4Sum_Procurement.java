import java.io.*;
import java.util.*;

public class LC18_4Sum_Procurement {
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
        long target = fs.nextLong();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = fs.nextLong();

        Arrays.sort(a);
        StringBuilder out = new StringBuilder();

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && a[i] == a[i - 1]) continue;

            // 剪枝：最小/最大可能和檢查
            long min1 = a[i] + a[i + 1] + a[i + 2] + a[i + 3];
            if (min1 > target) break;
            long max1 = a[i] + a[n - 1] + a[n - 2] + a[n - 3];
            if (max1 < target) continue;

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && a[j] == a[j - 1]) continue;

                long min2 = a[i] + a[j] + a[j + 1] + a[j + 2];
                if (min2 > target) break;
                long max2 = a[i] + a[j] + a[n - 1] + a[n - 2];
                if (max2 < target) continue;

                int l = j + 1, r = n - 1;
                long need = target - a[i] - a[j];
                while (l < r) {
                    long sum = a[l] + a[r];
                    if (sum == need) {
                        out.append(a[i]).append(' ')
                           .append(a[j]).append(' ')
                           .append(a[l]).append(' ')
                           .append(a[r]).append('\n');
                        long lv = a[l], rv = a[r];
                        while (l < r && a[l] == lv) l++;
                        while (l < r && a[r] == rv) r--;
                    } else if (sum < need) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        System.out.print(out.toString());
    }
}
