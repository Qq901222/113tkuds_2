// 題目：Longest Common Prefix
// 給定一個字串陣列 strs，找出其中的最長共同前綴字串。
// 如果沒有共同前綴，回傳空字串 ""。

class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        // 先假設第一個字串是共同前綴
        String prefix = strs[0];

        // 從第二個字串開始逐一比對
        for (int i = 1; i < strs.length; i++) {
            // 不斷縮短 prefix，直到符合當前字串的開頭
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }
}

/*
解題思路：
1. 假設第一個字串為共同前綴，逐一與後續字串比對。
2. 若當前字串不是以 prefix 開頭，則縮短 prefix (去掉最後一個字元)。
3. 持續縮短直到符合或 prefix 變成空字串。
4. 最終得到的 prefix 即為最長共同前綴。
5. 範例：
   ["flower","flow","flight"]
   初始 prefix = "flower"
   與 "flow" 比較 → prefix 縮短為 "flow"
   與 "flight" 比較 → prefix 縮短為 "fl"
   結果 = "fl"
6. 時間複雜度 O(S)，S = 所有字元總數。
   空間複雜度 O(1)。
*/
