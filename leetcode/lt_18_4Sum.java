// 題目：4Sum
// 給定整數陣列 nums 與整數 target，找出所有不重複的四元組 [a,b,c,d] 使得
// nums[a] + nums[b] + nums[c] + nums[d] == target。答案順序不限。
import java.util.*;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        if (n < 4) return ans;

        Arrays.sort(nums);                     // 先排序以便雙指針與去重
        long T = target;                       // 避免整數溢位，後續以 long 相加比較

        for (int i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;  // 去重：固定 i

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;  // 去重：固定 j

                int l = j + 1, r = n - 1;
                while (l < r) {
                    long sum = (long) nums[i] + nums[j] + nums[l] + nums[r];

                    if (sum == T) {
                        ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        // 移動並跳過重複值
                        int lv = nums[l], rv = nums[r];
                        while (l < r && nums[l] == lv) l++;
                        while (l < r && nums[r] == rv) r--;
                    } else if (sum < T) {
                        l++;                  // 總和偏小，左指針右移
                    } else {
                        r--;                  // 總和偏大，右指針左移
                    }
                }
            }
        }
        return ans;
    }
}
/*
解題思路：
1. 排序：將陣列排序後，能用雙指針在 O(n^2) 內處理固定兩數後的兩數和問題，整體 O(n^3)。
2. 兩層迴圈固定 i、j，內層以 l、r 做夾逼：
   - sum = nums[i] + nums[j] + nums[l] + nums[r]（用 long 避免溢位）。
   - 若 sum == target，收錄並跳過重複值；sum < target 則 l++；sum > target 則 r--。
3. 去重：對 i、j、l、r 在移動時跳過相同數值，確保四元組唯一。
4. 複雜度：時間 O(n^3)，空間 O(1)（輸出除外）。
*/
