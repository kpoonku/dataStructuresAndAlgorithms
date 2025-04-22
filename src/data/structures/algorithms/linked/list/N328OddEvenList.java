package data.structures.algorithms.linked.list;

import data.structures.algorithms.linked.list.N2095DeleteMiddleNode.ListNode;

public class N328OddEvenList {
    N2095DeleteMiddleNode nodeObj = new N2095DeleteMiddleNode();

    public static void main(String[] args) {
        N328OddEvenList list = new N328OddEvenList();
        ListNode head = list.nodeObj.createNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        list.nodeObj.printNode(head);
        head = list.oddEvenList(head);
        list.nodeObj.printNode(head);
        head = list.nodeObj.createNode(new int[]{2, 4, 6, 8, 10});
        list.nodeObj.printNode(head);
        head = list.oddEvenList(head);
        list.nodeObj.printNode(head);
        head = list.nodeObj.createNode(new int[]{1, 3, 5, 7, 9});
        list.nodeObj.printNode(head);
        head = list.oddEvenList(head);
        list.nodeObj.printNode(head);
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null) {
            return null;
        }
        boolean isOdd = head.val % 2 == 1;
        ListNode oddHead = null, evenHead = null;
        ListNode current = head, odd = null, even = null;
        while (current != null) {
            if (current.val % 2 == 1) {
                if (oddHead == null) {
                    oddHead = current;
                    odd = current;
                } else {
                    odd.next = current;
                    odd = odd.next;
                }
            } else {
                if (evenHead == null) {
                    evenHead = current;
                    even = current;
                } else {
                    even.next = current;
                    even = even.next;
                }
            }
            current = current.next;
        }
        if (even != null) {
            even.next = null;
            if (!isOdd) {
                even.next = oddHead;
                return evenHead;
            }
        }
        if (odd != null) {
            odd.next = null;
            if (isOdd) {
                odd.next = evenHead;
                return oddHead;
            }
        }
        return evenHead;
    }

    public ListNode getLastNode(ListNode head) {
        ListNode last = null;
        while (head != null) {
            last = head;
            head = head.next;
        }
        return last;
    }
}
