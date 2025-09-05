// 題目：Combination Sum II
// 給定一個整數陣列 candidates 和目標值 target，
// 找出所有候選數字的「不重複組合」使其和為 target。
// 規則：
// 1. 每個數字只能使用一次。
// 2. 結果不能包含重複組合。
// 3. 結果可以以任意順序返回。

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 排序方便剪枝和去重
        backtrack(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] candidates, int target, int start,
                           List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue; // 去重
            if (candidates[i] > target) break; // 剪枝

            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i + 1, path, result); // 下一輪不能重複使用 i
            path.remove(path.size() - 1); // 回溯
        }
    }
}

/*
解題思路：
1. 使用回溯法 (Backtracking) 遍歷所有可能組合。
2. 排序 candidates 方便：
   - 剪枝：當 candidates[i] > target 時停止迴圈。
   - 去重：跳過與前一個數字相同的分支。
3. 對於每個候選數字：
   - 選擇它，遞迴下一輪，start 索引改為 i+1，避免重複使用。
4. 若 target == 0，表示找到合法組合，加入結果集。
5. 範例：
   - candidates = [10,1,2,7,6,1,5], target = 8 → [[1,1,6],[1,2,5],[1,7],[2,6]]
6. 時間複雜度：取決於組合數，最壞情況接近 O(2^n)。
   空間複雜度：O(n) 遞迴棧 + 結果集。
*/
