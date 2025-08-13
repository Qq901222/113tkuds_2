import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueWithHeap {
    static class Task {
        String name;
        int priority;
        long timestamp;

        Task(String name, int priority, long timestamp) {
            this.name = name;
            this.priority = priority;
            this.timestamp = timestamp;
        }

        @Override
        public String toString() {
            return name + "(優先級:" + priority + ")";
        }
    }

    private PriorityQueue<Task> queue;
    private long counter = 0;

    public PriorityQueueWithHeap() {
        queue = new PriorityQueue<>(Comparator
                .comparingInt((Task t) -> -t.priority)
                .thenComparingLong(t -> t.timestamp));
    }

    public void addTask(String name, int priority) {
        queue.offer(new Task(name, priority, counter++));
    }

    public Task executeNext() {
        return queue.poll();
    }

    public Task peek() {
        return queue.peek();
    }

    public void changePriority(String name, int newPriority) {
        ArrayList<Task> tasks = new ArrayList<>(queue);
        queue.clear();
        for (Task t : tasks) {
            if (t.name.equals(name)) {
                t.priority = newPriority;
            }
            queue.offer(t);
        }
    }

    public static void main(String[] args) {
        PriorityQueueWithHeap pq = new PriorityQueueWithHeap();
        pq.addTask("備份", 1);
        pq.addTask("緊急修復", 5);
        pq.addTask("更新", 3);

        System.out.println("下一個任務: " + pq.peek());
        while (pq.peek() != null) {
            System.out.println("執行: " + pq.executeNext());
        }
    }
}
