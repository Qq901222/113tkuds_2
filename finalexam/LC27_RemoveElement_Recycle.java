import java.io.*;
import java.util.*;

public class LC27_RemoveElement_Recycle {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        if (first == null || first.isEmpty()) return;
        StringTokenizer st = new StringTokenizer(first);
        int n = Integer.parseInt(st.nextToken());
        int val = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        if (n > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());
        }

        int len = removeElement(nums, n, val);
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(nums[i]);
        }
        System.out.println();
    }

    static int removeElement(int[] nums, int n, int val) {
        int write = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] != val) nums[write++] = nums[i];
        }
        return write;
    }
}
