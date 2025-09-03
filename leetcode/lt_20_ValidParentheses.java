// File: lt_20_ValidParentheses.java
// 題目：Valid Parentheses
import java.util.*;
class Solution {
    public boolean isValid(String s) {
        Deque<Character> st = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') st.push(c);
            else {
                if (st.isEmpty()) return false;
                char t = st.pop();
                if ((c == ')' && t != '(') || (c == '}' && t != '{') || (c == ']' && t != '[')) return false;
            }
        }
        return st.isEmpty();
    }
}
/*
解題思路：
1. 用堆疊保存左括號，遇右括號檢查是否匹配棧頂。
2. 遍歷完後棧需為空。
3. 時間 O(n)，空間 O(n)。
*/