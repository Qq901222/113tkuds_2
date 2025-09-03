// 題目：Regular Expression Matching
// 給定字串 s 與模式 p，支援 '.' 與 '*' 的正則匹配。
// '.' 可以匹配任意一個字元
// '*' 可以匹配零個或多個前一個元素
// 要求：匹配必須涵蓋整個字串（不能只部分匹配）

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // dp[i][j] 表示 s 前 i 個字元 與 p 前 j 個字元 是否能匹配
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true; // 空字串與空模式相符

        // 預處理：處理像 a*, a*b*, a*b*c* 這樣可以匹配空字串的情況
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 開始填 dp 表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '.' || pc == sc) {
                    // 當前字元匹配
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    // '*' 代表 0 次 或 多次
                    // 0 次：忽略前一個字元與 '*' (j-2)
                    dp[i][j] = dp[i][j - 2];
                    // 多次：若 s[i-1] 能與 p[j-2] 匹配，則看前一個字元
                    char prev = p.charAt(j - 2);
                    if (prev == '.' || prev == sc) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }
        return dp[m][n];
    }
}

/*
解題思路：
1. 使用動態規劃 (DP)，dp[i][j] 表示 s 的前 i 個字元與 p 的前 j 個字元是否匹配。
2. 狀態轉移：
   - 當 p[j-1] 是普通字元或 '.'：檢查 dp[i-1][j-1]。
   - 當 p[j-1] 是 '*'：
       a. 匹配 0 次：dp[i][j] = dp[i][j-2]
       b. 匹配多次：若 s[i-1] 與 p[j-2] 相符，dp[i][j] |= dp[i-1][j]
3. 初始化：
   - dp[0][0] = true
   - 對於能匹配空字串的模式 (如 a*, a*b*, ...)，先處理 dp[0][j]
4. 時間複雜度 O(m * n)，其中 m = s.length, n = p.length
   空間複雜度 O(m * n)
*/
