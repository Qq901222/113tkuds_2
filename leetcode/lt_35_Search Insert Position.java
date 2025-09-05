// 題目：Search Insert Position
// 給定一個升序排列且不重複的整數陣列 nums，以及一個目標值 target。
// 若 target 存在於陣列中，回傳其索引值；若不存在，則回傳它應該被插入的位置索引。
// 要求：演算法必須在 O(log n) 時間內完成。

class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid; // 找到目標
            } else if (nums[mid] < target) {
                left = mid + 1; // target 在右側
            } else {
                right = mid - 1; // target 在左側
            }
        }

        // 若沒找到，left 即為 target 應插入的位置
        return left;
    }
}

/*
解題思路：
1. 由於 nums 已排序，且題目要求 O(log n)，必須用二分搜尋。
2. 設定左右指標 left、right：
   - 當 nums[mid] == target → 回傳 mid。
   - 當 nums[mid] < target → 縮小到右半邊。
   - 當 nums[mid] > target → 縮小到左半邊。
3. 若迴圈結束仍沒找到，left 會停在 target 應插入的位置。
4. 範例：
   nums = [1,3,5,6], target = 5 → 回傳 2
   nums = [1,3,5,6], target = 2 → 回傳 1
   nums = [1,3,5,6], target = 7 → 回傳 4
5. 時間複雜度：O(log n)，二分搜尋。
   空間複雜度：O(1)。
*/
