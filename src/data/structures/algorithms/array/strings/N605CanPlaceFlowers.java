package data.structures.algorithms.array.strings;

public class N605CanPlaceFlowers {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int cnt = 0;
        int length = flowerbed.length;
        for (int i = 0; i < length; i++) {
            if (cnt >= n) {
                return true;
            }
            if (flowerbed[i] == 0 &&
                    (i == 0 || flowerbed[i - 1] == 0) &&
                    (i == length - 1 || flowerbed[i + 1] == 0)) {
                cnt++;
                flowerbed[i] = 1;
            }
        }
        return cnt >= n;
    }

    public static void main(String[] args) {
        System.out.println
                ("can Place Flowers : " +
                        canPlaceFlowers(new int[]{1, 0, 1, 0, 0}, 1));
        System.out.println
                ("can Place Flowers : " +
                        canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2));
    }
}

/*
605. Can Place Flowers
You have a long flowerbed in which some of the plots are planted, and some are not. However,
flowers cannot be planted in adjacent plots.
Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty,
and an integer n, return true if n new flowers can be planted in the flowerbed without violating
the no-adjacent-flowers rule and false otherwise.
Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: true
Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: false
 */
/*
Let's explain **Leetcode 605: Can Place Flowers** in a very simple, **beginner-friendly way**
— like you're solving a puzzle or playing a game.
---
## 🧠 Imagine the Problem Like This:
You have a **row of garden plots**.
Each spot is either:
- `1` = already has a flower 🌸
- `0` = empty spot 🟦
But here’s the rule:
> **You can't plant flowers next to each other.**
> No flower should be in **adjacent** plots.
---
### 🔍 Example 1:
```
flowerbed = [1, 0, 0, 0, 1]
n = 1
```
Let’s look at it:
- The flowerbed is:
  `1` `0` `0` `0` `1`
  🌸 🟦 🟦 🟦 🌸
- You **cannot plant next to the existing flowers**, so positions 0 and 4 are blocked.
- But look at the middle:
  ```
  1  0  0  0  1
     ↑  ↑  ↑
  ```
  The middle `0 0 0` has a space at index 2 (the center 0):
  - Left is `0`
  - Right is `0`
  ✅ You **can** plant 1 flower at index 2 → now it’s:
  `1 0 1 0 1` → 🌸 🟦 🌸 🟦 🌸
  So **YES**, you can plant 1 flower. → Output: `true`
---
### ❌ Example 2:
```
flowerbed = [1, 0, 0, 0, 1]
n = 2
```
Same garden:
- Only one safe place to plant (index 2).
- But you’re asked to plant **2 flowers**.
🚫 No way to fit 2 flowers without putting them side by side.
So, **Output: false**
---
## ✅ Rule for Planting
When you're at a spot (say index `i`), you can plant a flower **only if**:
```java
flowerbed[i] == 0 &&
(i == 0 || flowerbed[i - 1] == 0) &&
(i == flowerbed.length - 1 || flowerbed[i + 1] == 0)
```
---
## 👶 Simple Way to Think:
- You walk through the garden one spot at a time.
- Ask: "Is this spot empty **and** are both neighbors empty (or out of bounds)?"
- If yes → plant a flower and **skip the next spot** (because you just planted one).
- Count how many you planted.
- If you planted `n` or more → return `true`. Else → `false`.
---
Would you like to see the beginner-level Java code for this with comments?
*/

