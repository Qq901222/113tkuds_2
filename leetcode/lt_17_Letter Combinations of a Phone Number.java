// 題目：Letter Combinations of a Phone Number
// 給定一個只包含數字 2-9 的字串，回傳所有可能的字母組合（電話按鍵對應）。
// 若輸入為空字串，回傳空列表。
import java.util.*;

class Solution {
    private static final String[] KEYS = {
        "",    // 0
        "",    // 1
        "abc", // 2
        "def", // 3
        "ghi", // 4
        "jkl", // 5
        "mno", // 6
        "pqrs",// 7
        "tuv", // 8
        "wxyz" // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) return result; // 空字串直接回傳

        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder path, List<String> result) {
        if (index == digits.length()) {
            result.add(path.toString()); // 完整組合加入結果
            return;
        }

        String letters = KEYS[digits.charAt(index) - '0'];
        for (char c : letters.toCharArray()) {
            path.append(c); // 選擇
            backtrack(digits, index + 1, path, result); // 遞迴處理下一位
            path.deleteCharAt(path.length() - 1); // 回溯
        }
    }
}
/*
解題思路：
1. 題目要根據電話按鍵對應數字找所有可能字母組合。
2. 使用回溯（Backtracking）：
   - 每位數字對應一組字母，逐一選擇並遞迴生成字串。
   - 當 index == digits.length()，表示一個完整組合產生，加入結果集。
3. 特殊情況：digits 為空字串直接回傳空列表。
4. 時間複雜度 O(4^n * n)，n 為 digits 長度，最壞情況每位最多 4 個字母。
5. 空間複雜度 O(n)（遞迴深度）。
*/
