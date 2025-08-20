import java.io.*;

public class M02_YouBikeNextArrival {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(readNonEmpty(br));
        int[] times = new int[n];
        for (int i = 0; i < n; i++) times[i] = toMinutes(readNonEmpty(br));
        int q = toMinutes(readNonEmpty(br));

        int idx = upperBound(times, q);
        if (idx == n) System.out.println("No bike");
        else System.out.println(fromMinutes(times[idx]));
    }

    static int upperBound(int[] a, int key) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] <= key) l = m + 1; else r = m;
        }
        return l;
    }

    static int toMinutes(String hhmm) {
        int h = Integer.parseInt(hhmm.substring(0, 2));
        int m = Integer.parseInt(hhmm.substring(3, 5));
        return h * 60 + m;
    }

    static String fromMinutes(int minutes) {
        int h = minutes / 60, m = minutes % 60;
        return String.format("%02d:%02d", h, m);
    }

    static String readNonEmpty(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null && s.trim().isEmpty()) {}
        return s == null ? "" : s.trim();
    }
}
