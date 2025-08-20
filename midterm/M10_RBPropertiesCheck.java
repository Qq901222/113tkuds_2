import java.io.*;
import java.util.StringTokenizer;

public class M10_RBPropertiesCheck {
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
        int[] val = new int[n];
        char[] col = new char[n];
        for (int i = 0; i < n; i++) {
            val[i] = Integer.parseInt(fs.next());
            String c = fs.next();
            col[i] = (c == null || c.isEmpty()) ? 'B' : c.charAt(0);
            if (val[i] == -1) col[i] = 'B';
        }

        if (n > 0 && val[0] != -1 && col[0] != 'B') {
            System.out.println("RootNotBlack");
            return;
        }

        for (int i = 0; i < n; i++) {
            if (val[i] == -1) continue;
            int l = 2 * i + 1, r = l + 1;
            if (l < n && val[l] != -1 && col[i] == 'R' && col[l] == 'R') {
                System.out.println("RedRedViolation at index " + l);
                return;
            }
            if (r < n && val[r] != -1 && col[i] == 'R' && col[r] == 'R') {
                System.out.println("RedRedViolation at index " + r);
                return;
            }
        }

        int bh = blackHeight(0, val, col);
        if (bh == Integer.MIN_VALUE) {
            System.out.println("BlackHeightMismatch");
        } else {
            System.out.println("RB Valid");
        }
    }

    static int blackHeight(int i, int[] val, char[] col) {
        if (i >= val.length || val[i] == -1) return 1;
        int hl = blackHeight(2 * i + 1, val, col);
        if (hl == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int hr = blackHeight(2 * i + 2, val, col);
        if (hr == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        if (hl != hr) return Integer.MIN_VALUE;
        return hl + (col[i] == 'B' ? 1 : 0);
        }

}

