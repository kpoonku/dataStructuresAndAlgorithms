package data.structures.algorithms.hard.leetCode;

public class N115DistinctSubSequences {
    public static void main(String[] args) {
        N115DistinctSubSequences sol = new N115DistinctSubSequences();
        System.out.println(sol.numDistinct("rabbbit", "rabbit"));
        System.out.println(sol.numDistinct("babgbag", "bag"));
    }
    public int numDistinct(String s, String t) {
        int rows = s.length();
        int cols = t.length();
        int[][] dp = new int[rows + 1][cols + 1];

        dp[0][0] = 1;
        for (int r = 1; r <= rows; r++) {
            dp[r][0] = 1;
        }

        for (int r = 1; r <= rows; r++) {
            for (int c = 1; c <= cols; c++) {
                if (s.charAt(r - 1) == t.charAt(c - 1)) {
                    dp[r][c] = dp[r - 1][c - 1] + dp[r - 1][c];
                } else {
                    dp[r][c] = dp[r - 1][c];
                }
            }
        }


        return dp[rows][cols];
    }
}
/*
Given two strings s and t, return the number of distinct subsequences of s which equals t.

The test cases are generated so that the answer fits on a 32-bit signed integer.

Example 1:
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit
Example 2:

Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag


Constraints:

1 <= s.length, t.length <= 1000
s and t consist of English letters.
 */
/*
Absolutely — this line is **the heart of the dynamic programming logic**, and it's super important to understand it clearly. Let's break it down **slowly and visually**.

---

### 🧠 What's the Goal Again?

We want to compute how many ways we can **build `t[0..j-1]` from `s[0..i-1]`** — that is:

> How many subsequences of the first `i` characters of `s` can form the first `j` characters of `t`?

---

### 🔍 If `s[i-1] == t[j-1]` — characters match

This is the **key case** — when the current characters in `s` and `t` **match**, we have **2 choices**:

```python
dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
```

Let’s explain both parts:

---

### ✅ Option 1: **Use the matching character**

* Since `s[i-1] == t[j-1]`, we can match them.
* So we ask: **How many ways can I match `t[0..j-2]` with `s[0..i-2]`?**
* That's `dp[i-1][j-1]`.

💡 In simple words:

> "If I'm using this `s[i-1]` to match `t[j-1]`, then the previous part of `t` must match the previous part of `s`."

✔️ This is the **“use it”** part:
`dp[i-1][j-1]`

---

### ❌ Option 2: **Skip the current character in `s`**

* Maybe you don't want to use `s[i-1]`, even if it matches.
* So instead, you just **ignore it**, and try to form `t[0..j-1]` from `s[0..i-2]`.
* That's `dp[i-1][j]`.

💡 In simple words:

> "Let's pretend `s[i-1]` doesn't exist, and see how many ways we could already match `t` without it."

✔️ This is the **“skip it”** part:
`dp[i-1][j]`

---

### 📦 Combined:

So if `s[i-1] == t[j-1]`, we add both options:

```python
dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
```

---

### 📊 Visual Example

Let’s say:

* `s = "babgbag"`
* `t = "bag"`

Let’s pick:

* `i = 4` (s\[0..3] = "babg")
* `j = 2` (t\[0..1] = "ba")

Now, `s[3] = 'g'`, `t[1] = 'a'` → they **don’t match** → so we just skip:

```python
dp[4][2] = dp[3][2]
```

BUT...

At `i = 5` (s\[4] = 'b'), and `j = 2` (t\[1] = 'a') → they **don’t match** again

Still just skip.

Now at `i = 6` (s\[5] = 'a'), and `j = 2` (t\[1] = 'a') → they **match!**

✅ So:

```python
dp[6][2] = dp[5][1] + dp[5][2]
```

* `dp[5][1]`: use the match
* `dp[5][2]`: skip it

We add both results, because **both are valid choices** for building `t[0..1]` from `s[0..5]`.

---

### 🔁 If characters **don’t match**?

Then we **can't** use the character from `s`. So we just skip it:

```python
dp[i][j] = dp[i-1][j]
```

---

### 🧩 Final Takeaway

Whenever characters match:

* You have 2 options → use or skip
* So total ways = both possibilities added up

This is a core DP principle in many string problems:
**“Try both paths, and combine results.”**

---

Would you like a simple tracing diagram to walk through an actual value with real characters?

 */
/*
Sure! Here's the **Java code** that solves the problem of counting the number of distinct subsequences of `s` that equal `t` using **Dynamic Programming**:

---

### ✅ Java Code (2D DP version):

```java
public class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        // Edge case: If t is longer than s, there can't be any subsequences
        if (n > m) return 0;

        // dp[i][j] = number of distinct subsequences of s[0..i-1] which equals t[0..j-1]
        int[][] dp = new int[m + 1][n + 1];

        // Base case: An empty t ("") can be matched by any prefix of s in exactly 1 way
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    // Use or skip s.charAt(i - 1)
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                } else {
                    // Skip s.charAt(i - 1)
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[m][n];
    }
}
```

---

### 📌 Example Usage:

```java
public class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();

        String s1 = "rabbbit";
        String t1 = "rabbit";
        System.out.println(sol.numDistinct(s1, t1)); // Output: 3

        String s2 = "babgbag";
        String t2 = "bag";
        System.out.println(sol.numDistinct(s2, t2)); // Output: 5
    }
}
```

---

### 💡 Time & Space Complexity:

* **Time**: `O(m * n)` — where `m = s.length()`, `n = t.length()`
* **Space**: `O(m * n)` — can be optimized to `O(n)` using a 1D array (let me know if you want that)

Let me know if you'd like comments on every line or the optimized version with 1D DP.

 */
