package data.structures.algorithms.hard.leetCode;

public class N41FindMissingPositive {
    public int firstMissingPositive(int[] nums) {

        int n = nums.length;

        // Step 1: Put each number in its right place
        for (int i = 0; i < n; i++) {
            // Keep swapping until the number is in the correct position
            while (nums[i] >= 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // Swap nums[i] with nums[nums[i] - 1]
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }

        // Step 2: Find the first missing number
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) return i + 1;
        }

        // If all numbers from 1 to n are present
        return n + 1;
    }
}
/*
Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.

You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.



Example 1:

Input: nums = [1,2,0]
Output: 3
Explanation: The numbers in the range [1,2] are all in the array.
Example 2:

Input: nums = [3,4,-1,1]
Output: 2
Explanation: 1 is in the array but 2 is missing.
Example 3:

Input: nums = [7,8,9,11,12]
Output: 1
Explanation: The smallest positive integer 1 is missing.


Constraints:

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
 */
/*
To solve this problem efficiently, we need to find the **smallest missing positive integer** from an unsorted array.

### 🧠 Requirements:

* Time complexity: **O(n)**
* Extra space: **O(1)** (you can **modify the input array**, but you can't use extra space like hash sets or new arrays)

---

## ✅ Key Idea: Place Numbers in Their Correct Index

We will use **index as a hash key**:

* The smallest positive integer is **1**, so ideally, `nums[0] == 1`, `nums[1] == 2`, and so on.
* We **place each number `x` (if it's between 1 and n)** in index `x-1`.

---

## ✨ Steps:

1. **Rearrange** numbers so that each number `x` (1 ≤ x ≤ n) is at `nums[x - 1]`.
2. **Scan the array**:

   * If `nums[i] != i + 1`, then `i + 1` is the missing number.
3. If all are correct, return `n + 1`.

---

## ✅ Java Code (Efficient & Beginner-Friendly)

```java
public class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // Step 1: Put each number in its right place
        for (int i = 0; i < n; i++) {
            // Keep swapping until the number is in the correct position
            while (nums[i] >= 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                // Swap nums[i] with nums[nums[i] - 1]
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }

        // Step 2: Find the first missing number
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1)
                return i + 1;
        }

        // If all numbers from 1 to n are present
        return n + 1;
    }
}
```

---

## 🧪 Example Walkthrough

### Input:

```java
nums = [3, 4, -1, 1]
```

### Step-by-step swap:

* Swap 3 into position 2 → `[ -1, 4, 3, 1 ]`
* Swap 1 into position 0 → `[ 1, 4, 3, -1 ]`
* All other numbers are in the wrong range or already in place.

### Final state:

```java
[1, 4, 3, -1]
```

We scan:

* `nums[0] == 1`
* `nums[1] != 2` → So, return **2**

---

## 🕒 Time & Space Complexity

| Aspect      | Complexity                                   |
| ----------- | -------------------------------------------- |
| Time        | **O(n)** (each number is moved at most once) |
| Extra Space | **O(1)** (in-place, no extra arrays)         |

---

## 🧠 Summary

* Rearrange numbers in-place.
* Use indices to simulate a hash table.
* Scan to find the first mismatch → that's the missing number.

Let me know if you'd like a **diagram** or a **Python version**!
 */