import java.util.*;

public class MergeKSortedArrays {
    static class Element {
        int value;
        int arrayIndex;
        int elementIndex;

        Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();
        PriorityQueue<Element> minHeap = new PriorityQueue<>(Comparator.comparingInt(e -> e.value));

        for (int i = 0; i < arrays.length; i++) {
            if (arrays[i].length > 0) {
                minHeap.offer(new Element(arrays[i][0], i, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            Element curr = minHeap.poll();
            result.add(curr.value);

            int nextIndex = curr.elementIndex + 1;
            if (nextIndex < arrays[curr.arrayIndex].length) {
                minHeap.offer(new Element(arrays[curr.arrayIndex][nextIndex], curr.arrayIndex, nextIndex));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] test1 = {{1,4,5}, {1,3,4}, {2,6}};
        int[][] test2 = {{1,2,3}, {4,5,6}, {7,8,9}};
        int[][] test3 = {{1}, {0}};

        System.out.println(mergeKSortedArrays(test1));
        System.out.println(mergeKSortedArrays(test2));
        System.out.println(mergeKSortedArrays(test3));
    }
}
