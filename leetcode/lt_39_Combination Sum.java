// 題目：Combination Sum
// 給定一個由不同整數組成的陣列 candidates 和一個目標值 target，
// 找出所有候選數字的「不重複組合」使其和為 target。
// 規則：
// 1. 每個候選數字可以被無限次選用。
// 2. 組合唯一性：若某個數字的出現頻率不同，則視為不同組合。
// 3. 結果可以以任意順序返回。

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates); // 可選，方便剪枝
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
            if (candidates[i] > target) continue; // 剪枝
            path.add(candidates[i]);
            backtrack(candidates, target - candidates[i], i, path, result); // 可以重複使用 i
            path.remove(path.size() - 1); // 回溯
        }
    }
}

/*
解題思路：
1. 使用回溯法 (Backtracking) 遍歷所有可能組合。
2. 對於每個候選數字：
   - 如果選擇它，將 target 減去該數字並遞迴下一步。
   - 可以重複使用同一個數字，所以下一輪遞迴起始索引仍為 i。
3. 若 target == 0，表示找到合法組合，加入結果集。
4. 若 target < 0，則剪枝，不再遞迴。
5. 範例：
   - candidates = [2,3,6,7], target = 7 → [[2,2,3],[7]]
6. 時間複雜度：取決於組合數，最壞情況接近 O(2^target)。
   空間複雜度：O(target) 遞迴棧 + 結果集。
*/
