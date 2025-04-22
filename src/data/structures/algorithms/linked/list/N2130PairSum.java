package data.structures.algorithms.linked.list;

import data.structures.algorithms.linked.list.N2095DeleteMiddleNode.ListNode;

public class N2130PairSum {
    N2095DeleteMiddleNode nodeObj = new N2095DeleteMiddleNode();

    public static void main(String[] args) {
        N2130PairSum pairSum = new N2130PairSum();
        ListNode head = pairSum.nodeObj.createNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        pairSum.pairSum(head);
        head = pairSum.nodeObj.createNode(new int[]{5, 4, 2, 1});
        pairSum.pairSum(head);
        head = pairSum.nodeObj.createNode(new int[]{4, 2, 2, 3});
        pairSum.pairSum(head);
        head = pairSum.nodeObj.createNode(new int[]{1,100000});
        pairSum.pairSum(head);
    }

    public int pairSum(ListNode head) {
        int maxSum = 0;
        //find middle
        ListNode tortoize = head, hare = head.next, prevToMiddle = null;
        while (tortoize != null && hare != null) {
            prevToMiddle = tortoize;
            tortoize = tortoize.next;
            hare = (hare.next == null) ? null : hare.next.next;
        }
        ListNode current = tortoize, prev = null, temp;
        System.out.println("middle : " + tortoize.val);
        nodeObj.printNode(head);
        while (current != null) {
            temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        prevToMiddle.next = prev;
        current = prev;
        nodeObj.printNode(head);
        while (current != null && head != tortoize) {
            maxSum = Math.max(maxSum, current.val + head.val);
            System.out.println("maxSum : " + maxSum + ", current Sum : " + current.val + head.val);
            current = current.next;
            head = head.next;
        }
        return maxSum;
    }
}
/*
In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known
as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.

For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2.
These are the only nodes with twins for n = 4.
The twin sum is defined as the sum of a node and its twin.

Given the head of a linked list with even length, return the maximum twin sum of the linked list.

Example 1:
Input: head = [5,4,2,1]
Output: 6
Explanation:
Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
There are no other nodes with twins in the linked list.
Thus, the maximum twin sum of the linked list is 6.

Example 2:
Input: head = [4,2,2,3]
Output: 7
Explanation:
The nodes with twins present in this linked list are:
- Node 0 is the twin of node 3 having a twin sum of 4 + 3 = 7.
- Node 1 is the twin of node 2 having a twin sum of 2 + 2 = 4.
Thus, the maximum twin sum of the linked list is max(7, 4) = 7.

Example 3:
Input: head = [1,100000]
Output: 100001
Explanation:
There is only one node with a twin in the linked list having twin sum of 1 + 100000 = 100001.

Constraints:
The number of nodes in the list is an even integer in the range [2, 105].
1 <= Node.val <= 105
 */
