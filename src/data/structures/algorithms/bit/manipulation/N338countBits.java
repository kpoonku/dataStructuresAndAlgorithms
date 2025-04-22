package data.structures.algorithms.bit.manipulation;

import java.util.Arrays;

public class N338countBits {
    public static void main(String[] args) {
        N338countBits sol = new N338countBits();
        int[] result = sol.countBits(5);
        System.out.println("Result : " + Arrays.toString(result));
    }

    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }
        return ans;
    }
}
/*
Given an integer n, return an array ans of length n + 1 such that for each i (0 <= i <= n), ans[i] is the number of 1's in the binary representation of i.

Example 1:

Input: n = 2
Output: [0,1,1]
Explanation:
0 --> 0
1 --> 1
2 --> 10
Example 2:

Input: n = 5
Output: [0,1,1,2,1,2]
Explanation:
0 --> 0
1 --> 1
2 --> 10
3 --> 11
4 --> 100
5 --> 101


Constraints:

0 <= n <= 105


Follow up:

It is very easy to come up with a solution with a runtime of O(n log n). Can you do it in linear time O(n) and possibly in a single pass?
Can you do it without using any built-in function (i.e., like __builtin_popcount in C++)?
 */
/*
Sure! Here's the **Java** version of the same logic, achieving **O(n)** time and avoiding any built-in bit-counting functions:

---

### ✅ Java Code:

```java
public class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i >> 1] + (i & 1);
        }

        return ans;
    }
}
```

---

### 🔍 Explanation:

- `i >> 1` is a right shift (same as integer division by 2)
- `i & 1` checks if the least significant bit is 1 (i.e., whether `i` is odd)
- So for every number, you're using previously calculated values to build the answer efficiently

---

### 🧪 Example:
Input: `n = 5`
Output: `[0, 1, 1, 2, 1, 2]`

Let me know if you want a more detailed explanation or a version with comments!
 */