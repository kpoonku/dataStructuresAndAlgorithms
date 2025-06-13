package data.structures.algorithms.array.strings;

import java.util.Arrays;
import java.util.List;

public class N1431KidsWithCandies {
    public static List<Boolean> kidsWithCandies
            (int[] candies, int extraCandies) {
        int maxCandies = Integer.MIN_VALUE;
        for (int candy : candies) {
            maxCandies = Math.max(candy, maxCandies);
        }
        Boolean[] output = new Boolean[candies.length];
        for (int i = 0; i < candies.length; i++) {
            output[i] = (candies[i] + extraCandies) >= maxCandies;
        }
        return Arrays.asList(output);
    }

    public static void main(String[] args) {
        int[] candies = {4, 2, 1, 1, 2};
        int extraCandies = 1;
        System.out.println("Output :" +
                kidsWithCandies(candies, extraCandies));
        candies = new int[]{2, 3, 5, 1, 3};
        extraCandies = 3;
        System.out.println("Output :" +
                kidsWithCandies(candies, extraCandies));
        candies = new int[]{12, 1, 12};
        extraCandies = 10;
        System.out.println("Output :" + kidsWithCandies(candies, extraCandies));
    }
}
/*
https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/?envType=study-plan-v2&envId=leetcode-75

1431. Kids With the Greatest Number of Candies
There are n kids with candies. You are given an integer array candies,
where each candies[i] represents the number of candies the ith kid has,
and an integer extraCandies,
denoting the number of extra candies that you have.

Return a boolean array result of length n, where result[i] is true if,
after giving the ith kid all the
extraCandies, they will have the greatest number of candies among all the kids,
or false otherwise.

Note that multiple kids can have the greatest number of candies.

Example 1:
Input: candies = [2,3,5,1,3], extraCandies = 3
Output: [true,true,true,false,true]
Explanation: If you give all extraCandies to:
- Kid 1, they will have 2 + 3 = 5 candies, which is the greatest among the kids.
- Kid 2, they will have 3 + 3 = 6 candies, which is the greatest among the kids.
- Kid 3, they will have 5 + 3 = 8 candies, which is the greatest among the kids.
- Kid 4, they will have 1 + 3 = 4 candies, which is not the greatest among the kids.
- Kid 5, they will have 3 + 3 = 6 candies, which is the greatest among the kids.

Example 2:
Input: candies = [4,2,1,1,2], extraCandies = 1
Output: [true,false,false,false,false]
Explanation: There is only 1 extra candy.
Kid 1 will always have the greatest number of candies, even if a different kid is given the extra candy.

Example 3:
Input: candies = [12,1,12], extraCandies = 10
Output: [true,false,true]

Constraints:
n == candies.length
2 <= n <= 100
1 <= candies[i] <= 100
1 <= extraCandies <= 50
*/
/*
Let's break this problem down into **super simple steps** — like you're explaining it to someone new to coding or a young learner.

---

## 🎯 **What’s the Goal?**

You have a bunch of kids, and each kid has a certain number of candies.
You're also holding some **extra candies** that you can give to **one kid at a time**.

> You want to check for **each kid**:
> 👉 "If I give **all the extra candies** to this kid, will they have the **most candies** of all?"

---

## 🧾 **Example**

```text
candies = [2, 3, 5, 1, 3]
extraCandies = 3
```

We go one by one:

- Kid 1: 2 + 3 = 5 → 🟢 yes (5 is the max)
- Kid 2: 3 + 3 = 6 → 🟢 yes
- Kid 3: 5 + 3 = 8 → 🟢 yes
- Kid 4: 1 + 3 = 4 → 🔴 no (others have more than 4)
- Kid 5: 3 + 3 = 6 → 🟢 yes

So final answer is:

```text
[true, true, true, false, true]
```

---

## 🧠 **Logic (Step-by-Step)**

1. **Find the kid who currently has the most candies.**
   - Example: in `[2,3,5,1,3]`, the biggest is `5`.

2. Now, for **each kid**, do this:
   - Give them all the extra candies.
   - Check: does their total become **greater than or equal** to the current max?
     - If yes → `true`
     - If no  → `false`

---

## 🧑‍💻 Java-like Pseudocode (Easy to Understand)

```java
List<Boolean> result = new ArrayList<>();

// Step 1: Find the max candies any kid currently has
int max = 0;
for (int candy : candies) {
    if (candy > max) {
        max = candy;
    }
}

// Step 2: Check each kid
for (int candy : candies) {
    if (candy + extraCandies >= max) {
        result.add(true);
    } else {
        result.add(false);
    }
}
```

---

## 🧒 Think of It Like a Game:

- You’re the game master.
- You give all your extra candies to one kid at a time.
- After giving, check if that kid becomes the **candy king** (has the most).
- If yes → Mark ✅
- If no  → Mark ❌

---

Would you like the full Java solution with comments or a visual chart showing how each kid's total compares to the max?
 */