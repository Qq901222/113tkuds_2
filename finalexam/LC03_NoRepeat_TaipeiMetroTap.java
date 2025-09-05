import java.io.*;
import java.util.*;

public class LC03_NoRepeat_TaipeiMetroTap {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) s = "";

        int[] last = new int[65536];
        Arrays.fill(last, -1);

        int left = 0, ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (last[c] >= left) left = last[c] + 1;
            last[c] = i;
            ans = Math.max(ans, i - left + 1);
        }
        System.out.println(ans);
    }
}
