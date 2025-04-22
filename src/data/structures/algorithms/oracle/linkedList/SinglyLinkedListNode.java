package data.structures.algorithms.oracle.linkedList;

public class SinglyLinkedListNode<T> {
    public T value;
    public SinglyLinkedListNode<T> next;

    public SinglyLinkedListNode(T value, SinglyLinkedListNode<T> next) {
        this.value = value;
        this.next = next;
    }
}
