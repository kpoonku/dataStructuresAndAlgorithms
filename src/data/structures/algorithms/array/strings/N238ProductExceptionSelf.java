package data.structures.algorithms.array.strings;

import java.util.Arrays;

public class N238ProductExceptionSelf {
    public static int[] productExceptSelf(int[] num) {
        int length = num.length;
        int[] result = new int[length];
        result[0] = 1;
        for (int i = 1; i < length; i++) {
            result[i] = num[i - 1] * result[i - 1];
        }
        int sp = 1;
        for (int i = length - 1; i >= 0; i--) {
            result[i] = sp * result[i];
            sp = sp * num[i];
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(productExceptSelf(new int[]{1, 2, 3, 4}));
    }
}
/*
Given an integer array nums, return an array answer such that answer[i]
 is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a
32-bit integer.

You must write an algorithm that runs in O(n) time and without using
the division operation.

Example 1:
Input: nums = [1,2,3,4]
Output: [24,12,8,6]

Example 2:
Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
 */
/*
This is the **"Product of Array Except Self"** problem — a popular one that teaches **prefix and suffix products**. Let’s walk through the **beginner-friendly explanation** and then see the **Java solution**.

---

## 🧠 Problem Recap

You’re given an array `nums` and you must return a new array `answer`, where:
- `answer[i]` = product of all elements in `nums` **except** `nums[i]`.
- **Do NOT use division**.
- Must run in **O(n)** time.

---

### 🧾 Example

**Input:** `nums = [1, 2, 3, 4]`
**Output:** `[24, 12, 8, 6]`

Why?
- For index 0 → 2×3×4 = 24
- For index 1 → 1×3×4 = 12
- For index 2 → 1×2×4 = 8
- For index 3 → 1×2×3 = 6

---

## ✅ Strategy: Prefix and Suffix Products

We’ll break the problem into **two passes**:

1. **Prefix pass** → product of all elements **before** index `i`
2. **Suffix pass** → product of all elements **after** index `i`

Then, `answer[i] = prefix[i] × suffix[i]`

---

## 🧑‍💻 Java Code (With Explanation)

```java
public class ProductExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] answer = new int[n];

        // Step 1: Fill prefix products
        answer[0] = 1;
        for (int i = 1; i < n; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        // Step 2: Multiply with suffix products
        int suffix = 1;
        for (int i = n - 1; i >= 0; i--) {
            answer[i] = answer[i] * suffix;
            suffix *= nums[i];
        }

        return answer;
    }
}
```

---

## 🔍 How It Works (For nums = [1, 2, 3, 4])

### Step 1 – Prefix:
- answer = [1, 1, 2, 6]

### Step 2 – Suffix:
- Start from end:
  - i = 3 → suffix = 1 → answer[3] = 6×1 = 6
  - i = 2 → suffix = 4 → answer[2] = 2×4 = 8
  - i = 1 → suffix = 12 → answer[1] = 1×12 = 12
  - i = 0 → suffix = 24 → answer[0] = 1×24 = 24
→ Final answer = [24, 12, 8, 6]

---

### 🚫 No Division
We avoid division by calculating prefix and suffix separately.

---

Would you like to see a version of this in Python too, or practice with a custom input?
 */