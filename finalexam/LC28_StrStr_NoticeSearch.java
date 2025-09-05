import java.io.*;

public class LC28_StrStr_NoticeSearch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String haystack = br.readLine();
        String needle = br.readLine();
        if (haystack == null) haystack = "";
        if (needle == null) needle = "";

        System.out.println(strStr(haystack, needle));
    }

    static int strStr(String h, String n) {
        if (n.length() == 0) return 0;
        if (n.length() > h.length()) return -1;
        for (int i = 0; i <= h.length() - n.length(); i++) {
            int j = 0;
            while (j < n.length() && h.charAt(i + j) == n.charAt(j)) j++;
            if (j == n.length()) return i;
        }
        return -1;
    }
}
