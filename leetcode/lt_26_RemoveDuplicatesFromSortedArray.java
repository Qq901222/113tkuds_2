// File: lt_26_RemoveDuplicatesFromSortedArray.java
// 題目：Remove Duplicates from Sorted Array
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int idx = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) nums[idx++] = nums[i];
        }
        return idx;
    }
}
/*
解題思路：
1. 用 idx 指標覆蓋重複元素。
2. 返回 idx 即為新長度。
3. 時間 O(n)，空間 O(1)。
*/