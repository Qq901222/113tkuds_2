// 題目：Container With Most Water
// 給定一個整數陣列 height，每個元素代表一條垂直線的高度。
// 找出兩條線與 x 軸構成的容器，使容器能儲存最多的水量。
// 回傳能容納的最大水量。

class Solution {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int max = 0;

        // 使用雙指針，從左右兩端開始收縮
        while (left < right) {
            int h = Math.min(height[left], height[right]);
            int width = right - left;
            max = Math.max(max, h * width);

            // 移動高度較小的一端，因為高度限制了容量
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }
}

/*
解題思路：
1. 直覺做法是窮舉所有左右組合，計算面積，時間複雜度 O(n^2)，但 n 可達 10^5，會超時。
2. 採用雙指針法：
   - 一開始指向最左和最右，計算面積。
   - 每次移動高度較小的一端，因為面積受限於「較小的高度」。
   - 持續收縮直到左右指針相遇。
3. 每次計算面積並更新最大值。
4. 時間複雜度 O(n)，空間複雜度 O(1)。
*/
