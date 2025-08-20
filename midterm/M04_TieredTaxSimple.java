import java.io.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(readNonEmpty(br));
        long sum = 0;
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < n; i++) {
            long x = Long.parseLong(readNonEmpty(br));
            long tax = computeTax(x);
            sum += tax;
            out.append("Tax: ").append(tax).append('\n');
        }
        long avg = Math.round((double) sum / n);
        out.append("Average: ").append(avg).append('\n');
        System.out.print(out.toString());
    }

    static long computeTax(long income) {
        double tax = 0.0;
        if (income > 0) {
            long a = Math.min(income, 120000);
            tax += a * 0.05;
        }
        if (income > 120000) {
            long b = Math.min(income, 500000) - 120000;
            tax += b * 0.12;
        }
        if (income > 500000) {
            long c = Math.min(income, 1000000) - 500000;
            tax += c * 0.20;
        }
        if (income > 1000000) {
            long d = income - 1000000;
            tax += d * 0.30;
        }
        return Math.round(tax);
    }

    static String readNonEmpty(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null && s.trim().isEmpty()) {}
        return s == null ? "" : s.trim();
    }
}

/*
時間複雜度：O(n)
說明：每筆計稅為常數時間，走訪 n 筆輸入一次完成。
*/
