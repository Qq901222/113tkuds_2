// File: lt_22_GenerateParentheses.java
// 題目：Generate Parentheses
import java.util.*;
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(n, 0, 0, new StringBuilder(), res);
        return res;
    }
    private void dfs(int n, int open, int close, StringBuilder path, List<String> res) {
        if (path.length() == 2 * n) { res.add(path.toString()); return; }
        if (open < n) { path.append('('); dfs(n, open + 1, close, path, res); path.deleteCharAt(path.length() - 1); }
        if (close < open) { path.append(')'); dfs(n, open, close + 1, path, res); path.deleteCharAt(path.length() - 1); }
    }
}
/*
解題思路：
1. 回溯生成所有括號組合，限制左括號 ≤ n、右括號 ≤ 左括號。
2. 當長度為 2n 時加入結果。
3. 時間 O(Cn)，空間 O(n)。
*/