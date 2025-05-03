package data.structures.algorithms.linked.list;

public class N2095DeleteMiddleNode {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        N2095DeleteMiddleNode middleNode = new N2095DeleteMiddleNode();
        ListNode head = middleNode.createNode(nums);
        int size = middleNode.size(head);
        System.out.println("Size : " + size);
        System.out.println("2nd Node : " + middleNode.getNthNode(head, 1).val);
        int middleIndex = (size / 2);
        ListNode node = middleNode.getNthNode(head, middleIndex);
        System.out.println("Middle Node : " + node.val);
        middleNode.deleteNthNode(head, middleIndex);
        nums = new int[]{1, 3, 4, 7, 1, 2, 6};
        ListNode head1 = middleNode.createNode(nums);
        middleIndex = middleNode.size(head1) / 2;
        middleNode.deleteNthNode(head1, middleIndex);
        nums = new int[]{1, 2, 3, 4};
        head1 = middleNode.createNode(nums);
        middleIndex = middleNode.size(head1) / 2;
        middleNode.deleteNthNode(head1, middleIndex);
        nums = new int[]{2, 1};
        head1 = middleNode.createNode(nums);
        middleIndex = middleNode.size(head1) / 2;
        middleNode.deleteNthNode(head1, middleIndex);
        ListNode middleNode1 = deleteMiddle(head);
        printList(head);
    }

    // Helper function to print linked list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " -> " : ""));
            head = head.next;
        }
        System.out.println();
    }

    public ListNode createNode(int[] nums) {
        ListNode head = new ListNode(nums[0]);
        ListNode node = head;
        for (int i = 1; i < nums.length; i++) {
            node.next = new ListNode(nums[i]);
            node = node.next;
        }
        return head;
    }

    public int size(ListNode head) {
        int count = 0;
        while (head != null) {
            head = head.next;
            count++;
        }
        return count;
    }

    public ListNode getNthNode(ListNode node, int n) {
        while (node != null & n > 0) {
            n--;
            node = node.next;
        }
        return node;
    }

    public ListNode deleteNthNode(ListNode head, int number) {
        ListNode deleted = null;
        if (head == null) {
            return null;
        }
        printNode(head);
        ListNode pNode = getNthNode(head, number - 1);
        if (pNode == null) {
            head = head.next;
        } else {
            deleted = pNode.next;
            if (deleted != null) {
                pNode.next = deleted.next;
                deleted.next = null;
            }
        }
        printNode(head);
        return pNode;
    }

    public void printNode(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static ListNode deleteMiddle(ListNode head) {
        // If there's only one node, return null
        if (head == null || head.next == null) {
            return null;
        }

        // Use two pointers: slow and fast
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        // Move fast by 2 and slow by 1 to find middle
        while (fast != null && fast.next != null) {
            prev = slow;            // Keep track of the node before slow
            slow = slow.next;       // Move slow by 1
            fast = fast.next.next;  // Move fast by 2
        }

        // Delete the middle node
        prev.next = slow.next;

        return head;
    }

    public static class ListNode {
        public int val;
        public ListNode next;

        ListNode(int val) {
            this.val = val;
        }
    }
}
/*
You are given the head of a linked list. Delete the middle node, and return the head of
the modified linked list.

The middle node of a linked list of size n is the ⌊n / 2⌋th node from the start using 0-based
indexing, where ⌊x⌋ denotes the largest integer less than or equal to x.

For n = 1, 2, 3, 4, and 5, the middle nodes are 0, 1, 1, 2, and 2, respectively.

Example 1:
Input: head = [1,3,4,7,1,2,6]
Output: [1,3,4,1,2,6]
Explanation:
The above figure represents the given linked list. The indices of the nodes are written below.
Since n = 7, node 3 with value 7 is the middle node, which is marked in red.
We return the new list after removing this node.

Example 2:
Input: head = [1,2,3,4]
Output: [1,2,4]
Explanation:
The above figure represents the given linked list.
For n = 4, node 2 with value 3 is the middle node, which is marked in red.

Example 3:
Input: head = [2,1]
Output: [2]
Explanation:
The above figure represents the given linked list.
For n = 2, node 1 with value 1 is the middle node, which is marked in red.
Node 0 with value 2 is the only node remaining after removing node 1.
*/
/*
Let’s solve this problem in **Java** and also understand it step by step in a **simple, beginner-friendly way**.

---

## 🧠 Problem Summary (In Simple Words)

You're given a **linked list** like this:

```
[1 → 3 → 4 → 7 → 1 → 2 → 6]
```

Your task is to:

* **Find the middle node** (the ⌊n/2⌋-th node, using 0-based index)
* **Delete it**
* Return the updated linked list

> Example: In the list above, there are 7 nodes. Middle index = ⌊7/2⌋ = 3
> So, we delete the **4th node (index 3)** → which has the value **7**

---

## 📌 Special Case:

* If the list has **only one node**, then deleting the middle node means the list becomes **empty (null)**.

---

## ✅ Java Code (Beginner-Friendly)

```java
// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class DeleteMiddleNode {

    public ListNode deleteMiddle(ListNode head) {
        // If there's only one node, return null
        if (head == null || head.next == null) {
            return null;
        }

        // Use two pointers: slow and fast
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        // Move fast by 2 and slow by 1 to find middle
        while (fast != null && fast.next != null) {
            prev = slow;            // Keep track of the node before slow
            slow = slow.next;       // Move slow by 1
            fast = fast.next.next;  // Move fast by 2
        }

        // Delete the middle node
        prev.next = slow.next;

        return head;
    }

    // Helper function to print linked list
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " -> " : ""));
            head = head.next;
        }
        System.out.println();
    }

    // Testing it
    public static void main(String[] args) {
        DeleteMiddleNode obj = new DeleteMiddleNode();

        // Example: [1, 3, 4, 7, 1, 2, 6]
        ListNode head = new ListNode(1);
        head.next = new ListNode(3);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(7);
        head.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next = new ListNode(6);

        System.out.print("Original: ");
        printList(head);

        head = obj.deleteMiddle(head);

        System.out.print("After deleting middle: ");
        printList(head);
    }
}
```
---
## 🔄 How It Works:
* `slow` and `fast` pointers help us **find the middle node**
* `prev` keeps track of the **node before the middle**
* We then do: `prev.next = slow.next` → which **skips (deletes)** the middle node
---
 */