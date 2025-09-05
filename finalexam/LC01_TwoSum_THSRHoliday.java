import java.io.*;
import java.util.*;

public class LC01_TwoSum_THSRHoliday {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st1.nextToken());
        long target = Long.parseLong(st1.nextToken());
        long[] a = new long[n];
        StringTokenizer st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) a[i] = Long.parseLong(st2.nextToken());

        Map<Long, Integer> need = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (need.containsKey(a[i])) {
                System.out.println(need.get(a[i]) + " " + i);
                return;
            }
            need.put(target - a[i], i);
        }
        System.out.println("-1 -1");
    }
}
