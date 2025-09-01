
public class lt_05_longestpalindromicsubstring {
    public static String longestExpand(String s) {
        int n = s.length();
        if (n < 2) return s;
        int L = 0, R = 0;
        for (int i = 0; i < n; i++) {
            int[] a = expand(s, i, i);
            if (a[1] - a[0] > R - L) { L = a[0]; R = a[1]; }
            int[] b = expand(s, i, i + 1);
            if (b[1] - b[0] > R - L) { L = b[0]; R = b[1]; }
        }
        return s.substring(L, R + 1);
    }

    private static int[] expand(String s, int l, int r) {
        while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) { l--; r++; }
        return new int[]{l + 1, r - 1};
    }

    public static String longestManacher(String s) {
        if (s.length() < 2) return s;
        char[] t = transform(s);
        int n = t.length;
        int[] p = new int[n];
        int center = 0, right = 0, bestLen = 0, bestCenter = 0;
        for (int i = 1; i < n - 1; i++) {
            int mirror = 2 * center - i;
            p[i] = (right > i) ? Math.min(right - i, p[mirror]) : 0;
            while (t[i + 1 + p[i]] == t[i - 1 - p[i]]) p[i]++;
            if (i + p[i] > right) { center = i; right = i + p[i]; }
            if (p[i] > bestLen) { bestLen = p[i]; bestCenter = i; }
        }
        int start = (bestCenter - bestLen) / 2;
        return s.substring(start, start + bestLen);
    }

    private static char[] transform(String s) {
        char[] t = new char[s.length() * 2 + 3];
        t[0] = '^'; t[t.length - 1] = '$';
        for (int i = 0; i < s.length(); i++) {
            t[2 * i + 1] = '#';
            t[2 * i + 2] = s.charAt(i);
        }
        t[t.length - 2] = '#';
        return t;
    }

    public static void main(String[] args) {
        System.out.println(longestExpand("babad"));
        System.out.println(longestExpand("cbbd"));
        System.out.println(longestManacher("babad"));
        System.out.println(longestManacher("cbbd"));
    }
}
