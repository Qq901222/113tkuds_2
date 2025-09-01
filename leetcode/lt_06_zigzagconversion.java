public class lt_06_zigzagconversion {
    public static String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) return s;
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) rows[i] = new StringBuilder();
        int r = 0, step = 1;
        for (int i = 0; i < s.length(); i++) {
            rows[r].append(s.charAt(i));
            if (r == 0) step = 1;
            else if (r == numRows - 1) step = -1;
            r += step;
        }
        StringBuilder ans = new StringBuilder();
        for (StringBuilder sb : rows) ans.append(sb);
        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(convert("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR
        System.out.println(convert("PAYPALISHIRING", 4)); // PINALSIGYAHRPI
        System.out.println(convert("A", 1));              // A
    }
}
