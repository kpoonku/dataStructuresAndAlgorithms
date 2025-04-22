package data.structures.algorithms.oracle.linkedList;

public class DoublyLinkedListNode<T> {
    public T value;
    public DoublyLinkedListNode<T> prev;
    public DoublyLinkedListNode<T> next;

    public DoublyLinkedListNode(T value, DoublyLinkedListNode<T> prev, DoublyLinkedListNode<T> next) {
        this.value = value;
        this.next = next;
    }
}
