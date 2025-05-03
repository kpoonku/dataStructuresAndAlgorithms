package data.structures.algorithms.dp.onedimension;

import java.util.Arrays;

public class N746MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        if (cost.length == 0) return 0;
        if (cost.length == 1) return cost[0];
        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];

        for (int i = 2; i < cost.length; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }
        System.out.println(Arrays.toString(dp));
        return Math.min(dp[cost.length-1], dp[cost.length-2]);
    }

    public static void main(String[] args) {
        int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        N746MinCostClimbingStairs sol = new N746MinCostClimbingStairs();
        System.out.println("result : " + sol.minCostClimbingStairs(cost));
        cost = new int[] {10, 15, 20};
        System.out.println("result : " + sol.minCostClimbingStairs(cost));
    }
}
/*
You are given an integer array cost where cost[i] is the cost of ith
step on a staircase. Once you pay the cost, you can either climb one or two steps.

You can either start from the step with index 0, or the step with index 1.

Return the minimum cost to reach the top of the floor.

Example 1:
Input: cost = [10,15,20]
Output: 15
Explanation: You will start at index 1.
- Pay 15 and climb two steps to reach the top.
The total cost is 15.

Example 2:
Input: cost = [1,100,1,1,1,100,1,1,100,1]
Output: 6
Explanation: You will start at index 0.
- Pay 1 and climb two steps to reach index 2.
- Pay 1 and climb two steps to reach index 4.
- Pay 1 and climb two steps to reach index 6.
- Pay 1 and climb one step to reach index 7.
- Pay 1 and climb two steps to reach index 9.
- Pay 1 and climb one step to reach the top.
The total cost is 6.

Constraints:
2 <= cost.length <= 1000
0 <= cost[i] <= 999
*/
/*
Absolutely! Let’s break it down for a beginner’s brain 🧠—step by step, super clear and relatable.
---
### 🚶‍♂️ The Problem (In Simple Words):
You’re climbing a staircase 🪜. Each step has a **cost**, like paying coins to step on it.
- You can start from **step 0 or step 1**.
- From any step, you can **go 1 or 2 steps up**.
- Your goal is to reach the **top of the floor (past the last step)**.
- You want to **spend the least amount of money** to get there.
---
### 🧠 How to Think About It:
Let’s say the steps cost this:
```java
int[] cost = {10, 15, 20};
```
You have 3 steps:
- Step 0 costs 10
- Step 1 costs 15
- Step 2 costs 20
To reach the top (which is **after step 2**), you have 2 options:
- Go: Step 0 → Step 2 → Top
- Go: Step 1 → Step 3 (Top)
We want the **cheapest way** to get there.
---
### 🛠️ Key Idea — Dynamic Programming:
We’ll create a plan where we **remember the cheapest way** to get to each step.
Think like this:
- "To reach step `i`, I can come from either step `i-1` or step `i-2`."
- So, I’ll take the **cheapest** of those two and **add the cost of step `i`**.
Let’s create an array called `dp`:
```java
dp[i] = the minimum cost to reach step i
```
We set the first two manually:
```java
dp[0] = cost[0]; // Because it's the cost of stepping on it
dp[1] = cost[1]; // Same here
```
Now, for every next step:
```java
dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
```
This means:
- "The cheapest way to get to this step is by taking the cheapest route to step
before it or two steps before it, and adding this step's cost."
---
### 🧮 Full Example:
```java
int[] cost = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
```
We'll compute the cheapest way to each step:
- dp[0] = 1
- dp[1] = 100
- dp[2] = cost[2] + min(dp[1], dp[0]) = 1 + min(100, 1) = 2
- dp[3] = 1 + min(2, 100) = 3
- dp[4] = 1 + min(3, 2) = 3
- dp[5] = 100 + min(3, 3) = 103
- dp[6] = 1 + min(103, 3) = 4
- dp[7] = 1 + min(4, 103) = 5
- dp[8] = 100 + min(5, 4) = 104
- dp[9] = 1 + min(104, 5) = 6
🟩 You don’t pay to step **off the last step**, so:
```java
return min(dp[n-1], dp[n-2]);
```
So the answer is:
```java
min(6, 104) = 6
```
---
### 🎯 Summary:
You're building a **cheapest path map** step by step. At each step, you ask:
> "What's the cheapest way to get here? From one step back or two steps back?"
That’s **dynamic programming** — solving a big problem by building up the answer from small solutions.
---
Let me know if you'd like me to **draw this out visually**, or want an animation-style explanation!
*/