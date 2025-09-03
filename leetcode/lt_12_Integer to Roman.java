// 題目：Integer to Roman
// 給定一個整數 num (1 <= num <= 3999)，將它轉換成對應的羅馬數字。
// 規則：
// 1. 羅馬數字由七個符號組成：I=1, V=5, X=10, L=50, C=100, D=500, M=1000
// 2. 使用減法表示法處理 4、9 開頭的數字，如 4=IV, 9=IX, 40=XL, 90=XC, 400=CD, 900=CM
// 3. 連續相加時，I, X, C, M 最多連續 3 次，不能出現 4 次。

class Solution {
    public String intToRoman(int num) {
        // 對應的數值和符號，從大到小排列
        int[] values =    {1000, 900, 500, 400, 100, 90,  50, 40,  10, 9,   5, 4,  1};
        String[] symbols = {"M", "CM","D", "CD","C","XC","L","XL","X","IX","V","IV","I"};

        StringBuilder sb = new StringBuilder();

        // 貪心法：每次盡量匹配最大符號，直到 num 減為 0
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }

        return sb.toString();
    }
}

/*
解題思路：
1. 羅馬數字有固定的符號與組合，將數字映射到對應的符號。
2. 使用「貪心法」：從最大的數值開始，逐步減去並附加符號。
   - 例如 num=1994：
     - 減 1000 → M
     - 減 900 → CM
     - 減 90 → XC
     - 減 4 → IV
     - 結果 = MCMXCIV
3. 因為 num 最大 3999，所以數組長度有限，效能佳。
4. 時間複雜度 O(1)，因為最多執行固定的 13 種判斷。
   空間複雜度 O(1)，僅需少量輔助結構。
*/
