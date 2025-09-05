// 題目：Sudoku Solver
// 給定一個 9x9 的數獨盤面，請填滿空格使數獨合法。
// 規則：
// 1. 每一行(row) 不能有重複的數字 (1~9)。
// 2. 每一列(column) 不能有重複的數字。
// 3. 每一個 3x3 小方格不能有重複的數字。
// 注意：
// - 空格以 '.' 表示。
// - 請直接修改原盤面，不需要回傳新盤面。
// - 保證給定的盤面至少有一個解。

class Solution {
    public void solveSudoku(char[][] board) {
        backtrack(board);
    }

    private boolean backtrack(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, i, j, c)) {
                            board[i][j] = c;
                            if (backtrack(board)) return true;
                            board[i][j] = '.'; // 回溯
                        }
                    }
                    return false; // 若 1-9 都不能放，回傳 false
                }
            }
        }
        return true; // 全部填滿
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        // 檢查行、列、3x3 小方格
        for (int k = 0; k < 9; k++) {
            if (board[row][k] == c) return false; // 行重複
            if (board[k][col] == c) return false; // 列重複
            if (board[(row/3)*3 + k/3][(col/3)*3 + k%3] == c) return false; // 小方格重複
        }
        return true;
    }
}

/*
解題思路：
1. 使用回溯法 (Backtracking) 填充每個空格。
2. 遍歷盤面，遇到 '.' 時：
   - 嘗試填入 '1' 到 '9'。
   - 若符合數獨規則，遞迴下一格。
   - 若遞迴失敗，回溯並嘗試下一個數字。
3. isValid 函數檢查行、列、以及 3x3 小方格是否重複。
4. 遞迴直到整個盤面填滿，則完成解答。
5. 範例：
   board = [["5","3",".",".","7",".",".",".","."], ...] → 解後完整盤面
6. 時間複雜度：最壞 O(9^(n))，n 為空格數。
   空間複雜度：O(n) 遞迴棧。
*/
