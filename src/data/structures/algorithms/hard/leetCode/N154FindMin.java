package data.structures.algorithms.hard.leetCode;

public class N154FindMin {
    // For quick testing
    public static void main(String[] args) {
        N154FindMin solution = new N154FindMin();
        System.out.println(solution.findMin(new int[]{2, 2, 2, 0, 1})); // Output: 0
        System.out.println(solution.findMin(new int[]{1, 3, 5}));       // Output: 1
    }

    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[right]) right = mid + 1;
            else if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right--; // nums[mid] == nums[right], reduce the search space safely
            }
        }

        return nums[left];
    }
}
/*
Suppose an array of length n sorted in ascending order is
rotated between 1 and n times.
For example, the array nums = [0,1,4,4,5,6,7] might become:

[4,5,6,7,0,1,4] if it was rotated 4 times.
[0,1,4,4,5,6,7] if it was rotated 7 times.
Notice that rotating an array [a[0], a[1], a[2], ..., a[n-1]] 1 time results in the array [a[n-1], a[0], a[1], a[2], ..., a[n-2]].

Given the sorted rotated array nums that may contain duplicates,
return the minimum element of this array.

You must decrease the overall operation steps as much as possible.

Example 1:
Input: nums = [1,3,5]
Output: 1
Example 2:
Input: nums = [2,2,2,0,1]
Output: 0

Constraints:
n == nums.length
1 <= n <= 5000
-5000 <= nums[i] <= 5000
nums is sorted and rotated between 1 and n times.

Follow up: This problem is similar to Find Minimum in Rotated Sorted Array,
but nums may contain duplicates. Would this affect the runtime complexity? How and why?


 */
/*
### 🔍 Problem Summary:

You’re given a **rotated sorted array** that **may contain duplicates**. Y
ou need to find the **minimum element** using the fewest possible operations.

---

### ✅ Key Insight:

This is a variation of the **"Find Minimum in Rotated Sorted Array"** problem, with the
added complexity of **duplicate elements**.

* In the version **without duplicates**, we can use **binary search** in `O(log n)` time.
* But with **duplicates**, the worst case can degrade to **O(n)** — because we may not be
able to determine the sorted side.

---

### ✅ Optimal Approach (Modified Binary Search):

```java
public class FindMinInRotatedSortedArrayWithDuplicates {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] > nums[right]) {
                // Min must be to the right of mid
                left = mid + 1;
            } else if (nums[mid] < nums[right]) {
                // Min is at mid or to the left
                right = mid;
            } else {
                // nums[mid] == nums[right] -> cannot decide, reduce right
                right--;
            }
        }

        return nums[left]; // or nums[right] since left == right
    }

    // Test examples
    public static void main(String[] args) {
        FindMinInRotatedSortedArrayWithDuplicates solver = new FindMinInRotatedSortedArrayWithDuplicates();
        System.out.println(solver.findMin(new int[]{1, 3, 5})); // Output: 1
        System.out.println(solver.findMin(new int[]{2, 2, 2, 0, 1})); // Output: 0
        System.out.println(solver.findMin(new int[]{10, 1, 10, 10, 10})); // Output: 1
    }
}
```

---

### 🧠 Complexity:

* **Best case:** `O(log n)` when duplicates don't interfere.
* **Worst case:** `O(n)` when many duplicates make it impossible to decide direction (e.g., `[2,2,2,2,2,2,2,1,2]`).

---

### 📌 Why Duplicates Affect Runtime:

In regular binary search (without duplicates), we can always tell which side is sorted. But with duplicates like `nums[mid] == nums[right]`, we lose that guarantee.

**Example:**

```text
nums = [3, 3, 1, 3]
mid = 1, nums[mid] = 3, nums[right] = 3 → can't tell which side is sorted
```

So we must **shrink the search space** by `right--`, which becomes linear in worst-case.

---

Let me know if you want a **recursive** version or want to understand the intuition with **visual illustrations**!

 */
