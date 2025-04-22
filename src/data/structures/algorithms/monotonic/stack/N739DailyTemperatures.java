package data.structures.algorithms.monotonic.stack;

import java.util.Arrays;
import java.util.Stack;

public class N739DailyTemperatures {
    public static int[] dailyTemperatures(int[] temperatures) {
        int len = temperatures.length;
        int[] result = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = len - 1; i >= 0; i--) {
            if (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                result[i] = stack.peek() - i;
            }
            stack.push(i);
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    public static void main(String[] args) {
        dailyTemperatures(new int[]{30, 40, 50, 60});
        dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        dailyTemperatures(new int[]{30, 40, 35, 50});
    }
}
/*
Given an array of integers temperatures represents the daily temperatures,
 return an array answer such that answer[i] is the number of days you have to wait after 
 the ith day to get a warmer temperature. If there is no future day for which this is possible, 
 keep answer[i] == 0 instead.

Example 1:
Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:
Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:
Input: temperatures = [30,60,90]
Output: [1,1,0]

Constraints:
1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
 */
/*
Absolutely! Let's slow it down and explain this in a **super beginner-friendly** way — no fancy terms like "monotonic stack" (we’ll sneak that in later when it makes sense 😄).
---
### 🎯 The Problem (In Simple Words):
You are given a list of daily temperatures. For **each day**, you want to know:
> How many days will I have to wait until a warmer temperature?
If **no warmer day** is coming, just put `0`.
---

### 🧊 Example:
Let’s say we have these temperatures:
```
[73, 74, 75, 71, 69, 72, 76, 73]
```
Let’s look at each day:
- Day 0: 73 → next warmer is 74 (1 day later) → `1`
- Day 1: 74 → next warmer is 75 → `1`
- Day 2: 75 → next warmer is 76 → in 4 days → `4`
- Day 3: 71 → next warmer is 72 → in 2 days → `2`
- ...
- Day 6: 76 → no warmer day → `0`
- Day 7: 73 → no warmer day → `0`

Final Answer:
`[1, 1, 4, 2, 1, 1, 0, 0]`
---

### 🤔 So How Can We Do This Faster?
We could check every day after each day (brute force), but that would be too slow for large inputs.
Instead, we use a **stack** — think of it as a box where we keep track of "possible future warmer days."
---
### 📦 Stack — Simple Analogy:
Imagine you're walking **backwards through the week** (from the last day to the first):
- You have a list of temperatures
- You carry a **stack (box)** where you only keep the **most useful future days**
---
### 🪜 Step-by-Step Visual Walkthrough
Let's go through this list **from right to left**:
```
temperatures = [73, 74, 75, 71, 69, 72, 76, 73]
                         ^                 ^
                         i             <-  Start from the last day (index 7)
```
We’ll keep:
- A **stack** of days (indexes)
- An **answer array** to store how many days to wait
---
#### Day 7: 73
- No future days yet → `0`
- Put day 7 into the stack
**Stack**: [7]
**Answer**: [_, _, _, _, _, _, _, 0]
---
#### Day 6: 76
- 76 is hotter than 73 (day 7), so day 7 isn’t helpful anymore → remove it
- No other future warmer days → `0`
- Put day 6 in the stack
**Stack**: [6]
**Answer**: [_, _, _, _, _, _, 0, 0]
---
#### Day 5: 72
- 76 (day 6) is warmer than 72 → use it
- Wait 1 day → `1`
- Put day 5 in stack
**Stack**: [6, 5]
**Answer**: [_, _, _, _, _, 1, 0, 0]
---
#### Day 4: 69
- 72 (day 5) is warmer → use it
- Wait 1 day → `1`
- Put day 4 in stack
**Stack**: [6, 5, 4]
**Answer**: [_, _, _, _, 1, 1, 0, 0]
---
#### Day 3: 71
- 69 (day 4) is cooler → pop it
- 72 (day 5) is warmer → use it
- Wait 2 days → `2`
- Put day 3 in stack
**Stack**: [6, 5, 3]
**Answer**: [_, _, _, 2, 1, 1, 0, 0]
---
#### Day 2: 75
- 71 (day 3) is cooler → pop
- 72 (day 5) is cooler → pop
- 76 (day 6) is warmer → use it
- Wait 4 days → `4`
- Put day 2 in stack
**Stack**: [6, 2]
**Answer**: [_, _, 4, 2, 1, 1, 0, 0]
---
#### Day 1: 74
- 75 (day 2) is warmer → wait 1 day → `1`
- Stack: [6, 2, 1]
---
#### Day 0: 73
- 74 (day 1) is warmer → wait 1 day → `1`
---
### 🎉 Final Answer:
```
[1, 1, 4, 2, 1, 1, 0, 0]
```
---
### 📌 What You Need to Know:
- We use a stack to **keep track of future warmer days**
- We loop from **right to left**
- For each day:
  - Remove (pop) all future days that are **not warmer**
  - If we find a warmer day, calculate the difference in days
---
### ✅ Bonus: What’s a Monotonic Stack?
Now that you’ve seen how the stack **always keeps days with increasing temperatures**, that’s what we call a **monotonic (increasing) stack** — a fancy word for a stack that keeps things in order.
---
### Want To Try it Yourself?
Start with a tiny example:
```
[30, 40, 35, 50]
```
Try to follow the same steps and build your own stack and answer.
---
Let me know if you'd like to build a **visual simulation** or try it step-by-step with me live!
 */
