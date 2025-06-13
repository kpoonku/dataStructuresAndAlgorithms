package data.structures.algorithms;

public class WhatisWhat {

/*
Dynamic Programing:

Sure! Let's break down **Dynamic Programming (DP)** in a very simple, beginner-friendly way.

---

## 🤔 What is Dynamic Programming?

**Dynamic Programming (DP)** is a method for solving problems by **breaking them into smaller subproblems**, solving each of them **once**, and **saving** their answers to avoid repeating work.

It's great for problems with:

* **Overlapping subproblems** (you solve the same problem again and again)
* **Optimal substructure** (the solution depends on solutions to smaller parts)

---

## 🧠 Real-life Analogy

Think of doing **homework** where:

* You keep solving the same math formula again and again.
* Instead of redoing it every time, you **write the answer down** in a notebook.
* Next time, you **look it up** instead of redoing it — that’s dynamic programming!

---

## ✅ A Classic Beginner Example: Fibonacci Numbers

Fibonacci sequence:

```
Fib(0) = 0
Fib(1) = 1
Fib(n) = Fib(n-1) + Fib(n-2)
```

If we calculate Fib(5) without DP, it looks like this:

```
Fib(5)
├─ Fib(4)
│  ├─ Fib(3)
│  │  ├─ Fib(2)
│  │  │  ├─ Fib(1)
│  │  │  └─ Fib(0)
...
```

We repeat the same work! 😫

### Solution with DP:

We **store answers** as we go:

```java
int[] dp = new int[n + 1];
dp[0] = 0;
dp[1] = 1;

for (int i = 2; i <= n; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
}
```

Now we only calculate each value **once**! 🎉

---

## 🧩 Back to Regular Expression Matching

In the regex problem:

We are checking:

* Does `s.substring(0, i)` match `p.substring(0, j)`?

We store the result in a 2D table:

```
dp[i][j] = true if first i letters of s match first j letters of p
```

Instead of recalculating the match again and again, we store results in this table.

---

## 📊 Visual Example

Let’s say:

```java
s = "aa"
p = "a*"
```

We create a table:

| i\j | "" | a | \* |
| --- | -- | - | -- |
| ""  | T  | F | T  |
| a   | F  | T | T  |
| aa  | F  | F | T  |

* `dp[0][0] = true` (empty string matches empty pattern)
* `dp[1][1] = true` ("a" matches "a")
* `dp[2][2] = true` ("aa" matches "a\*")

---

## 💡 Key Tips to Learn DP

1. **Think small:** Start with small inputs (like 1 or 2 characters).
2. **Draw a table:** Helps visualize what’s being solved.
3. **Ask:** “If I know how to solve smaller versions, can I use them to solve the bigger version?”
4. **Store answers:** Use arrays or tables to save the results.

---

## ✅ Summary

* **Dynamic Programming** avoids repeating the same work.
* You **solve once**, **save**, and **reuse**.
* It's used in regex matching to store match results in a 2D table.

---
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
}
