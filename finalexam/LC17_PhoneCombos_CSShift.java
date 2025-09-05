import java.io.*;

public class LC17_PhoneCombos_CSShift {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String digits = br.readLine();
        if (digits == null) return;
        digits = digits.trim();
        if (digits.isEmpty()) return;

        String[] map = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
        StringBuilder sb = new StringBuilder();
        dfs(0, digits, map, sb);
    }

    static void dfs(int i, String digits, String[] map, StringBuilder sb) {
        if (i == digits.length()) {
            System.out.println(sb.toString());
            return;
        }
        String letters = map[digits.charAt(i) - '2'];
        for (int k = 0; k < letters.length(); k++) {
            sb.append(letters.charAt(k));
            dfs(i + 1, digits, map, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
