
public class lt_04_medianoftwosortedarrays {
    public static double medianLog(int[] a, int[] b) {
        if (a.length > b.length) return medianLog(b, a);
        int m = a.length, n = b.length, lo = 0, hi = m, half = (m + n + 1) / 2;
        while (lo <= hi) {
            int i = (lo + hi) >>> 1;
            int j = half - i;
            int la = (i == 0) ? Integer.MIN_VALUE : a[i - 1];
            int ra = (i == m) ? Integer.MAX_VALUE : a[i];
            int lb = (j == 0) ? Integer.MIN_VALUE : b[j - 1];
            int rb = (j == n) ? Integer.MAX_VALUE : b[j];
            if (la <= rb && lb <= ra) {
                if (((m + n) & 1) == 1) return Math.max(la, lb);
                int leftMax = Math.max(la, lb);
                int rightMin = Math.min(ra, rb);
                return (leftMax + rightMin) / 2.0;
            } else if (la > rb) {
                hi = i - 1;
            } else {
                lo = i + 1;
            }
        }
        return 0.0;
    }

    public static double medianMerge(int[] a, int[] b) {
        int m = a.length, n = b.length, total = m + n;
        int mid1 = (total - 1) / 2, mid2 = total / 2;
        int i = 0, j = 0, k = -1, x = 0, y = 0;
        while (i < m || j < n) {
            int val;
            if (j == n || (i < m && a[i] <= b[j])) val = a[i++];
            else val = b[j++];
            k++;
            if (k == mid1) x = val;
            if (k == mid2) { y = val; break; }
        }
        if ((total & 1) == 1) return y;
        return (x + y) / 2.0;
    }

    public static void main(String[] args) {
        System.out.println(medianLog(new int[]{1,3}, new int[]{2}));
        System.out.println(medianLog(new int[]{1,2}, new int[]{3,4}));
        System.out.println(medianMerge(new int[]{1,3}, new int[]{2}));
        System.out.println(medianMerge(new int[]{1,2}, new int[]{3,4}));
    }
}
