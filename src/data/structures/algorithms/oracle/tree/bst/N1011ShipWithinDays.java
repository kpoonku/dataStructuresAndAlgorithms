package data.structures.algorithms.oracle.tree.bst;

public class N1011ShipWithinDays {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;

        // Set left to max weight and right to sum of all weights
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }

        // Binary search to find minimum valid ship capacity
        while (left < right) {
            int mid = (left + right) / 2;
            if (canShip(weights, mid, days)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // Helper method to check if we can ship within given days
    private boolean canShip(int[] weights, int capacity, int days) {
        int currentLoad = 0, dayCount = 1;

        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                currentLoad = weight;
                dayCount++;
            } else {
                currentLoad += weight;
            }
        }

        return dayCount <= days;
    }
}

/*
1011. Capacity To Ship Packages Within D Days
A conveyor belt has packages that must be shipped from one port to another within days.

The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with
packages on the conveyor belt (in the order given by weights). We may not load more weight than the
maximum weight capacity of the ship.

Return the least weight capacity of the ship that will result in all the packages on the conveyor
belt being shipped within days.

Example 1:
Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10

Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and
splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.

Example 2:
Input: weights = [3,2,2,4,1,4], days = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4

Example 3:
Input: weights = [1,2,3,1,1], days = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1
*/
/*
Let’s break down this problem step-by-step in a **beginner-friendly** way, and then provide
a **Java solution** using **binary search** (which is the most efficient way to solve this).
---
## 🧠 Problem Summary (Simple Terms)
You have a list of packages, each with a certain **weight**.
You must ship **all packages** in **order** (you can’t rearrange them).
You want to know the **minimum ship capacity** required so that:
* You can ship **all packages**
* In **exactly or fewer than `days` days**
* And you **do not exceed** the ship's daily weight capacity
---
## 🎯 Goal
Return the **smallest** ship capacity that can **finish the job in `days`** or less.
---
## 🔍 Example Walkthrough
Input:
```
weights = [1,2,3,4,5,6,7,8,9,10]
days = 5
```
We want the **smallest capacity** where we can load the packages like this:
* Day 1: 1+2+3+4+5 = 15
* Day 2: 6+7 = 13
* Day 3: 8
* Day 4: 9
* Day 5: 10
✅ So answer is **15**
If the capacity were **less than 15**, like 14, you’d need **more than 5 days**.
---
## 🚀 How to Solve (Using Binary Search)
We don’t **guess every possible capacity**. Instead, we use **binary search**:
1. **Lowest possible capacity** = max weight in the list (because we must carry the heaviest
package at least)
2. **Highest possible capacity** = sum of all weights (if we did everything in one day)
Then we:
* Check the **mid-point capacity**
* Try to simulate how many days it would take using that capacity
* If it takes too many days → try a **bigger capacity**
* If it fits within `days` → try a **smaller capacity**
---
## ✅ Java Code

```java
public class ShipPackages {
    public int shipWithinDays(int[] weights, int days) {
        int low = 0, high = 0;

        // Initialize the search range
        for (int weight : weights) {
            low = Math.max(low, weight); // ship must carry at least the heaviest item
            high += weight;              // at most carry everything in one day
        }

        // Binary search to find the smallest possible capacity
        while (low < high) {
            int mid = (low + high) / 2;

            if (canShip(weights, days, mid)) {
                high = mid; // Try smaller capacity
            } else {
                low = mid + 1; // Need more capacity
            }
        }

        return low; // or high — both are equal now
    }

    // Helper function to check if we can ship with given capacity in 'days'
    private boolean canShip(int[] weights, int days, int capacity) {
        int currentLoad = 0;
        int requiredDays = 1;

        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                requiredDays++;     // Need a new day
                currentLoad = 0;
            }
            currentLoad += weight;
        }

        return requiredDays <= days;
    }

    // Test
    public static void main(String[] args) {
        ShipPackages obj = new ShipPackages();

        int[] weights1 = {1,2,3,4,5,6,7,8,9,10};
        System.out.println(obj.shipWithinDays(weights1, 5)); // Output: 15

        int[] weights2 = {3,2,2,4,1,4};
        System.out.println(obj.shipWithinDays(weights2, 3)); // Output: 6

        int[] weights3 = {1,2,3,1,1};
        System.out.println(obj.shipWithinDays(weights3, 4)); // Output: 3
    }
}
```
---
## ✅ Key Concepts:
* Use **binary search** to optimize the guessing process
* Use a **helper method** to simulate the shipping process for a given capacity
* Efficient: `O(n * log(sum))` time
*/
