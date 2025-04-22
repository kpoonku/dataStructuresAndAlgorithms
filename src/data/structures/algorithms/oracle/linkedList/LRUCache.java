package data.structures.algorithms.oracle.linkedList;

import java.util.HashMap;
import java.util.Map;

class DoublyLinkedNode {
    int key;
    int value;
    DoublyLinkedNode prev;
    DoublyLinkedNode next;

    public DoublyLinkedNode(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {
    private final Map<Integer, DoublyLinkedNode> cacheMap;
    private final DoublyLinkedNode head, tail;
    private final int capacity;
    private int size;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.size = 0;
        head = new DoublyLinkedNode(0, 0);
        tail = new DoublyLinkedNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cacheMap.containsKey(key)) {
            return -1;
        }
        DoublyLinkedNode node = cacheMap.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (cacheMap.containsKey(key)) {
            DoublyLinkedNode node = cacheMap.get(key);
            node.value = value;
            moveToHead(node);
        } else {
            DoublyLinkedNode newNode = new DoublyLinkedNode(key, value);
            cacheMap.put(key, newNode);
            addToHead(newNode);
            size++;
            if (size > capacity) {
                DoublyLinkedNode tail = removeTail();
                cacheMap.remove(tail.key);
                size--;
            }
        }
    }

    private void moveToHead(DoublyLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(DoublyLinkedNode node) {
        node.prev.next = node.next;
        if (node.next != null)
            node.next.prev = node.prev;
    }

    private void addToHead(DoublyLinkedNode node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
    }

    private DoublyLinkedNode removeTail() {
        DoublyLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}