/*
Great! Let’s walk through a **visual hands-on example** with a **diagram** to really solidify how this works and why we use **Dynamic Programming (DP)** for it.

---

### 🔍 Problem Recap:

**Input:**

```plaintext
s = "rabbbit"
t = "rabbit"
```

We want to know: **How many different ways can we form `"rabbit"` from `"rabbbit"`** by **deleting characters without reordering**?

---

### 👣 Step-by-Step Example:

We’ll walk through just the beginning to keep it clear.

---

#### 📦 Step 1: Think of a Grid

We create a table where:

* Rows represent characters from `s` ("rabbbit"), including an empty start
* Columns represent characters from `t` ("rabbit"), including an empty start

So we build a grid of size `(len(s)+1) x (len(t)+1)` — in this case 8 x 7.

The top-left corner starts with both strings empty.

We fill the table so that:

* Each cell tells us **how many ways** we can match the first few characters of `t` using the first few characters of `s`.

---

#### 🧱 Step 2: Base Case

* When `t` is empty (i.e., column 0), there's always **1 way** to match: **delete everything** from `s`.
* When `s` is empty (i.e., row 0) but `t` is not, there are **0 ways** to form `t`.

Here's how the base of the grid starts:

```
      ""  r  a  b  b  i  t  <- t
   +-------------------------
"" |  1  0  0  0  0  0  0
 r |
 a |
 b |
 b |
 b |
 i |
 t |
```

---

#### 🔄 Step 3: Fill in the Table (Just a Few Steps)

Now we loop through `s` and `t`, comparing characters:

If `s[i-1] == t[j-1]`:

```python
dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
```

* Use it (`dp[i-1][j-1]`)
* Or skip it (`dp[i-1][j]`)

If they don't match:

```python
dp[i][j] = dp[i-1][j]
```

* Just skip `s[i-1]`

---

### 🧮 Real Example with Matching:

Let's look at part of it. For the first few rows:

```
      ""  r  a  b  b  i  t
   +-----------------------
"" |  1  0  0  0  0  0  0
 r |  1  1  0  0  0  0  0
 a |  1  1  1  0  0  0  0
 b |  1  1  1  1  0  0  0
 b |  1  1  1  2  1  0  0
 b |  1  1  1  3  3  0  0
 i |  1  1  1  3  3  3  0
 t |  1  1  1  3  3  3  3
```

Final cell (`dp[7][6]`) = **3** → Answer ✅

---

### 🧠 Why DP Makes It Easy

Instead of rechecking all combinations of characters in `s`, you build the answer from smaller parts.

You're saying:

* "If I know how many ways I can build `"rabb"` from `"rabbbi"`, then I can figure out how many ways I can build `"rabbit"` from `"rabbbit"`."

This **build-up approach** is classic DP.

---

### 💡 TL;DR Summary

* Matching strings with subsequences often involves **choices** (match or skip).
* Those choices repeat in patterns → so we **save the answers** using DP.
* It's like filling in a crossword puzzle where each clue depends on clues you've already solved.

Would you like a little interactive version or a worksheet-style practice?

 */
/*
That's a great question — and yes, you're thinking in the right direction! Let's break this down for a **beginner brain** to understand clearly:

---

### 🧠 **First, what's happening here?**

You are given two strings:

* `s` → the **source string** (like a big pool of letters)
* `t` → the **target string** (what you're trying to build using letters from `s`, without rearranging them)

You're asked:

> "In how many **ways** can you pick letters **in order** from `s` to form `t`?"

This is called a **subsequence** — it's like copying and deleting characters, **but never changing their order**.

---

### 🧩 Why is **Dynamic Programming (DP)** used?

DP is like solving a big problem by breaking it into **smaller, reusable problems**.

In this case:

* You want to know how many ways you can form `t` from `s`
* But instead of checking all combinations manually (which would be very slow), DP stores answers to smaller subproblems so you can reuse them

Think of it like **filling out a grid** (a table), where:

* Rows represent letters of `s`
* Columns represent letters of `t`
* Each cell tells you: “How many ways can I form part of `t` using part of `s`?”

---

### 🎯 Is DP a "rule of thumb" for string and pattern problems?

✅ Yes — **many pattern-matching problems use DP**.

You use DP when:

* The problem asks **"how many ways"** or **"minimum steps"** to match strings
* The order of characters matters (like subsequences or substrings)
* You need to **reuse** solutions to smaller parts (e.g., matching half of `t` with part of `s`)

Some classic problems that use DP in string matching:

| Problem Type                     | DP Use Case                                                                  |
| -------------------------------- | ---------------------------------------------------------------------------- |
| Subsequence Counting (like this) | Count how many ways to form `t` from `s`                                     |
| Edit Distance                    | Find how many edits (insert, delete, etc.) to change one string into another |
| Longest Common Subsequence       | Find the longest sequence that appears in both strings                       |
| Regular Expression Matching      | Match patterns with `*`, `.` etc.                                            |

---

### 🪜 Final Tip: When you're stuck, ask:

* **Can I break this problem into smaller, similar problems?**
* **Does the order of characters matter?**
* **Am I trying to count ways or find the best/shortest/longest match?**

If yes — **DP is probably your friend**.

Let me know if you want a drawing/diagram or a hands-on visual example with real strings!

 */