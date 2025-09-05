// 題目：Search in Rotated Sorted Array
// 給定一個可能被旋轉過的升序整數陣列 nums（所有元素互異），以及一個目標值 target。
// 請在陣列中找到 target 並回傳其索引值，若不存在則回傳 -1。
// 要求：演算法必須在 O(log n) 時間內完成。

class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // 判斷哪一半是有序的
            if (nums[left] <= nums[mid]) {
                // 左半邊有序
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // target 在左半邊
                } else {
                    left = mid + 1;  // target 在右半邊
                }
            } else {
                // 右半邊有序
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;  // target 在右半邊
                } else {
                    right = mid - 1; // target 在左半邊
                }
            }
        }

        return -1; // 沒找到
    }
}

/*
解題思路：
1. 陣列原本是升序，但可能在某個點被旋轉。
   - 範例：[0,1,2,4,5,6,7] → 旋轉後 [4,5,6,7,0,1,2]
2. 使用二分搜尋（Binary Search），但每次要判斷哪一半是「有序的」。
   - 若 nums[left] <= nums[mid]，表示左半邊有序。
   - 否則右半邊有序。
3. 根據 target 是否落在有序區間內，決定往左或往右縮小搜尋範圍。
4. 範例：
   nums = [4,5,6,7,0,1,2], target = 0 → 回傳 4
   nums = [4,5,6,7,0,1,2], target = 3 → 回傳 -1
   nums = [1], target = 0 → 回傳 -1
5. 時間複雜度：O(log n)，因為每次二分搜尋。
   空間複雜度：O(1)。
*/
