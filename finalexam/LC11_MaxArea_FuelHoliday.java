import java.io.*;
import java.util.*;

public class LC11_MaxArea_FuelHoliday {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        if (line == null || line.isEmpty()) return;
        int n = Integer.parseInt(line.trim());

        long[] h = new long[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) h[i] = Long.parseLong(st.nextToken());

        int l = 0, r = n - 1;
        long ans = 0;
        while (l < r) {
            long height = Math.min(h[l], h[r]);
            ans = Math.max(ans, height * (r - l));
            if (h[l] < h[r]) l++;
            else r--;
        }
        System.out.println(ans);
    }
}
