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
        // Add it next to node or insert after the head
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
/*
146. LRU Cache
Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise,
add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation,
evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.

Example 1:
Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4


Constraints:

1 <= capacity <= 3000
0 <= key <= 104
0 <= value <= 105
At most 2 * 105 calls will be made to get and put.
 */
