package data.structures.algorithms.queue;

import java.util.PriorityQueue;

public class N933RecentCounter {
    PriorityQueue<Integer> pq = null;

    public N933RecentCounter() {
        this.pq = new PriorityQueue<>(3000);
    }

    public int ping(int t) {
        pq.offer(t);
        if (!pq.isEmpty() && pq.peek() < t - 3000) {
            pq.poll();
        }
        return pq.size();
    }

    public static void main(String[] args) {
        N933RecentCounter recentCounter = new N933RecentCounter();
        System.out.println(recentCounter.ping(1));     // requests = [1], range is [-2999,1], return 1
        System.out.println(recentCounter.ping(100));   // requests = [1, 100], range is [-2900,100], return 2
        System.out.println(recentCounter.ping(3001));  // requests = [1, 100, 3001], range is [1,3001], return 3
        System.out.println(recentCounter.ping(3002));  // requests = [1, 100, 3001, 3002], range is [2,3002], return 3

    }
}
