package data.structures.algorithms;

import data.structures.algorithms.linked.list.N2095DeleteMiddleNode;
import data.structures.algorithms.linked.list.N2095DeleteMiddleNode.ListNode;

public class N206ReverseLinkedList {
    N2095DeleteMiddleNode nodeObj = new N2095DeleteMiddleNode();

    public static void main(String[] args) {
        N206ReverseLinkedList list = new N206ReverseLinkedList();
        ListNode head = list.nodeObj.createNode(new int[]{1, 2, 3, 4, 5});
        list.nodeObj.printNode(head);
        head = list.reverseList(head);
        list.nodeObj.printNode(head);
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        ListNode next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;

        }
        return prev;
    }
}
/*
Given the head of a singly linked list, reverse the list,
and return the reversed list.

Example 1:
Input: head = [1,2,3,4,5]
Output: [5,4,3,2,1]

Example 2:
Input: head = [1,2]
Output: [2,1]

Example 3:
Input: head = []
Output: []

 */
