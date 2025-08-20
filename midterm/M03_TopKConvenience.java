import java.io.*;
import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        Item(String n, int q) { name = n; qty = q; }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(readNonEmpty(br));
        int n = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> {
            if (a.qty != b.qty) return Integer.compare(a.qty, b.qty);
            return b.name.compareTo(a.name);
        });

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(readNonEmpty(br));
            String name = st.nextToken();
            int qty = Integer.parseInt(st.nextToken());
            pq.offer(new Item(name, qty));
            if (pq.size() > K) pq.poll();
        }

        List<Item> list = new ArrayList<>(pq);
        list.sort((a, b) -> {
            if (a.qty != b.qty) return Integer.compare(b.qty, a.qty);
            return a.name.compareTo(b.name);
        });

        StringBuilder sb = new StringBuilder();
        for (Item it : list) sb.append(it.name).append(' ').append(it.qty).append('\n');
        System.out.print(sb.toString());
    }

    static String readNonEmpty(BufferedReader br) throws IOException {
        String s;
        while ((s = br.readLine()) != null && s.trim().isEmpty()) {}
        return s == null ? "" : s.trim();
    }
}

/*
時間複雜度：O(n log K)
說明：維護大小為 K 的最小堆；每筆入堆/出堆 O(log K)，處理 n 筆總計 O(n log K)。
*/
