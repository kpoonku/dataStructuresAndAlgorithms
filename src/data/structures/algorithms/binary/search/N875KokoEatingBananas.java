package data.structures.algorithms.binary.search;

public class N875KokoEatingBananas {

    public static void main(String[] args) {
        N875KokoEatingBananas koko = new N875KokoEatingBananas();

        System.out.println(koko.minEatingSpeed(new int[]{3, 6, 7, 11}, 8)); // Output: 4
        System.out.println(koko.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5)); // Output: 30
        System.out.println(koko.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6)); // Output: 23
    }

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = getMaxPiles(piles);
        int result = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println("left : " + left + ", right : " + right + ", mid : " + mid);
            if (canFinish(piles, h, mid)) {
                result = mid; // try to find a smaller k
                right = mid - 1;
            } else {
                left = mid + 1; // too slow, try faster k
            }
        }
        return result;
    }

    public boolean canFinish(int[] piles, int h, int k) {
        int hours = 0;
        for (int pile : piles) {
            hours += (pile + k - 1) / k;
            //hours += Math.ceil((double) pile/k);
        }
        System.out.println("Hours for k : " + k + " : " + hours);
        return hours <= h;
    }

    public int getMaxPiles(int[] piles) {
        int maxPile = 0;
        for (int pile : piles) {
            maxPile = Math.max(pile, maxPile);
        }
        return maxPile;
    }
}
/*
Koko loves to eat bananas. There are n piles of bananas,
the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour,
she chooses some pile of bananas and eats k bananas from that pile.
If the pile has less than k bananas, she eats all of them instead and will not eat any
more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the
guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.

Example 1:
Input: piles = [3,6,7,11], h = 8
Output: 4

Example 2:
Input: piles = [30,11,23,4,20], h = 5
Output: 30

Example 3:
Input: piles = [30,11,23,4,20], h = 6
Output: 23

Constraints:
1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109
*/
/*
Why mid ?
Great question! Both expressions are used to calculate the **middle index** in binary search:

### ✅ `(left + right) / 2`
This is the **simplest and most common** form, but it can be risky.

### ⚠️ Problem with `(left + right) / 2`:
When `left` and `right` are **very large integers**, adding them together
(`left + right`) can **overflow** the maximum value an `int`
can hold in Java (which is 2³¹ - 1 = 2,147,483,647).

---

### ✅ Safe alternative:
```java
int mid = left + (right - left) / 2;
```

#### Why it's better:
- `(right - left)` will **never overflow**, because it's the size of the range
(always ≤ total array size).
- Adding it to `left` is safe unless both are extremely large, which is rare in practice.

---

### 📌 Example of Overflow Risk:

Imagine:
```java
left = 2_000_000_000
right = 2_100_000_000
```

- `(left + right)` = 4,100,000,000 → 🚫 **Overflow!**
- But:
  - `(right - left) / 2` = 50,000,000
  - `left + (right - left) / 2` = 2,050,000,000 → ✅ Safe!

---

### ✅ TL;DR (Which Should You Use?)

| Expression                  | Safe for large values? | Recommended |
|----------------------------|------------------------|-------------|
| `(left + right) / 2`        | ❌ No (can overflow)    | 🚫 Not safe |
| `left + (right - left)/2`   | ✅ Yes                  | ✅ Use this |

---

Would you like to see a working code example that shows the overflow in Java?
 */
/*
Let's break this problem down at a **beginner level** and then explain the logic step-by-step.

---

## 🐒 Problem Summary:
**Koko is eating bananas.**
She must eat **all the piles** in **at most `h` hours**.
She eats at a speed of `k` bananas per hour.

Each hour:
- She picks **one pile** and eats **up to `k` bananas**.
- If the pile has **less than `k`**, she just eats the whole pile in 1 hour.

---

## 🎯 Goal:
> Find the **smallest** value of `k` (eating speed per hour) so that **Koko finishes in `h`
 hours or less**.

---

## 🧠 How Do We Solve This?

We can use **Binary Search**! Here's why:

- The **minimum speed** Koko can eat is `1` banana/hour.
- The **maximum speed** she ever needs is the **biggest pile**, because eating faster won’t
help (you can only eat one pile per hour).
- So we **search between 1 and max(piles)** to find the **smallest `k`** that works.

---

## 🧑‍💻 Step-by-Step Logic

1. Set `low = 1`, `high = max(piles)`
2. While `low < high`:
   - Find `mid = (low + high) / 2`
   - Calculate **how many hours** it would take Koko to eat all bananas at speed `mid`
   - If total hours ≤ `h`: ✅ Try a smaller speed → `high = mid`
   - Else: ❌ Too slow → Try faster speed → `low = mid + 1`
3. Return `low` (the minimum speed that worked)

---

## 🔢 How to Calculate Hours at Speed `k`

For each pile `p`, time needed is:

```
hours = ceil(p / k)
```

In code:
```java
hours += (pile + k - 1) / k;
```
(This avoids using floating point math)

---

## 🧑‍💻 Java-like Pseudocode

```java
int minEatingSpeed(int[] piles, int h) {
    int low = 1;
    int high = Arrays.stream(piles).max().getAsInt();

    while (low < high) {
        int mid = (low + high) / 2;
        int hours = 0;
        for (int pile : piles) {
            hours += (pile + mid - 1) / mid;
        }

        if (hours <= h) {
            high = mid;  // Try slower speed
        } else {
            low = mid + 1;  // Too slow
        }
    }

    return low;
}
```

---

## 🧾 Example Walkthrough

Input:
```text
piles = [3,6,7,11], h = 8
```

Try speeds from 1 to 11:

- Try `k = 6`:
  - [3/6=1, 6/6=1, 7/6≈2, 11/6≈2] → 1+1+2+2 = 6 ✅ (too much time left → try slower)
- Try `k = 4`:
  - [1, 2, 2, 3] → 8 ✅ ← This is the smallest k that works!

So, `Output = 4`

---

Would you like me to generate this in full Java with comments or visualize the binary search process step-by-step?
 */
