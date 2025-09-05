import java.io.*;
import java.util.*;

public class LC39_CombinationSum_PPE {
    static int n, target;
    static int[] cand;
    static List<Integer> path = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        cand = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) cand[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(cand);

        dfs(0, target);
    }

    static void dfs(int start, int remain) {
        if (remain == 0) {
            printPath();
            return;
        }
        for (int i = start; i < n; i++) {
            if (cand[i] > remain) break;
            path.add(cand[i]);
            dfs(i, remain - cand[i]); // 可重複用
            path.remove(path.size() - 1);
        }
    }

    static void printPath() {
        for (int i = 0; i < path.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(path.get(i));
        }
        System.out.println();
    }
}
