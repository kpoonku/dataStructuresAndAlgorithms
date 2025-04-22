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

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
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
