import java.io.*;
import java.util.*;

public class LC04_Median_QuakeFeeds {
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String next() throws IOException {
            while (st == null || !st.hasMoreElements()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }
        int nextInt() throws IOException { return Integer.parseInt(next()); }
        double nextDouble() throws IOException { return Double.parseDouble(next()); }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        String t = fs.next();
        if (t == null) return;
        int n = Integer.parseInt(t);
        int m = fs.nextInt();
        double[] A = new double[n];
        double[] B = new double[m];
        for (int i = 0; i < n; i++) A[i] = fs.nextDouble();
        for (int j = 0; j < m; j++) B[j] = fs.nextDouble();
        System.out.printf("%.1f%n", medianTwoSorted(A, B));
    }

    static double medianTwoSorted(double[] a, double[] b) {
        if (a.length > b.length) return medianTwoSorted(b, a);
        int n = a.length, m = b.length, half = (n + m + 1) / 2;
        int lo = 0, hi = n;
        while (lo <= hi) {
            int i = (lo + hi) >>> 1;
            int j = half - i;
            double lA = (i == 0) ? Double.NEGATIVE_INFINITY : a[i - 1];
            double rA = (i == n) ? Double.POSITIVE_INFINITY : a[i];
            double lB = (j == 0) ? Double.NEGATIVE_INFINITY : b[j - 1];
            double rB = (j == m) ? Double.POSITIVE_INFINITY : b[j];
            if (lA <= rB && lB <= rA) {
                if (((n + m) & 1) == 1) return Math.max(lA, lB);
                return (Math.max(lA, lB) + Math.min(rA, rB)) / 2.0;
            } else if (lA > rB) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        return 0.0;
    }
}
