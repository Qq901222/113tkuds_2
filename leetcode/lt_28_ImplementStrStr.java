// File: lt_28_ImplementStrStr.java
// 題目：Implement strStr()
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        int n = haystack.length(), m = needle.length();
        for (int i = 0; i + m <= n; i++) {
            if (haystack.substring(i, i + m).equals(needle)) return i;
        }
        return -1;
    }
}
/*
解題思路：
1. 暴力檢查子字串是否等於 needle。
2. 可優化為 KMP。
3. 時間 O((n-m+1)*m)，空間 O(1)。
*/