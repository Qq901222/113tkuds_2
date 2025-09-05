// 題目：Find First and Last Position of Element in Sorted Array
// 給定一個非遞減排序的整數陣列 nums，以及一個目標值 target。
// 找出 target 在陣列中第一次和最後一次出現的位置。
// 若 target 不存在於陣列中，回傳 [-1, -1]。
// 要求：演算法必須在 O(log n) 時間內完成。

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = findBound(nums, target, true);
        int last = findBound(nums, target, false);
        return new int[]{first, last};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int left = 0, right = nums.length - 1;
        int bound = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                bound = mid;
                if (isFirst) {
                    right = mid - 1; // 繼續往左找
                } else {
                    left = mid + 1;  // 繼續往右找
                }
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return bound;
    }
}

/*
解題思路：
1. 題目要求 O(log n)，所以必須用二分搜尋。
2. 分兩次二分搜尋：
   - 第一次找 target 的最左邊（first position）。
   - 第二次找 target 的最右邊（last position）。
3. findBound(nums, target, true) → 找左邊界。
   - 當找到 target 時，繼續往左縮小範圍。
4. findBound(nums, target, false) → 找右邊界。
   - 當找到 target 時，繼續往右縮小範圍。
5. 若找不到，回傳 -1。
6. 範例：
   nums = [5,7,7,8,8,10], target = 8 → [3,4]
   nums = [5,7,7,8,8,10], target = 6 → [-1,-1]
   nums = [], target = 0 → [-1,-1]
7. 時間複雜度：O(log n)，二分搜尋兩次。
   空間複雜度：O(1)。
*/
