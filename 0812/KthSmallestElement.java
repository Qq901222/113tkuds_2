import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestElement {

    public static int kthSmallestMaxHeap(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : arr) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }

    public static int kthSmallestMinHeap(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) minHeap.offer(num);
        for (int i = 1; i < k; i++) minHeap.poll();
        return minHeap.peek();
    }

    public static void main(String[] args) {
        int[][] testArrays = {
            {7, 10, 4, 3, 20, 15},
            {1},
            {3, 1, 4, 1, 5, 9, 2, 6}
        };
        int[] ks = {3, 1, 4};

        for (int i = 0; i < testArrays.length; i++) {
            int[] arr = testArrays[i];
            int k = ks[i];
            System.out.println("陣列: " + java.util.Arrays.toString(arr) + ", K=" + k);
            System.out.println("方法1(MaxHeap) → " + kthSmallestMaxHeap(arr, k));
            System.out.println("方法2(MinHeap) → " + kthSmallestMinHeap(arr, k));
            System.out.println();
        }
    }
}
//Max Heap（大小 K）：
//時間：O(N log K)
//空間：O(K)
//K 遠小於 N 時效率高

//Min Heap（取 K 次）：
//時間：O(N + K log N)
//空間：O(N)
//K 接近 N 時效率高