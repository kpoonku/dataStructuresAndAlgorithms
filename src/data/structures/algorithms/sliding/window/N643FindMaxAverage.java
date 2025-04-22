package data.structures.algorithms.sliding.window;

public class N643FindMaxAverage {
    public static double findMaxAverage(int[] nums, int k) {
        double currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum = +nums[i];
        }
        double maxAverage = currentSum / k;
        for (int i = k; i < nums.length; i++) {
            currentSum += nums[i] - nums[i - k];
            maxAverage = Math.max(maxAverage, currentSum / k);
        }
        return maxAverage;
    }

    public static void main(String[] args) {
        System.out.println("Max Average : " + findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
    }
}
/*
643. Maximum Average Subarray I
You are given an integer array nums consisting of n elements,
and an integer k.
Find a contiguous sub-array whose length is equal to k that has
the maximum average value and return this value. Any answer with
a calculation error less than 10-5 will be accepted.
Example 1:
Input: nums = [1,12,-5,-6,50,3], k = 4
Output: 12.75000
Explanation: Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
Example 2:
Input: nums = [5], k = 1
Output: 5.00000
 */
/*
This is a classic **sliding window** problem — and a great one for beginners to understand optimization tricks!

---

## 🧠 Problem Summary:
You are given:
- An array `nums`
- A number `k`

You need to find the **maximum average** of any **subarray of length k** (i.e., `k` consecutive numbers).

---

### 🔍 Key Insight:
Instead of checking **every possible subarray** (which would be slow), we can use a **sliding window of size k** to keep track of the sum efficiently.

---

## ✅ Sliding Window Idea:

1. **Start by calculating the sum of the first `k` elements.**
2. Then **slide the window one step at a time**:
   - Subtract the element that’s going out of the window
   - Add the new element coming in
3. Keep track of the **maximum sum** as you go.
4. At the end, divide the **maximum sum by k** to get the **maximum average**.

---

## ✅ Java Code:

```java
public class MaxAverageSubarray {

    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;

        // Step 1: Calculate initial sum of first k elements
        double currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }

        double maxSum = currentSum;

        // Step 2: Slide the window
        for (int i = k; i < n; i++) {
            currentSum = currentSum - nums[i - k] + nums[i];
            maxSum = Math.max(maxSum, currentSum);
        }

        // Step 3: Return the maximum average
        return maxSum / k;
    }

    public static void main(String[] args) {
        MaxAverageSubarray mas = new MaxAverageSubarray();

        int[] nums1 = {1, 12, -5, -6, 50, 3};
        int k1 = 4;
        System.out.println("Max Average: " + mas.findMaxAverage(nums1, k1));  // Output: 12.75

        int[] nums2 = {5};
        int k2 = 1;
        System.out.println("Max Average: " + mas.findMaxAverage(nums2, k2));  // Output: 5.0
    }
}
```

---

### 🧪 Example 1:
```java
nums = [1,12,-5,-6,50,3], k = 4
Subarrays:
[1,12,-5,-6] → sum = 2
[12,-5,-6,50] → sum = 51 ✅
[-5,-6,50,3] → sum = 42

Max sum = 51 → average = 51 / 4 = 12.75
```

---

### ⏱️ Time Complexity:
- **O(n)** — we only loop through the array once
- **O(1)** — space

---

Let me know if you want this in Python too or explained visually with each sliding step!
 */