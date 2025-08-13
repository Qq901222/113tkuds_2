import java.util.*;

class MovingAverageStream {
    private final int size;
    private final Queue<Integer> window;
    private long sum;

    // 用於中位數的兩個堆
    private final PriorityQueue<Integer> minHeap;
    private final PriorityQueue<Integer> maxHeap;

    // 用於極值的 TreeMap
    private final TreeMap<Integer, Integer> minMaxMap;

    public MovingAverageStream(int size) {
        this.size = size;
        this.window = new LinkedList<>();
        this.sum = 0;
        this.minHeap = new PriorityQueue<>();
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        this.minMaxMap = new TreeMap<>();
    }

    public double next(int val) {
        // Step 1: 處理新加入的元素
        window.offer(val);
        sum += val;
        minMaxMap.put(val, minMaxMap.getOrDefault(val, 0) + 1);

        // Step 2: 處理滑動視窗移除元素
        if (window.size() > size) {
            int oldVal = window.poll();
            sum -= oldVal;
            minMaxMap.put(oldVal, minMaxMap.get(oldVal) - 1);
            if (minMaxMap.get(oldVal) == 0) {
                minMaxMap.remove(oldVal);
            }

            // 同步移除堆中的元素 (這裡需要特殊的處理，我們稍後再討論)
            if (maxHeap.contains(oldVal)) {
                maxHeap.remove(oldVal);
            } else {
                minHeap.remove(oldVal);
            }
        }

        // Step 3: 更新並平衡兩個堆以計算中位數
        if (maxHeap.isEmpty() || val <= maxHeap.peek()) {
            maxHeap.offer(val);
        } else {
            minHeap.offer(val);
        }

        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }

        return (double) sum / window.size();
    }

    public double getMedian() {
        if (window.isEmpty()) {
            throw new IllegalStateException("Window is empty.");
        }
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        } else {
            // 如果大小相同，中位數是兩個堆頂的平均值
            return (double) (maxHeap.peek() + minHeap.peek()) / 2;
        }
    }

    public int getMin() {
        if (minMaxMap.isEmpty()) {
            throw new IllegalStateException("Window is empty.");
        }
        return minMaxMap.firstKey();
    }

    public int getMax() {
        if (minMaxMap.isEmpty()) {
            throw new IllegalStateException("Window is empty.");
        }
        return minMaxMap.lastKey();
    }
}