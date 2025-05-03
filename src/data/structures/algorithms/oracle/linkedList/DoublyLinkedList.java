package data.structures.algorithms.oracle.linkedList;

import java.util.Arrays;
import java.util.List;

public class DoublyLinkedList {
    public static DoublyLinkedListNode<Integer> createDoublyLinkedList(List<Integer> integerList) {
        if(integerList == null || integerList.isEmpty()) {
            return null;
        }
        DoublyLinkedListNode<Integer> head = new DoublyLinkedListNode<>
                (integerList.get(0),null,null);
        DoublyLinkedListNode<Integer> current = head;

        for(int i = 1; i < integerList.size(); i++) {
            DoublyLinkedListNode<Integer> newNode = new DoublyLinkedListNode<>
                    (integerList.get(i), null, null);
            newNode.prev = current;
            current.next = newNode;
            current = newNode;
        }
        return head;
    }

    public static void printList(DoublyLinkedListNode<Integer> node) {
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        DoublyLinkedListNode<Integer> head = createDoublyLinkedList(integerList);
        printList(head);
    }
}
