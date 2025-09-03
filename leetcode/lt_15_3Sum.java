// 題目：3Sum
// 給定一個整數陣列 nums，找出所有不重複的三元組 [nums[i], nums[j], nums[k]]，使得：
// i != j, i != k, j != k，並且 nums[i] + nums[j] + nums[k] == 0。
// 解答不能包含重複的三元組。

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 先排序，方便去重與雙指針操作

        for (int i = 0; i < nums.length - 2; i++) {
            // 避免重複三元組：跳過相同的起始數字
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 移動指針並跳過重複元素
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++; // 和太小，左指針右移
                } else {
                    right--; // 和太大，右指針左移
                }
            }
        }
        return result;
    }
}

/*
解題思路：
1. 排序：先對陣列進行排序，方便去除重複並使用雙指針。
2. 遍歷：固定一個數 nums[i]，然後用雙指針尋找另外兩個數，使三數之和為 0。
   - 左指針從 i+1 開始，右指針從結尾開始。
   - 若總和 < 0，左指針右移（增加總和）。
   - 若總和 > 0，右指針左移（減少總和）。
   - 若總和 = 0，加入結果，並跳過重複元素。
3. 去重：避免相同數字產生相同的三元組。
4. 範例：
   nums = [-1,0,1,2,-1,-4] → 排序後 [-4,-1,-1,0,1,2]
   結果 = [[-1,-1,2], [-1,0,1]]
5. 時間複雜度 O(n^2)，因為外層遍歷 O(n)，內層雙指針 O(n)。
   空間複雜度 O(1)，不計輸出空間。
*/
