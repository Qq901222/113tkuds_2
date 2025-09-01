public class lt_09_palindromenumber {
    // 方法A：不轉字串，反轉後半
    public static boolean isPalindromeHalf(int x) {
        if (x < 0) return false;
        if (x % 10 == 0 && x != 0) return false;
        int rev = 0;
        while (x > rev) {
            rev = rev * 10 + x % 10;
            x /= 10;
        }
        return x == rev || x == rev / 10;
    }

    // 方法B：轉字串（簡單版）
    public static boolean isPalindromeStr(int x) {
        if (x < 0) return false;
        String s = Integer.toString(x);
        int l = 0, r = s.length() - 1;
        while (l < r) if (s.charAt(l++) != s.charAt(r--)) return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindromeHalf(121));  // true
        System.out.println(isPalindromeHalf(-121)); // false
        System.out.println(isPalindromeHalf(10));   // false
        System.out.println(isPalindromeStr(121));   // true
    }
}
