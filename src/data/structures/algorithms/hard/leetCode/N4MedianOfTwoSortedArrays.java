package data.structures.algorithms.hard.leetCode;

public class N4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        N4MedianOfTwoSortedArrays solution = new N4MedianOfTwoSortedArrays();

        int[] nums1 = {1, 3};
        int[] nums2 = {};
        System.out.println("Median is: " + solution.findMedianSortedArrays(nums1, nums2)); // Output: 2.0

        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("Median is: " + solution.findMedianSortedArrays(nums3, nums4)); // Output: 2.5
    }
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        // Smallest is the first argument
        if (nums2.length < nums1.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int imin = 0, imax = m, halfLen = (m + n + 1) / 2;

        while (imin <= imax) {
            int i = (imin + imax) / 2;
            int j = halfLen - i;

            if (i < m && nums2[j - 1] > nums1[i]) {
                imin = i + 1;
            } else if (i > 0 && nums1[i - 1] > nums2[j]) {
                imax = i - 1;
            } else {
                int maxOfLeft;
                if (i == 0) {
                    maxOfLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxOfLeft = nums1[i - 1];
                } else {
                    maxOfLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }

                if ((m + n) % 2 == 1) {
                    return maxOfLeft;
                }

                int minOfRight;
                if (i == m) {
                    minOfRight = nums2[j];
                } else if (j == n) {
                    minOfRight = nums1[i];
                } else {
                    minOfRight = Math.min(nums1[i], nums2[j]);
                }
                return (maxOfLeft + minOfRight) / 2.0;
            }
        }
        throw new IllegalArgumentException("Input arrays are not valid.");
    }
}

/*
Given two sorted arrays nums1 and nums2 of size m and n respectively,
return the median of the two sorted arrays.

The overall run time complexity should be O(log (m+n)).

Example 1:

Input: nums1 = [1,3], nums2 = [2]
Output: 2.00000
Explanation: merged array = [1,2,3] and median is 2.
Example 2:

Input: nums1 = [1,2], nums2 = [3,4]
Output: 2.50000
Explanation: merged array = [1,2,3,4] and median is (2 + 3) / 2 = 2.5.

Constraints:

nums1.length == m
nums2.length == n
0 <= m <= 1000
0 <= n <= 1000
1 <= m + n <= 2000
-106 <= nums1[i], nums2[i] <= 106
*/

/*
Absolutely! Let me walk you through the **step-by-step beginner-level explanation** of
how this Java solution works to find the **median of two sorted arrays** in
**O(log(min(m, n)))** time.

---

## 🧠 First, What's the Median?

* **Median** is the "middle" value in a sorted list.

  * If the total number of elements is **odd**, median is the **middle element**.
  * If the total number is **even**, median is the **average of the two middle elements**.

---

## 🎯 Goal

Given two **sorted arrays** `nums1` and `nums2`, find the **median** of the **combined
sorted array**, without actually merging them (to save time).

---

## 🧩 Example

Let’s say:

```java
nums1 = [1, 3]
nums2 = [2]
```

Combined sorted array would be `[1, 2, 3]`, so the median is `2`.

---

## ✅ Key Idea (Divide and Conquer)

We want to **partition** both arrays such that:

* The **left half** contains the same number of elements (or 1 more) as the **right half**.
* All elements in the **left half** are ≤ all elements in the **right half**.

We use **binary search** on the **shorter array** to find that perfect partition.

---

## 👣 Step-by-Step Explanation

### Step 1: Make Sure `nums1` is Smaller

```java
if (nums1.length > nums2.length) {
    return findMedianSortedArrays(nums2, nums1);
}
```

This ensures we always do binary search on the smaller array, which is faster and avoids
out-of-bound errors.

---

### Step 2: Set Up Binary Search

```java
int m = nums1.length;
int n = nums2.length;
int imin = 0, imax = m;
int halfLen = (m + n + 1) / 2;
```

We’ll search from `0` to `m` (length of `nums1`).
We also calculate `halfLen`, the size of the left partition we want.

---

### Step 3: Binary Search Loop

```java
while (imin <= imax) {
    int i = (imin + imax) / 2;
    int j = halfLen - i;
```

We're choosing `i` as a partition point in `nums1`.
Then `j` will be the partition point in `nums2`.

---

### Step 4: Check If Partition is Correct

We want:

```text
nums1[i - 1] <= nums2[j] and nums2[j - 1] <= nums1[i]
```

If not, we adjust `i`:

```java
if (i < m && nums2[j - 1] > nums1[i]) {
    imin = i + 1; // i is too small
} else if (i > 0 && nums1[i - 1] > nums2[j]) {
    imax = i - 1; // i is too big
} else {
    // i is perfect
}
```

---

### Step 5: Get Max of Left Part

```java
int maxOfLeft;
if (i == 0) {
    maxOfLeft = nums2[j - 1];
} else if (j == 0) {
    maxOfLeft = nums1[i - 1];
} else {
    maxOfLeft = Math.max(nums1[i - 1], nums2[j - 1]);
}
```

This is the largest number in the left half.

* If the total number of elements is **odd**, this is the **median**.

---

### Step 6: If Total is Even, Get Min of Right

```java
if ((m + n) % 2 == 1) {
    return maxOfLeft;
}

int minOfRight;
if (i == m) {
    minOfRight = nums2[j];
} else if (j == n) {
    minOfRight = nums1[i];
} else {
    minOfRight = Math.min(nums1[i], nums2[j]);
}

return (maxOfLeft + minOfRight) / 2.0;
```

If the total number is even, take the **average** of `maxOfLeft` and `minOfRight`.
---
## 🔁 How Binary Search Helps
Instead of merging the arrays (which takes O(m+n) time), we **search for the correct partition**
in just **O(log(min(m, n)))** time using binary search.
---
## 📌 Summary

* **Partition** the arrays so that left and right halves are balanced.
* Use **binary search** to find the correct partition quickly.
* Use the partition to **find the median** without full merging.

Would you like a visual diagram of how the partition works or a dry run of a specific example?
*/
