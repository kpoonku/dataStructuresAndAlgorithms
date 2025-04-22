package data.structures.algorithms.oracle.linkedList;

import java.util.Arrays;
import java.util.List;

public class SinglyLinkedList {

    public static boolean hasCycle(SinglyLinkedListNode<Integer> node) {
        SinglyLinkedListNode<Integer> tortoize = node;
        SinglyLinkedListNode<Integer> hare = nextNode(nextNode(tortoize));
        while(tortoize != hare && hare != null) {
            tortoize = nextNode(tortoize);
            hare = nextNode(nextNode(hare));
        }
        return hare != null;
    }

    public static SinglyLinkedListNode<Integer> nextNode(SinglyLinkedListNode<Integer> node) {
        return (node != null && node.next != null) ? node.next : null;
    }

    public static SinglyLinkedListNode<Integer> createSinglyLinkedList(List<Integer> listValues) {
        if (listValues == null || listValues.isEmpty()) {
            return null;
        }

        SinglyLinkedListNode<Integer> head = new SinglyLinkedListNode<>(listValues.get(0), null);
        SinglyLinkedListNode<Integer> current = head;
        for (int i = 1; i < listValues.size(); i++) {
            current.next = new SinglyLinkedListNode<>(listValues.get(i), null);
            current = current.next;
        }
        return head;
    }

    public static void printList(SinglyLinkedListNode<Integer> singlyLinkedListNode) {
        while (singlyLinkedListNode != null) {
            System.out.print(singlyLinkedListNode.value + " ");
            singlyLinkedListNode = singlyLinkedListNode.next;
        }
    }

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        SinglyLinkedListNode<Integer> head = createSinglyLinkedList(integerList);
        printList(head);
        System.out.println("Has cycle : "  + hasCycle(head));
    }
}
