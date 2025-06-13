package data.structures.algorithms.hard.leetCode;

import data.structures.algorithms.linked.list.N2095DeleteMiddleNode;
import data.structures.algorithms.linked.list.N2095DeleteMiddleNode.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class N23MergeKSortedList {
    public static void main(String[] args) {
        int[][] list1 = {{1, 2, 3}, {6, 7, 8}, {4, 5}};
        ListNode[] lists = new ListNode[3];
        N2095DeleteMiddleNode n = new N2095DeleteMiddleNode();
        int i = 0;
        while (i < 3) {
            lists[i] = n.createNode(list1[i]);
            i++;
        }
        N23MergeKSortedList solution = new N23MergeKSortedList();
        n.printNode(solution.mergeKLists(lists));
    }

    public ListNode mergeKLists(ListNode[] lists) {
        // Min-heap to keep track of the smallest node
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(n -> n.val));

        // Add the head of each list to the heap
        for (ListNode list : lists) {
            if (list != null) {
                heap.add(list);
            }
        }

        // Dummy node to simplify result list building
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        // While heap is not empty, keep extracting the smallest node
        while (!heap.isEmpty()) {
            ListNode smallest = heap.poll();
            current.next = smallest;
            current = current.next;

            // If the smallest node has a next node, add it to the heap
            if (smallest.next != null) {
                heap.add(smallest.next);
            }
        }

        return dummy.next;
    }
}

/*
https://leetcode.com/problems/merge-k-sorted-lists/description/
You are given an array of k linked-lists lists, each linked-list is sorted
in ascending order.

Merge all the linked-lists into one sorted linked-list and return it.

Example 1:
Input: lists = [[1,4,5],[1,3,4],[2,6]]
Output: [1,1,2,3,4,4,5,6]
Explanation: The linked-lists are:
[
  1->4->5,
  1->3->4,
  2->6
]
merging them into one sorted list:
1->1->2->3->4->4->5->6

Example 2:
Input: lists = []
Output: []

Example 3:
Input: lists = [[]]
Output: []

Constraints:
k == lists.length
0 <= k <= 104
0 <= lists[i].length <= 500
-104 <= lists[i][j] <= 104
lists[i] is sorted in ascending order.
The sum of lists[i].length will not exceed 104.
*/