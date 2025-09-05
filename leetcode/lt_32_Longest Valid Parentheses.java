// 題目：Longest Valid Parentheses
// 給定一個只包含 '(' 和 ')' 的字串 s，請回傳「最長有效括號子字串」的長度。
// 有效括號：括號配對正確，且左右順序正確。

class Solution {
    public int longestValidParentheses(String s) {
        int maxLen = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1); // 初始基準點，避免空堆疊問題

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    // 當前不匹配，重新設定基準點
                    stack.push(i);
                } else {
                    // 計算有效長度
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}

/*
解題思路：
1. 使用「堆疊」追蹤括號匹配。
   - 初始化放入 -1 作為基準，處理 "()()" 這種情況。
2. 遍歷字串：
   - 若為 '('，將索引 i 入堆疊。
   - 若為 ')'，將堆疊頂元素彈出：
     - 若堆疊為空，表示不匹配，將 i 作為新的基準點。
     - 若堆疊非空，當前長度 = i - stack.peek()。
3. 更新最大長度 maxLen。
4. 範例：
   s = "(()" → 最長有效長度 = 2 ("()")
   s = ")()())" → 最長有效長度 = 4 ("()()")
   s = "" → 最長有效長度 = 0
5. 時間複雜度：O(n)，遍歷一次字串。
   空間複雜度：O(n)，堆疊最壞情況存放所有索引。

補充：也可以用 DP 解法，dp[i] 表示以 s[i] 結尾的最長有效括號長度。
*/
