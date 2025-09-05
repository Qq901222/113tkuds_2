import java.io.*;
import java.util.*;

public class LC34_SearchRange_DelaySpan {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String first = br.readLine();
        if (first == null || first.isEmpty()) return;
        StringTokenizer st = new StringTokenizer(first);
        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int[] nums = new int[n];
        if (n > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());
        }

        int left = lowerBound(nums, target);
        if (left == n || nums[left] != target) {
            System.out.println("-1 -1");
            return;
        }
        int right = lowerBound(nums, target + 1) - 1;
        System.out.println(left + " " + right);
    }

    static int lowerBound(int[] arr, int x) {
        int l = 0, r = arr.length;
        while (l < r) {
            int mid = (l + r) >>> 1;
            if (arr[mid] < x) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}
