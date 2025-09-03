// File: lt_30_SubstringWithConcatenation.java
// 題目：Substring with Concatenation of All Words
import java.util.*;
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s.isEmpty() || words.length == 0) return res;
        int wordLen = words[0].length(), total = wordLen * words.length;
        Map<String,Integer> wordCount = new HashMap<>();
        for (String w : words) wordCount.put(w, wordCount.getOrDefault(w, 0) + 1);
        for (int i = 0; i < wordLen; i++) {
            int left = i, count = 0; Map<String,Integer> seen = new HashMap<>();
            for (int j = i; j + wordLen <= s.length(); j += wordLen) {
                String w = s.substring(j, j + wordLen);
                if (wordCount.containsKey(w)) {
                    seen.put(w, seen.getOrDefault(w, 0) + 1);
                    count++;
                    while (seen.get(w) > wordCount.get(w)) {
                        String lw = s.substring(left, left + wordLen);
                        seen.put(lw, seen.get(lw) - 1);
                        left += wordLen; count--;
                    }
                    if (count == words.length) res.add(left);
                } else {
                    seen.clear(); count = 0; left = j + wordLen;
                }
            }
        }
        return res;
    }
}
/*
解題思路：
1. 固定窗口長度 total，滑動窗口檢查單詞頻率。
2. 使用兩個 hashmap：words 計數與窗口 seen 計數。
3. 時間 O(n * wordLen)，空間 O(k)。
*/
