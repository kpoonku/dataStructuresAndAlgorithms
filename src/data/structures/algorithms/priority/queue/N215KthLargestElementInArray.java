package data.structures.algorithms.priority.queue;

import java.util.PriorityQueue;

public class N215KthLargestElementInArray {
    public static int kthLargestElementInArray(int[] array, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < array.length; i++) {
            priorityQueue.offer(array[i]);
            if(priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        System.out.println(priorityQueue);
        return priorityQueue.peek();
    }

    public static void main(String[] args) {
        int result = kthLargestElementInArray(new int[]{3, 2, 1, 5, 6, 4}, 2);
        System.out.println("result : " + result);
        result = kthLargestElementInArray(new int[]{3,2,3,1,2,4,5,5,6}, 4);
        System.out.println("result : " + result);
    }
}
/*Example 1:

Input: nums = [3,2,1,5,6,4], k = 2
Output: 5
Example 2:

Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
Output: 4
* */
