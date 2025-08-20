import java.io.*;

public class M06_PalindromeClean {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null) s = "";
        int l = 0, r = s.length() - 1;
        boolean ok = true;
        while (l < r) {
            char cl = s.charAt(l), cr = s.charAt(r);
            if (!isAsciiLetter(cl)) { l++; continue; }
            if (!isAsciiLetter(cr)) { r--; continue; }
            if (toLowerAscii(cl) != toLowerAscii(cr)) { ok = false; break; }
            l++; r--;
        }
        System.out.println(ok ? "Yes" : "No");
    }

    static boolean isAsciiLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    static char toLowerAscii(char c) {
        return (c >= 'A' && c <= 'Z') ? (char)(c - 'A' + 'a') : c;
    }
}
