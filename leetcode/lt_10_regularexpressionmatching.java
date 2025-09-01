public class lt_10_regularexpressionmatching {

    // 方法A：DP（與上面 Solution 同）
    public static boolean isMatchDP(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int j = 2; j <= n; j++) {
            if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 2];
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);
                if (pc == '*') {
                    dp[i][j] = dp[i][j - 2];
                    char prev = p.charAt(j - 2);
                    if (prev == '.' || prev == s.charAt(i - 1)) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                } else {
                    if (pc == '.' || pc == s.charAt(i - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[m][n];
    }

    // 方法B：遞迴 + 記憶化（同樣 O(mn)）
    public static boolean isMatchMemo(String s, String p) {
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return dfs(0, 0, s, p, memo);
    }

    private static boolean dfs(int i, int j, String s, String p, Boolean[][] memo) {
        if (memo[i][j] != null) return memo[i][j];
        if (j == p.length()) return memo[i][j] = (i == s.length());

        boolean first = (i < s.length()) && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i));
        boolean ans;
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            ans = dfs(i, j + 2, s, p, memo) || (first && dfs(i + 1, j, s, p, memo));
        } else {
            ans = first && dfs(i + 1, j + 1, s, p, memo);
        }
        return memo[i][j] = ans;
    }

    public static void main(String[] args) {
        System.out.println(isMatchDP("aa", "a"));   // false
        System.out.println(isMatchDP("aa", "a*"));  // true
        System.out.println(isMatchDP("ab", ".*"));  // true

        System.out.println(isMatchMemo("aa", "a"));   // false
        System.out.println(isMatchMemo("aa", "a*"));  // true
        System.out.println(isMatchMemo("ab", ".*"));  // true
    }
}
