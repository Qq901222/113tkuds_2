// 題目：Next Permutation
// 給定一個整數陣列 nums，找出其字典序中的「下一個排列」。
// - 下一個排列是指比當前排列更大的最小排列。
// - 如果已經是最大排列，則將 nums 轉換成最小排列（升序）。
// 要求：演算法必須原地修改（in-place），並且只使用常數額外空間。

class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;

        // 1. 從後往前找到第一個遞減的位置
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        if (i >= 0) {
            int j = n - 1;
            // 2. 從後往前找到第一個比 nums[i] 大的數
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            // 3. 交換 nums[i] 和 nums[j]
            swap(nums, i, j);
        }

        // 4. 將 i 之後的數字反轉，得到最小排列
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}

/*
解題思路：
1. 從右往左，找到第一個遞減元素 nums[i]（即 nums[i] < nums[i+1]）。
   - 如果整個陣列是非遞增（如 [3,2,1]），說明已經是最大排列，直接反轉得到最小排列。
2. 從右往左，找到第一個比 nums[i] 大的元素 nums[j]。
   - 保證交換後仍然是比原排列大的最小值。
3. 交換 nums[i] 和 nums[j]。
4. 將 i+1 之後的部分反轉（因為這段原本是遞減的，反轉後變成最小升序）。

範例：
nums = [1,2,3] → 下一個排列 [1,3,2]
nums = [3,2,1] → 已是最大排列 → 最小排列 [1,2,3]
nums = [1,1,5] → 下一個排列 [1,5,1]

時間複雜度：O(n)，最壞情況需要掃描兩次陣列並反轉。
空間複雜度：O(1)，只需常數額外空間。
*/
