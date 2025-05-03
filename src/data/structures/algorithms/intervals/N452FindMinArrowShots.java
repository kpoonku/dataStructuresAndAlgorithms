package data.structures.algorithms.intervals;

import java.util.Arrays;

public class N452FindMinArrowShots {
    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;

        // Step 1: Sort balloons by end position
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;  // We need at least one arrow
        int end = points[0][1];  // First arrow at the end of the first balloon

        for (int i = 1; i < points.length; i++) {
            // If the current balloon starts after the last arrow position,
            // it can't be burst by the same arrow — we need a new one
            if (points[i][0] > end) {
                arrows++;
                end = points[i][1]; // Update arrow position
            }
        }
        return arrows;
    }

    public static void main(String[] args) {
        int[][] intervals = {{10, 16}, {2, 8}, {1, 6}, {7, 12}};
        System.out.println("Output : " + findMinArrowShots(intervals));
        intervals = new int[][]{{1, 2}, {1, 2}, {1, 2}};
        System.out.println("Output : " + findMinArrowShots(intervals));
    }
}
/*
There are some spherical balloons taped onto a flat wall that represents the XY-plane.
The balloons are represented as a 2D integer array points where points[i] = [xstart, xend]
denotes a balloon whose horizontal diameter stretches between xstart and xend.
You do not know the exact y-coordinates of the balloons.

Arrows can be shot up directly vertically (in the positive y-direction) from different points
along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend.
There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely,
bursting any balloons in its path.

Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

Example 1:
Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].

Example 2:
Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.

Example 3:
Input: points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].

Constraints:
1 <= points.length <= 105
points[i].length == 2
-231 <= xstart < xend <= 231 - 1
*/
/*
Great question! This is a classic **greedy algorithm** problem that’s very similar in spirit
to the **non-overlapping intervals** problem you asked earlier.

Let’s go step by step in a **simple and beginner-friendly** way.
---
### 🎯 The Problem:
You have a list of balloons floating on a wall. Each balloon is described by
its **horizontal range**: `[xstart, xend]`.
You can shoot **arrows vertically**, and an arrow at position `x` will **burst all
balloons where** `xstart <= x <= xend`.

Your goal: **Shoot the minimum number of arrows** to pop all the balloons.
---
### 🧠 Key Idea (Greedy Strategy):
You want to **burst as many balloons as possible** with one arrow.
#### So what do we do?
1. **Sort the balloons by their end points** (the `xend` value).
2. Then **go through each balloon**:
   - If the current balloon starts **after** the last arrow's position, we need a new arrow.
   - Otherwise, the current arrow can burst this one too!
This is greedy: always pick the **earliest end** so we can cover the most balloons with one arrow.
---
### ✅ Java Code:
```java
import java.util.*;
public class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;

        // Step 1: Sort balloons by end position
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;  // We need at least one arrow
        int end = points[0][1];  // First arrow at the end of the first balloon

        for (int i = 1; i < points.length; i++) {
            // If the current balloon starts after the last arrow position,
            // it can't be burst by the same arrow — we need a new one
            if (points[i][0] > end) {
                arrows++;
                end = points[i][1]; // Update arrow position
            }
        }

        return arrows;
    }
}
```
---
### 🧪 Example Walkthrough
Input:
```java
points = [[10,16],[2,8],[1,6],[7,12]]
```
#### Step 1: Sort by end
```
Sorted: [[1,6], [2,8], [7,12], [10,16]]
```
#### Step 2:
- First arrow at `6` → bursts [1,6] and [2,8]
- [7,12] starts at `7` > `6` → needs new arrow at `12`
- [10,16] starts at `10` < `12` → already burst
✅ So: **2 arrows**
---
### ⏱ Time Complexity:
- Sorting: `O(n log n)`
- Looping through: `O(n)`
- Total: **O(n log n)** — Efficient even for large inputs (up to 100,000 balloons)
---
### 💡 Summary:
- Sort by `xend` (end of balloon)
- Always place an arrow at the **end** of the current interval
- If next balloon starts **after** this point, you need a new arrow
---
*/