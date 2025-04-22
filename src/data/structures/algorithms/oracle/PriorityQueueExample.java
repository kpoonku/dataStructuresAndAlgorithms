package data.structures.algorithms.oracle;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        Queue<Integer> priorityQueue = new PriorityQueue<>(Comparator.reverseOrder());
        priorityQueue.add(5);
        priorityQueue.add(1);
        priorityQueue.add(10);
        priorityQueue.add(3);

        System.out.println("Priority Queue: " + priorityQueue); // Output: Priority Queue: [1, 3, 10, 5]


        System.out.println("Removed element: " + priorityQueue.remove()); // Output: Removed element: 1
        System.out.println("Priority Queue: " + priorityQueue);
        System.out.println("Removed element: " + priorityQueue.remove()); // Output: Removed element: 3
        System.out.println("Priority Queue: " + priorityQueue);
        System.out.println("Removed element: " + priorityQueue.remove()); // Output: Removed element: 5
        System.out.println("Priority Queue: " + priorityQueue);
        System.out.println("Removed element: " + priorityQueue.remove()); // Output: Removed element: 10
        System.out.println("Priority Queue: " + priorityQueue);
    }
}