package data.structures.algorithms.dp.onedimension;

public class N790DominoTromino {
    public static int numTilings(int n) {
        int MOD = 1_000_000_007;

        if (n == 1) return 1;
        if (n == 2) return 2;
        if (n == 3) return 5;

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = (2 * dp[i - 1] + dp[i - 3]) % MOD;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println("Num Tilings for n - " + n + " is " + numTilings(n));
    }
}
/*
Awesome! Let's walk through **how `dp[0]` is used** in calculating something like
`dp[3]` or `dp[4]` — and **why it's so important** in your dynamic programming (DP) solution. 🧠📐
---
## 🧮 First, remember the formula:
We're using this formula for the number of ways to tile a **2 × n** board:
```java
dp[n] = 2 * dp[n - 1] + dp[n - 3]
```
We already defined:
```java
dp[0] = 1  ✅
dp[1] = 1
dp[2] = 2
dp[3] = ?
```
Let’s compute `dp[3]`.
---
## 🧩 Step-by-Step: Compute `dp[3]`
### Using the formula:
```
dp[3] = 2 * dp[2] + dp[0]
```
We plug in values:
```
dp[3] = 2 * 2 + 1 = 5
```
✨ Boom! We just used `dp[0] = 1` right there!
---
### 💡 But what does `dp[0] = 1` **mean** here?
It represents the one way to **do nothing** — to completely tile a zero-width board.
That one way (doing nothing) becomes the base of building tromino-based configurations
when you’re **backtracking three spaces** (like for `dp[n - 3]`).
If `dp[0]` were `0`, it would wrongly say “you can’t tile a 2×3 board using trominos” —
but you **can**!
---
## 🧱 Visualization of `dp[3] = 5`
Let’s list all 5 ways to tile a 2×3 board:
```
1. Vertical dominoes: 3 verticals
2. Horizontal dominoes: 1st row → 3 horizontals
3. L-tromino in left corner + domino
4. Mirrored L-tromino in right corner + domino
5. Tromino in the middle with dominoes on the sides
```
One of those configurations — the one where a tromino is placed across the
**leftmost 3 cells** — depends on `dp[0]` (what’s left after using a
tromino that covers the first 3 columns).
So it contributes:
```
+1 (dp[0]) → the “base case” under that tromino
```
---
## ✅ Why `dp[0] = 1` Is Important:
- It's the **starting point** for the whole DP solution.
- It represents the 1 way to tile **nothing** (empty board).
- It lets the **recursive relation work correctly**, especially for base cases like `dp[3]`.
---
## 🔁 Summary:

| Expression       | Meaning                                 |
|------------------|------------------------------------------|
| `dp[0] = 1`      | One way to tile a 2×0 board (do nothing) |
| `dp[3] = 2 * dp[2] + dp[0]` | Covers all combinations — including the case where a
tromino is used first, and then nothing is left. |
| `dp[4] = 2 * dp[3] + dp[1]` | Reuses previously computed answers efficiently |

---

Want to go step-by-step for `dp[4] = 11` too, showing what each part represents?
 */
/*
Great! Let’s break this one down **step-by-step**, nice and easy, for your beginner brain 🧠💡

---

## 🤖 Problem Summary

You have a **robot** on a **grid** of size `m × n`.

- It **starts** at the top-left (0, 0)
- It wants to reach the **bottom-right** (m - 1, n - 1)
- It can only move:
  - 👉 **Right**
  - 👇 **Down**

🟦🟦🟦🟦🟦🟦🟦
🟦🟦🟦🟦🟦🟦🟦
🟦🟦🟦🟦🟦🟦🟦

---

## 🎯 Your Goal:
> Count **how many different paths** the robot can take from top-left to bottom-right.

---

## 🔍 Example:
### For `m = 3`, `n = 7`:
```
Grid = 3 rows, 7 columns
```
✅ Output: `28`

---

## 🧠 How to Think About It (Dynamic Programming style)

Let’s say:

```
dp[i][j] = number of unique paths to cell (i, j)
```

Now, how can the robot reach `(i, j)`?

🟢 From the **top cell** → `(i-1, j)`
🟢 From the **left cell** → `(i, j-1)`

So we can say:

```java
dp[i][j] = dp[i-1][j] + dp[i][j-1]
```

### Base case:
- First row (`i = 0`) → only 1 way (keep going right)
- First column (`j = 0`) → only 1 way (keep going down)

---

## ✅ Java Code (Beginner-Friendly Version)

```java
public int uniquePaths(int m, int n) {
    int[][] dp = new int[m][n];

    // Fill the first row and column with 1s
    for (int i = 0; i < m; i++) dp[i][0] = 1;
    for (int j = 0; j < n; j++) dp[0][j] = 1;

    // Fill the rest using dp[i][j] = top + left
    for (int i = 1; i < m; i++) {
        for (int j = 1; j < n; j++) {
            dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
        }
    }

    return dp[m - 1][n - 1];
}
```

---

## 🎨 Visual Explanation for `m = 3`, `n = 2`

Here’s the grid with ways to reach each square:

```
[1][1]
[1][2]
[1][3]
```

Explanation:
- First column = always 1
- Second column = sum of top + left

So at bottom-right: `3` ways!

---

## 🔢 Math Shortcut (Combinations)

There’s also a mathematical trick:

- To go from top-left to bottom-right, robot must make:
  - `m - 1` **downs**
  - `n - 1` **rights**

So total steps = `(m + n - 2)`
Choose **where to place rights** → choose `(n - 1)` steps from total:

```java
// Using combinations: C(m + n - 2, n - 1)
```

This is faster for big grids!

Let me know if you want that version too.

---

### 🎁 Summary

| Concept        | Idea                                                                 |
|----------------|----------------------------------------------------------------------|
| Problem        | Count paths from (0,0) to (m-1,n-1) with only right/down moves       |
| Approach       | DP: Each cell = top + left                                            |
| Base Case      | First row and first column = 1                                        |
| Time Complexity| O(m × n)                                                              |
| Optional       | Use combinations for math shortcut: C(m+n−2, m−1) or C(m+n−2, n−1)    |

---

Want to try solving for `m = 5, n = 5` together? Or see the math shortcut version in code? 😄
 */