package data.structures.algorithms.bit.manipulation;

public class N136SingleNumber {
    public static void main(String[] args) {
        N136SingleNumber sol = new N136SingleNumber();
        int result = sol.singleNumber(new int[]{4, 2, 1, 2, 1, 4, 5});
        System.out.println("Result : " + result);
    }

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;  // XOR with each element
        }
        return result;
    }
}
/*
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:
Input: nums = [2,2,1]
Output: 1

Example 2:
Input: nums = [4,1,2,1,2]
Output: 4

Example 3:
Input: nums = [1]
Output: 1

Constraints:
1 <= nums.length <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104
Each element in the array appears twice except for one element which appears only once.
 */
/*
You can solve this problem in **O(n)** time and **O(1)** space using the **bitwise XOR** operation.

---

### ✅ Key Insight:

The XOR (`^`) operation has a few important properties:

1. `a ^ a = 0` (any number XOR itself is 0)
2. `a ^ 0 = a` (any number XOR 0 is itself)
3. XOR is **commutative** and **associative**, meaning the order doesn't matter.

So if you XOR all the numbers together, the duplicate numbers cancel out, and you're left with the single number.

---

### ✅ Java Code:

```java
public class Solution {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num;  // XOR with each element
        }
        return result;
    }
}
```

---

### 🧠 Example Walkthrough:

Input: `[4,1,2,1,2]`

```text
Step-by-step XOR:
0 ^ 4 = 4
4 ^ 1 = 5
5 ^ 2 = 7
7 ^ 1 = 6
6 ^ 2 = 4

Final result: 4
```

---

This solution is:
- ✅ **O(n)** time
- ✅ **O(1)** space
- ✅ Simple and elegant

Let me know if you’d like to see how this works step-by-step for another input!
 */
