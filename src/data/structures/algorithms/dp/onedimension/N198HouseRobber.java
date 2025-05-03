package data.structures.algorithms.dp.onedimension;

public class N198HouseRobber {
    public static void main(String[] args) {
        N198HouseRobber sol = new N198HouseRobber();
        System.out.println("result : " + sol.rob(new int[]{1, 2, 3, 1}));
        System.out.println("result : " + sol.rob(new int[]{2, 7, 9, 3, 1}));
    }

    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int maxAmount = Math.max(nums[1], nums[0]);
        int prev = nums[0];

        for (int i = 2; i < nums.length; i++) {
            int current = Math.max(nums[i] + prev, maxAmount);
            prev = maxAmount;
            maxAmount = current;
        }

        return maxAmount;
    }
}
/*
https://leetcode.com/problems/house-robber/?envType=study-plan-v2&envId=leetcode-75

You are a professional robber planning to rob houses along a street. Each house has a
 certain amount of money stashed, the only constraint stopping you from robbing each
 of them is that
 adjacent houses have security systems connected and it will automatically contact the
 police if two
 adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the
maximum amount of money you can rob tonight without alerting the police.

Example 1:
Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.

Example 2:
Input: nums = [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
Total amount you can rob = 2 + 9 + 1 = 12.

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 400
*/
/*
Great! Let’s break down this classic problem, known as the **House Robber** problem,
in a way that’s super beginner-friendly and then write the Java solution.
---
## 🎯 Problem Recap (In Simple Terms):
You’re a thief trying to rob houses.
Each house has some amount of money (`nums[i]`), but there's a catch:
> **You can’t rob two houses in a row**, or the alarm will go off!
Your goal is to **rob houses in a way that gets you the most money**, without robbing
any two **next to each other**.
---
## 🧠 How to Think About It:
Let’s say:
```java
nums = [2, 7, 9, 3, 1]
```
You can:
* Rob house 1 → 2
* Skip house 2
* Rob house 3 → 9
* Skip house 4
* Rob house 5 → 1
Total = 2 + 9 + 1 = 12
But you could also try:
* Skip house 1
* Rob house 2 → 7
* Skip house 3
* Rob house 4 → 3
Total = 7 + 3 = 10 → not better than first one.
So what we’re trying to figure out is:
> "At each house, should I rob it or skip it — to get the **most total money**?"
---
## ✅ Dynamic Programming (DP) Approach (Beginner Friendly)
Let’s build an array `dp[]` where:
* `dp[i]` = maximum money we can rob **up to house i**
### Rules:
1. If you rob house `i`, you **can’t rob house `i-1`**
2. So:
   ```
   dp[i] = max(
       dp[i-1],              // skip current house
       nums[i] + dp[i-2]     // rob current house + value from 2 houses before
   )
   ```
---
## 🧑‍💻 Java Code (Step by Step)
```java
public class HouseRobber {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;         // No houses
        if (n == 1) return nums[0];   // Only one house
        if (n == 2) return Math.max(nums[0], nums[1]); // Pick richer house
        // Create a DP array to store max money up to each house
        int[] dp = new int[n];
        dp[0] = nums[0];                        // Rob first house
        dp[1] = Math.max(nums[0], nums[1]);     // Rob richer of first two
        // Fill the rest
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i] + dp[i - 2]);
        }
        // The last element has the answer
        return dp[n - 1];
    }
}
```
---
## 🧪 Example Run:
Input: `[2, 7, 9, 3, 1]`
We build:
```
dp[0] = 2
dp[1] = max(2, 7) = 7
dp[2] = max(7, 9 + 2) = 11
dp[3] = max(11, 3 + 7) = 11
dp[4] = max(11, 1 + 11) = 12
```
**Final answer = 12**
---
## 🧠 Summary:
* Use an array to track the best amount you can rob up to each house.
* Decide: Rob current house or skip it?
* Use past results to make smart decisions (that’s dynamic programming!).
Would you like this simplified even more or visualized with a table for the steps?
 */
