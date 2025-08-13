import java.util.*;

public class MeetingRoomScheduler {

    public static int minMeetingRooms(int[][] meetings) {
        if (meetings.length == 0) return 0;

        Arrays.sort(meetings, Comparator.comparingInt(a -> a[0])); // 按開始時間排序
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 存結束時間

        for (int[] meeting : meetings) {
            if (!minHeap.isEmpty() && minHeap.peek() <= meeting[0]) {
                minHeap.poll(); // 可重用會議室
            }
            minHeap.offer(meeting[1]);
        }
        return minHeap.size();
    }

    public static int maxMeetingTimeWithLimitedRooms(int[][] meetings, int roomCount) {
        Arrays.sort(meetings, Comparator.comparingInt(a -> a[1])); // 按結束時間排序

        int totalTime = 0;
        // 每個會議室的結束時間
        int[] roomEndTime = new int[roomCount];
        Arrays.fill(roomEndTime, -1);

        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1];
            for (int r = 0; r < roomCount; r++) {
                if (roomEndTime[r] <= start) {
                    totalTime += (end - start);
                    roomEndTime[r] = end;
                    break;
                }
            }
        }
        return totalTime;
    }

    public static void main(String[] args) {
        int[][] test1 = {{0, 30}, {5, 10}, {15, 20}};
        int[][] test2 = {{9, 10}, {4, 9}, {4, 17}};
        int[][] test3 = {{1, 5}, {8, 9}, {8, 9}};
        int[][] test4 = {{1, 4}, {2, 3}, {4, 6}};

        System.out.println(minMeetingRooms(test1)); // 2
        System.out.println(minMeetingRooms(test2)); // 2
        System.out.println(minMeetingRooms(test3)); // 2

        System.out.println(maxMeetingTimeWithLimitedRooms(test4, 1)); // 5
    }
}
