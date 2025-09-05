// 題目：Valid Sudoku
// 給定一個 9x9 的數獨盤面，判斷其是否有效。
// 規則：
// 1. 每一行(row) 不能有重複的數字 (1~9)。
// 2. 每一列(column) 不能有重複的數字。
// 3. 每一個 3x3 小方格 不能有重複的數字。
// 注意：
// - 空格以 '.' 表示，不需要檢查。
// - 僅需檢查盤面是否「有效」，不需要判斷是否可解。

class Solution {
    public boolean isValidSudoku(char[][] board) {
        // 使用三個集合紀錄 (row, col, box)
        HashSet<String> seen = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    // 生成唯一 key，避免重複
                    String rowKey = num + " in row " + i;
                    String colKey = num + " in col " + j;
                    String boxKey = num + " in box " + (i / 3) + "-" + (j / 3);

                    if (!seen.add(rowKey) || !seen.add(colKey) || !seen.add(boxKey)) {
                        return false; // 若重複，則不合法
                    }
                }
            }
        }
        return true;
    }
}

/*
解題思路：
1. 使用 HashSet 檢查是否有重複數字。
   - 每個數字的出現位置用 key 記錄 (row、col、box)。
   - 若同一數字在相同 row / col / box 出現過，則不合法。
2. 算法流程：
   - 遍歷整個 9x9 盤面。
   - 當遇到數字時，檢查：
     - 該數字是否已出現在相同行。
     - 該數字是否已出現在相同列。
     - 該數字是否已出現在相同 3x3 小方格。
   - 若有任一重複，回傳 false。
   - 否則最後回傳 true。
3. 範例：
   board = Example 1 → true
   board = Example 2 → false，因為左上角 3x3 小方格出現兩個 '8'。
4. 時間複雜度：O(81) ≈ O(1)，因為棋盤大小固定。
   空間複雜度：O(81) ≈ O(1)，最多存 81 個 key。
*/
