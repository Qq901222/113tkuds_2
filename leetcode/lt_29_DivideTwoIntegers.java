// File: lt_29_DivideTwoIntegers.java
// 題目：Divide Two Integers
class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
        long a = Math.abs((long) dividend), b = Math.abs((long) divisor);
        int sign = ((dividend < 0) ^ (divisor < 0)) ? -1 : 1;
        long res = 0;
        while (a >= b) {
            long t = b, m = 1;
            while (a >= (t << 1)) { t <<= 1; m <<= 1; }
            a -= t; res += m;
        }
        return (int) (sign * res);
    }
}
/*
解題思路：
1. 用 long 避免溢位，特殊情況直接返回。
2. 位運算加速減法實現除法。
3. 時間 O(log n)，空間 O(1)。
*/