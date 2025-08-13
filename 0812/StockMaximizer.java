import java.util.*;

public class StockMaximizer {

    public static int maxProfit(int[] prices, int k) {
        if (prices == null || prices.length < 2 || k <= 0) return 0;

        List<Integer> profits = new ArrayList<>();
        int i = 0;
        while (i < prices.length - 1) {
            // 找到下一個谷底（買入點）
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) i++;
            int buy = prices[i];

            // 找到下一個峰頂（賣出點）
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) i++;
            int sell = prices[i];

            if (sell > buy) {
                profits.add(sell - buy);
            }
        }

        // 如果沒有任何獲利交易
        if (profits.isEmpty()) return 0;

        // 使用最大堆取出前 K 大的獲利
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.addAll(profits);

        int totalProfit = 0;
        while (k > 0 && !maxHeap.isEmpty()) {
            totalProfit += maxHeap.poll();
            k--;
        }
        return totalProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{2, 4, 1}, 2));       // 2
        System.out.println(maxProfit(new int[]{3, 2, 6, 5, 0, 3}, 2)); // 7
        System.out.println(maxProfit(new int[]{1, 2, 3, 4, 5}, 2)); // 4
    }
}
