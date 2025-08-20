import java.io.*;

public class M05_GCD_LCM_Recursive {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] p = br.readLine().trim().split("\\s+");
        long a = Long.parseLong(p[0]);
        long b = Long.parseLong(p[1]);

        long g = gcd(a, b);
        long l = (a / g) * b;

        System.out.println("GCD: " + g);
        System.out.println("LCM: " + l);
    }

    static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

/*
時間複雜度：O(log min(a,b))
說明：遞迴歐幾里得每次模運算大幅縮小數值，最壞為費波那契情形也僅對數級。
*/

