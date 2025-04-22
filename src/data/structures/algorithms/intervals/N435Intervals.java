package data.structures.algorithms.intervals;

import java.util.Arrays;

public class N435Intervals {
    public static int eraseOverlapIntervals(int[][] intervals) {
        // Step 1: Sort by end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0; // How many intervals we can keep
        int prevEnd = Integer.MIN_VALUE;

        for (int[] interval : intervals) {
            if (interval[0] >= prevEnd) {
                // No overlap, we can keep this one
                count++;
                prevEnd = interval[1];
            }
            // Else: overlap, so we skip (remove) this one
        }

        // Step 3: Total - count of non-overlapping = number to remove
        return intervals.length - count;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 2}, {2, 3}, {3, 4}, {1, 3}};
        System.out.println("Output : " + eraseOverlapIntervals(intervals));
        intervals = new int[][]{{1, 2}, {1, 2}, {1, 2}};
        System.out.println("Output : " + eraseOverlapIntervals(intervals));
    }
}
/*
Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.



Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.


Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104
 */
/*
Great question! You're being asked to **remove the minimum number of intervals** from a list so that **none of the remaining intervals overlap**.

Let’s go step by step in a way that’s easy to understand. ✅

---

### 🔍 Problem in Simple Words:

You're given a list of time intervals like:

```
[1,2], [2,3], [3,4], [1,3]
```

Some of these intervals **overlap**, and your job is to remove as few as possible so that all the remaining intervals are non-overlapping.

---

### 🧠 Key Idea: Use a **Greedy Algorithm**

You want to **keep** as many non-overlapping intervals as possible.

So instead of thinking "how many should I remove?", think:

> "How can I keep the **maximum number of intervals** that don’t overlap?"

Then:

> `Minimum to remove = Total - Max non-overlapping`

---

### ✅ Strategy:

1. **Sort** the intervals by their `end` time.
   - Why? So we always pick the interval that finishes the earliest — leaving more space for others.

2. Loop through the intervals and keep a counter:
   - If the current interval **doesn’t overlap** with the previous one, we keep it
   - Otherwise, we skip (remove) it

---

### ✅ Java Code:

```java
import java.util.*;

public class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        // Step 1: Sort by end time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

        int count = 0; // How many intervals we can keep
        int prevEnd = Integer.MIN_VALUE;

        for (int[] interval : intervals) {
            if (interval[0] >= prevEnd) {
                // No overlap, we can keep this one
                count++;
                prevEnd = interval[1];
            }
            // Else: overlap, so we skip (remove) this one
        }

        // Step 3: Total - count of non-overlapping = number to remove
        return intervals.length - count;
    }
}
```

---

### 🧪 Example: `[[1,2],[2,3],[3,4],[1,3]]`

- After sorting by end: `[[1,2],[1,3],[2,3],[3,4]]`
- Keep [1,2]
- [1,3] overlaps → skip
- [2,3] doesn’t → keep
- [3,4] doesn’t → keep

✅ Keep 3 intervals → Need to remove **1**

---

### 💡 Time Complexity:

- Sorting: `O(n log n)`
- Looping: `O(n)`
- Total: **O(n log n)** — Fast and works well up to `10^5` intervals

---

Let me know if you want the **step-by-step visual walkthrough** or explanation with diagrams. Happy to draw it out or simplify further!
 */