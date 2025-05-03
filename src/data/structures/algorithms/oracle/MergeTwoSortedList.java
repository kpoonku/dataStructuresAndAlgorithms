package data.structures.algorithms.oracle;

import java.util.Arrays;
import java.util.List;

class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }
}

public class MergeTwoSortedList {
    public static ListNode mergeTwoSortedList(ListNode list1, ListNode list2) {
        ListNode head = new ListNode();
        ListNode current = head;

        while(list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        current.next = (list1 == null) ? list2: list1;
        return head.next;
    }

    public static void printListNode(ListNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            printListNode(node.next);
        } else {
            System.out.println();
        }
    }
    public static ListNode createList(List<Integer> list) {
        ListNode dummyNode = new ListNode();
        ListNode current = dummyNode;
        for(int val: list) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode node1 = createList(Arrays.asList(1));
        printListNode(node1);
        ListNode node2 = createList(Arrays.asList(5,6,7,8,9,10));
        printListNode(node2);
        ListNode node3 = mergeTwoSortedList(node1, node2);
        printListNode(node3);
    }
}
/*

You are given the heads of two sorted linked lists list1 and list2.

Merge the two lists into one sorted list. The list should be made by splicing together
the nodes of the first two lists.

Return the head of the merged linked list.



Example 1:


Input: list1 = [1,2,4], list2 = [1,3,4]
Output: [1,1,2,3,4,4]
Example 2:

Input: list1 = [], list2 = []
Output: []
Example 3:

Input: list1 = [], list2 = [0]
Output: [0]


Constraints:

The number of nodes in both lists is in the range [0, 50].
-100 <= Node.val <= 100
Both list1 and list2 are sorted in non-decreasing order.
 */
