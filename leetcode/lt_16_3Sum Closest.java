// 題目：3Sum Closest
// 給定整數陣列 nums 與整數 target，找出三個整數，使其總和最接近 target，並回傳該總和（保證唯一解）。
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        java.util.Arrays.sort(nums);                 // 先排序，方便雙指針夾逼
        int n = nums.length;
        int closest = nums[0] + nums[1] + nums[2];   // n ≥ 3，先取前三個作為初始解

        for (int i = 0; i < n - 2; i++) {
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];

                if (Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;                   // 更新更接近 target 的總和
                }

                if (sum == target) return target;    // 已經最接近，不可能更好
                if (sum < target)  l++;              // 總和偏小，左指針右移增大總和
                else               r--;              // 總和偏大，右指針左移減小總和
            }
        }
        return closest;
    }
}
/*
解題思路：
1. 排序 + 雙指針：固定一個數 nums[i] 後，利用 l、r 雙指針在有序區間內尋找最接近 target - nums[i] 的兩數和，整體 O(n^2)。
2. 夾逼規則：sum 與 target 比較：
   - 若更接近則更新 closest。
   - sum == target 直接回傳 target。
   - sum < target → l++；sum > target → r--。
3. 初始值：以前三數和作為 closest，確保有合法基準。
4. 時間複雜度 O(n^2)，空間複雜度 O(1)。
*/
