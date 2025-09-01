import java.util.*;

public class lt_03_longestsubstring {
    public static int lengthSet(String s) {
        Set<Character> set = new HashSet<>();
        int ans = 0, left = 0;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            while (set.contains(c)) set.remove(s.charAt(left++));
            set.add(c);
            int len = r - left + 1;
            if (len > ans) ans = len;
        }
        return ans;
    }

    public static int lengthMap(String s) {
        Map<Character,Integer> last = new HashMap<>();
        int ans = 0, left = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            Integer p = last.put(c, i);
            if (p != null && p >= left) left = p + 1;
            int len = i - left + 1;
            if (len > ans) ans = len;
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] arr = {"abcabcbb","bbbbb","pwwkew","au","dvdf"};
        for (String s : arr) System.out.println(lengthSet(s) + " " + lengthMap(s));
    }
}
