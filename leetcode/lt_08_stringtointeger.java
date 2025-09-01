public class lt_08_stringtointeger {
    public static int myAtoi(String s) {
        int n = s.length(), i = 0;
        while (i < n && s.charAt(i) == ' ') i++;
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            if (s.charAt(i) == '-') sign = -1;
            i++;
        }
        int res = 0;
        while (i < n) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') break;
            int d = c - '0';
            if (res > 214748364 || (res == 214748364 && (sign == 1 ? d > 7 : d > 8)))
                return sign == 1 ? 2147483647 : -2147483648;
            res = res * 10 + d;
            i++;
        }
        return sign * res;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("   -042"));
        System.out.println(myAtoi("1337c0d3"));
        System.out.println(myAtoi("0-1"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332"));
        System.out.println(myAtoi("2147483648"));
    }
}
