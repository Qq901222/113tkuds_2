// File: lt_27_RemoveElement.java
// 題目：Remove Element
class Solution {
    public int removeElement(int[] nums, int val) {
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) nums[idx++] = nums[i];
        }
        return idx;
    }
}
/*
解題思路：
1. 遍歷數組，非 val 的元素覆蓋到前方。
2. idx 為最後長度。
3. 時間 O(n)，空間 O(1)。
*/