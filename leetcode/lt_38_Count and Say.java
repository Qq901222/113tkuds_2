// 題目：Count and Say
// 給定一個正整數 n，請回傳 count-and-say 序列的第 n 項。
// 規則：
// 1. countAndSay(1) = "1"
// 2. countAndSay(n) 是 countAndSay(n-1) 的 run-length encoding (RLE)
//    - RLE：將連續相同的數字壓縮為「出現次數 + 數字」的形式。
// 範例：
// - "1" → "11" （一個 1）
// - "11" → "21" （兩個 1）
// - "21" → "1211" （一個 2、一個 1）
// 注意：
// - 輸入 n 的範圍：1 <= n <= 30
// - 請嘗試使用迭代方法。

class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";

        String prev = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int j = 0; j < prev.length(); j++) {
                if (j + 1 < prev.length() && prev.charAt(j) == prev.charAt(j + 1)) {
                    count++;
                } else {
                    sb.append(count).append(prev.charAt(j));
                    count = 1;
                }
            }
            prev = sb.toString();
        }
        return prev;
    }
}

/*
解題思路：
1. 從第 1 項 "1" 開始，逐步計算第 n 項。
2. 對於第 i 項：
   - 使用迴圈遍歷前一項的字串。
   - 計算每個連續相同字符的出現次數 (count)。
   - 將 count + 字符 加入新的字串。
3. 將新字串設為當前項，繼續下一輪迭代。
4. 範例：
   - n = 4 → "1211"
     1 → "1"
     2 → "11"
     3 → "21"
     4 → "1211"
5. 時間複雜度：O(2^n)，每一項字串長度近似翻倍。
   空間複雜度：O(2^n)，用於保存字串。
*/
