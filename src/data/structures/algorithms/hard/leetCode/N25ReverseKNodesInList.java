package data.structures.algorithms.hard.leetCode;

import data.structures.algorithms.linked.list.N2095DeleteMiddleNode;
import data.structures.algorithms.linked.list.N2095DeleteMiddleNode.ListNode;

public class N25ReverseKNodesInList {
    public static void main(String[] args) {
        N2095DeleteMiddleNode n = new N2095DeleteMiddleNode();
        ListNode listNode = n.createNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        N25ReverseKNodesInList sol = new N25ReverseKNodesInList();
        n.printNode(sol.reverseKGroup(listNode, 3));
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        // Dummy node for easier handling of head swaps
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;
        while (true) {
            ListNode kth = getKthNode(groupPrev, k);
            if (kth == null) break;
            ListNode groupNext = kth.next;
            // Reverse the group
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }
            ListNode temp = groupPrev.next;
            groupPrev.next = kth;
            groupPrev = temp;
        }
        return dummy.next;
    }

    // Helper to find the kth node from the current node
    private ListNode getKthNode(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}
/*
Given the head of a linked list, reverse the nodes of the list k at a time, and return
the modified list.
k is a positive integer and is less than or equal to the length of the linked list.
If the number of nodes is not a multiple of k then left-out nodes, in the end, should
remain as it is.
You may not alter the values in the list's nodes, only nodes themselves may be changed.
Example 1:
Input: head = [1,2,3,4,5], k = 2
Output: [2,1,4,3,5]
Example 2:
Input: head = [1,2,3,4,5], k = 3
Output: [3,2,1,4,5]
Constraints:
The number of nodes in the list is n.
1 <= k <= n <= 5000
0 <= Node.val <= 1000
Follow-up: Can you solve the problem in O(1) extra memory space?
*/
/*
You're working on a classic **linked list** problem:
> **Reverse every `k` nodes in the list** — but **only if there are at least `k`
nodes** in that group.
Let’s go through this with:
1. ✅ **What the problem wants**
2. 🔁 **How the algorithm works**
3. 📦 **Java code**
4. 🧠 **Beginner-level explanation**
---
## ✅ Problem Summary
You're given:
* A singly linked list
* A number `k`
You must:
* Reverse the **nodes** in groups of `k`
* If there are **fewer than `k`** nodes left at the end, leave them as-is
* Only change the links — **not the values**
---
## 🔁 Example
**Input**: `[1,2,3,4,5]`, `k = 2`
**Output**: `[2,1,4,3,5]`
**Input**: `[1,2,3,4,5]`, `k = 3`
**Output**: `[3,2,1,4,5]`
---
## 🧠 Beginner Explanation
### 💡 Idea:
You want to **reverse every `k` nodes**, but only if a full `k` group is available.
### Steps:
1. **Start with a dummy node** → makes it easier to change the head of the list
2. Use `getKthNode()` to check if there are at least `k` nodes left
3. If yes:
   * Reverse that section (just like regular reverse)
   * Hook the reversed part into the rest of the list
4. Repeat until no full `k` group is left
---
### 🔁 How Reversing Works:
For group: `1 -> 2 -> 3` (k=3)
You reverse the links one by one:
```
Before:
1 -> 2 -> 3 -> next
Step 1: 2 -> 1
Step 2: 3 -> 2
Final: 3 -> 2 -> 1
```
---
### 📦 Why Use `dummy` Node?

* Helps simplify edge cases, especially when reversing the **first `k` group**,
which affects the head of the list.
---
## ⏱ Time Complexity
* **O(n)** total — each node is visited once
* **O(1)** space (excluding recursion/stack)
*/
/*
Yes! Let's go through this code step by step — it's used **after reversing a group of `k` nodes** in a linked list. You're connecting the reversed group back into the full list and getting ready for the next group.
---
### 🔍 Code:
```java
ListNode temp = groupPrev.next;
groupPrev.next = kth;
groupPrev = temp;
```
---
## 🎯 Goal
After reversing a group, you need to:
1. Connect the **end of the previous group** to the **start of the reversed group**.
2. Prepare `groupPrev` for the **next group reversal**.
Let’s break it down.
---
## 📦 Setup (Before this code runs)
You just finished reversing a group of nodes like this:
```plaintext
Before reversal:
groupPrev -> 1 -> 2 -> 3 -> groupNext
After reversal (inside the group):
3 -> 2 -> 1 -> groupNext
Now:
- `groupPrev` is still pointing to the node before the group
- `kth` is the new head of the reversed group (3)
- `groupPrev.next` still points to the old head (1), which is now the **tail** of the reversed group
```
---
## 🔹 1. `ListNode temp = groupPrev.next;`
### What it does:
Saves the **old head** of the group (which is now the **tail**) into `temp`.
Why?
* After we update `groupPrev.next`, we’ll **lose access** to the tail of the reversed group.
* We need this later to **connect the next group**.
Example:
```java
groupPrev = dummy
groupPrev.next = 1   // 1 is the old head (now tail)
temp = 1
```
---
## 🔹 2. `groupPrev.next = kth;`
### What it does:
Connects the **previous part** of the list to the **new head** of the reversed group.
```java
groupPrev (dummy) -> 3 (new head of reversed group)
```
This makes sure the reversed part is linked back to the list.
---
## 🔹 3. `groupPrev = temp;`
### What it does:
Moves `groupPrev` forward for the **next group**.
Remember:
* `temp` is the **tail** of the current group (which was the original head)
* After reversing, this node is now **before** the next group
So we move `groupPrev` to that spot so we can repeat the same process.
---
## ✅ Example Visual
Before reversal:
```
dummy -> 1 -> 2 -> 3 -> 4 -> 5
```
After reversing `[1,2,3]` to `[3,2,1]`:
```
dummy
  ↓
groupPrev (still pointing to node before 1)
temp = 1 (tail of reversed group)
kth = 3 (head of reversed group)
Set: groupPrev.next = kth → dummy -> 3
Then: groupPrev = temp → groupPrev now points to 1
Next loop will reverse starting from 4
```
---
## ✅ Summary
| Line                    | What it does                                                     |
| ----------------------- | ---------------------------------------------------------------- |
| `temp = groupPrev.next` | Saves the tail of the reversed group                             |
| `groupPrev.next = kth`  | Connects previous part to new head of reversed group             |
| `groupPrev = temp`      | Prepares for next group (moves pointer to tail of current group) |
*/
/*
Absolutely! Let's break down this code:
```java
while (curr != groupNext) {
    ListNode temp = curr.next;
    curr.next = prev;
    prev = curr;
    curr = temp;
}
```
This code is the **core part of reversing a group of `k` nodes** in a linked list. Let’s explain it step-by-step in
beginner-friendly terms.
---
## 🧠 Problem Context
You are given a section of a linked list and asked to **reverse it**.
Let’s say we are reversing this part:
```
Before:
1 -> 2 -> 3 -> 4
If we're reversing 1 -> 2 -> 3, the group is:
curr = 1
groupNext = 4 (node after the group)
We want to make it:
3 -> 2 -> 1 -> 4
```
---
## 🔄 Code Breakdown
### 🔁 Loop: `while (curr != groupNext)`
We want to reverse nodes from `curr` (start of group) **up to but not including** `groupNext`.
This ensures we reverse exactly `k` nodes.
---
### 🔹 Step-by-Step Inside the Loop
Let's look at each line:
#### 1. `ListNode temp = curr.next;`
* **Save the next node**
* We'll need it after we change the current node's link
#### 2. `curr.next = prev;`
* **Reverse the link!**
* Instead of pointing forward, we make the node point backward
* This is the key step in reversing the list
#### 3. `prev = curr;`
* Move the `prev` pointer one step forward (to where `curr` was)
#### 4. `curr = temp;`
* Move to the next node in the original list (using the saved value)
---
## 🔁 Example Walkthrough
Let’s say:
```
curr = 1
prev = 4 (the node after the group)
```
First pass:
```
temp = 2
1 -> 4    // reversed
prev = 1
curr = 2
```
Second pass:
```
temp = 3
2 -> 1
prev = 2
curr = 3
```
Third pass:
```
temp = 4
3 -> 2
prev = 3
curr = 4 (stop here, because curr == groupNext)
```
Now the group is reversed:
```
3 -> 2 -> 1 -> 4
```
---
## ✅ Summary
This loop:
```java
while (curr != groupNext) {
    ListNode temp = curr.next;
    curr.next = prev;
    prev = curr;
    curr = temp;
}
```
* Reverses a group of `k` nodes by **reversing links one at a time**
* Uses `prev` to build the reversed part
* Stops just before the next group
*/
