package data.structures.algorithms.priority.queue;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class N2336SmallestInfinitySet {
    PriorityQueue<Integer> minHeap;
    Set<Integer> set;
    private int current;

    public N2336SmallestInfinitySet() {
        current = 1;
        minHeap = new PriorityQueue<>();
        set = new HashSet<>();
    }

    public static void main(String[] args) {
        N2336SmallestInfinitySet sol = new N2336SmallestInfinitySet();
        sol.addBack(2);
        System.out.println(sol.minHeap);
        System.out.println("1 : " + sol.popSmallest());
        System.out.println("2 : " + sol.popSmallest());
        System.out.println("3 : " + sol.popSmallest());
        sol.addBack(1);
        System.out.println("4 : " + sol.popSmallest());
        System.out.println("5 : " + sol.popSmallest());
        System.out.println("6 : " + sol.popSmallest());
        System.out.println("7 : " + sol.popSmallest());
        System.out.println(sol.minHeap);
    }

    public int popSmallest() {
        if (!minHeap.isEmpty()) {
            int num = minHeap.poll();
            set.remove(num);
            return num;
        }
        return current++;
    }

    public void addBack(int num) {
        if (num < current && !set.contains(num)) {
            minHeap.add(num);
            set.add(num);
        }
    }
}
