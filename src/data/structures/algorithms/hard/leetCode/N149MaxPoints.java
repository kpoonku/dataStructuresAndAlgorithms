package data.structures.algorithms.hard.leetCode;


import java.util.HashMap;
import java.util.Map;

public class N149MaxPoints {
    // Test cases
    public static void main(String[] args) {
        N149MaxPoints solver = new N149MaxPoints();
        System.out.println(solver.maxPoints(new int[][]{{1, 1}, {2, 2}, {3, 3}})); // Output: 3
        System.out.println(solver.maxPoints(new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}})); // Output: 4
    }

    public int maxPoints(int[][] points) {
        if (points.length <= 2) return points.length;

        int max = 0;

        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> slopeCount = new HashMap<>();
            int duplicate = 1; // count of same point as anchor
            int localMax = 0;

            for (int j = i + 1; j < points.length; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicate++;
                } else {
                    int gcd = gcd(dx, dy);
                    dx /= gcd;
                    dy /= gcd;

                    // Normalize slope sign
                    if (dx < 0) {
                        dx = -dx;
                        dy = -dy;
                    }

                    String slope = dy + "/" + dx;
                    slopeCount.put(slope, slopeCount.getOrDefault(slope, 0) + 1);
                    localMax = Math.max(localMax, slopeCount.get(slope));
                }
            }

            max = Math.max(max, localMax + duplicate);
        }

        return max;
    }

    // GCD helper
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
}
/*
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane,
return the maximum number of points that lie on the same straight line.

Example 1:
Input: points = [[1,1],[2,2],[3,3]]
Output: 3

Example 2:
Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4

Constraints:
1 <= points.length <= 300
points[i].length == 2
-104 <= xi, yi <= 104
All the points are unique.
 */
/*
To solve the problem of finding the **maximum number of points on the same straight line**, we use the **slope** concept between pairs of points.

---

### ✅ Core Idea:

Two points define a line. If multiple points share the same **slope** with a common origin point, they lie on the same line.

### 🧠 Key Concepts:

* **Slope formula:**
  For points `A(x1, y1)` and `B(x2, y2)`,
  slope = (y2 - y1) / (x2 - x1)

* To avoid **floating-point errors**, we store the slope as a **reduced fraction**:

  * Store slope as a pair `(dy / gcd, dx / gcd)`
  * Handle vertical lines (`dx = 0`) and horizontal lines (`dy = 0`) separately.

* We check for each point as the **anchor**, and count all slopes formed with other points.

---

### ✅ Java Code:

```java
import java.util.*;

public class MaxPointsOnLine {
    public int maxPoints(int[][] points) {
        if (points.length <= 2) return points.length;

        int max = 0;

        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> slopeCount = new HashMap<>();
            int duplicate = 1; // count of same point as anchor
            int localMax = 0;

            for (int j = i + 1; j < points.length; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];

                if (dx == 0 && dy == 0) {
                    duplicate++;
                } else {
                    int gcd = gcd(dx, dy);
                    dx /= gcd;
                    dy /= gcd;

                    // Normalize slope sign
                    if (dx < 0) {
                        dx = -dx;
                        dy = -dy;
                    }

                    String slope = dy + "/" + dx;
                    slopeCount.put(slope, slopeCount.getOrDefault(slope, 0) + 1);
                    localMax = Math.max(localMax, slopeCount.get(slope));
                }
            }

            max = Math.max(max, localMax + duplicate);
        }

        return max;
    }

    // GCD helper
    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Test cases
    public static void main(String[] args) {
        MaxPointsOnLine solver = new MaxPointsOnLine();
        System.out.println(solver.maxPoints(new int[][]{{1,1},{2,2},{3,3}})); // Output: 3
        System.out.println(solver.maxPoints(new int[][]{{1,1},{3,2},{5,3},{4,1},{2,3},{1,4}})); // Output: 4
    }
}
```

---

### 🕒 Time Complexity:

* `O(n^2)` where `n` is number of points.
* For each point, we compare it with every other point.

---

### 📌 Output Examples:

```java
Input: [[1,1],[2,2],[3,3]]
Output: 3

Input: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4
```

---

Let me know if you'd like the same solution in **Python**, or with performance optimizations for large data sets!

 */