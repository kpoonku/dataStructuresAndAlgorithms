package data.structures.algorithms.linked.list;

import data.structures.algorithms.linked.list.N2095DeleteMiddleNode.ListNode;

public class N328_OddEvenList {
    // Method to print the list for testing
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next != null ? " -> " : ""));
            head = head.next;
        }
        System.out.println();
    }
    // Example usage
    public static void main(String[] args) {
        // Create a sample linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        // Process the list
        N328_OddEvenList obj = new N328_OddEvenList();
        ListNode result = obj.oddEvenList(head);
        // Print the result: should be 1 -> 3 -> 5 -> 2 -> 4
        printList(result);
    }
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) {
            // If the list has 0 or 1 node, no changes needed
            return head;
        }
        // Create pointers for odd and even
        ListNode odd = head;             // First node (odd index)
        ListNode even = head.next;       // Second node (even index)
        ListNode evenHead = even;        // Save the start of even list
        // Loop through the list, rearranging links
        while (even != null && even.next != null) {
            odd.next = even.next;        // odd points to next odd
            odd = odd.next;              // move odd forward
            even.next = odd.next;        // even points to next even
            even = even.next;            // move even forward
        }
        // At the end, connect odd list to even list
        odd.next = evenHead;
        return head;
    }
}
/*
328. Odd Even Linked List
Given the head of a singly linked list, group all the nodes with odd indices together
followed by the nodes with even indices, and return the reordered list.
The first node is considered odd, and the second node is even, and so on.
Note that the relative order inside both the even and odd groups should remain as
it was in the input.
You must solve the problem in O(1) extra space complexity and O(n) time complexity.

Example 1:
Input: head = [1,2,3,4,5]
Output: [1,3,5,2,4]

Example 2:
Input: head = [2,1,3,5,6,4,7]
Output: [2,3,6,7,1,5,4]

Constraints:
The number of nodes in the linked list is in the range [0, 104].
-106 <= Node.val <= 106
*/
/*
Let’s explain **328. Odd Even Linked List** in a very **simple, beginner-level** way
— like you're just starting to learn data structures.
---
## 🧠 First, What Is a Linked List?
A **linked list** is a chain of nodes.
Each **node** holds:
* A **value** (like 1, 2, 3...)
* A **link** (or pointer) to the **next node**
So a list like this:
`[1, 2, 3, 4, 5]`
Looks like:
```
1 → 2 → 3 → 4 → 5 → null
```
---
## ✅ What Is This Problem Asking?
You are given a linked list.
You need to **rearrange it** so that:
* All **odd-indexed** nodes come **first**
* Followed by all the **even-indexed** nodes
👉 Remember:
* The **first node** is considered **odd** (index 1)
* The **second node** is considered **even** (index 2)
* Then odd, even, odd, even, etc...
---
### 🎯 Example:
Input:
```
[1, 2, 3, 4, 5]
```
Indexing the nodes:
```
Position:   1   2   3   4   5
Node val:   1 → 2 → 3 → 4 → 5
```
Odd nodes: `1 → 3 → 5`
Even nodes: `2 → 4`
Final result:
```
1 → 3 → 5 → 2 → 4
```
---
## 🚶‍♂️ How Do We Solve This?
You **walk through** the list and **split it** into:
* An "odd" list
* An "even" list
Then, you **connect the odd list to the even list**.
---
## 🗂️ Step-by-Step Summary:
1. Start with two pointers:
   * One for **odd** nodes
   * One for **even** nodes
2. Keep track of the **start of even list** (you'll need it later to attach to end of odd list)
3. Loop through the list:
   * Connect odd node to the next **odd**
   * Connect even node to the next **even**
4. At the end, attach the even list **after** the odd list
5. Return the **head** (first node) of the rearranged list
---
## 🔄 Real-Life Analogy:
Think of students standing in a line by roll number:
* You want to rearrange them so that all students with **odd roll numbers** stand first,
* Then all students with **even roll numbers**.
You **don’t mix their order** within the odd or even groups — just group them.
*/
