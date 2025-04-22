package data.structures.algorithms.oracle;

import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();

        queue.add("First");
        queue.add("Second");
        queue.add("Third");

        System.out.println("Queue: " + queue); // Output: Queue: [First, Second, Third]

        String firstElement = queue.remove();
        System.out.println("Removed element: " + firstElement); // Output: Removed element: First
        System.out.println("Queue after removal: " + queue); // Output: Queue after removal: [Second, Third]

        String peekedElement = queue.peek();
        System.out.println("Peeked element: " + peekedElement); // Output: Peeked element: Second

        String polledElement = queue.poll();
        System.out.println("Polled element: " + polledElement);
        System.out.println("Queue after removal: " + queue); // Output: Queue after removal: [Third]
    }
}
