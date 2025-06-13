package data.structures.algorithms.hard.leetCode;

import java.util.ArrayList;
import java.util.List;

public class N60PermutationSequence {
    public static void main(String[] args) {
        N60PermutationSequence sol = new N60PermutationSequence();
        System.out.println(sol.getPermutation(4, 9));
    }

    public String getPermutation(int n, int k) {
        List<Integer> numbers = new ArrayList<>();
        int[] fact = new int[n];
        StringBuilder result = new StringBuilder();

        // initialize numbers and factorials
        fact[0] = 1;
        for (int i = 1; i < n; i++) {
            fact[i] = fact[i - 1] * i;
        }

        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        k--; // convert to 0-based index

        for (int i = n; i >= 1; i--) {
            int idx = k / fact[i - 1];
            result.append(numbers.get(idx));
            numbers.remove(idx);
            k %= fact[i - 1];
        }

        return result.toString();
    }
}
/*
The set [1, 2, 3, ..., n] contains a total of n! unique permutations.
By listing and labeling all of the permutations in order, we get the following sequence for n = 3:
"123"
"132"
"213"
"231"
"312"
"321"
Given n and k, return the kth permutation sequence.

Example 1:
Input: n = 3, k = 3
Output: "213"
Example 2:
Input: n = 4, k = 9
Output: "2314"
Example 3:
Input: n = 3, k = 1
Output: "123"

Constraints:
1 <= n <= 9
1 <= k <= n!
 */
/*
Great question! Let's **break it down step-by-step** for a beginner to understand
how to find the **k-th permutation sequence** of numbers from `1` to `n`.
---
## 🔢 Problem Summary
Given:
* A number `n` → means you're working with the numbers `[1, 2, 3, ..., n]`
* A number `k` → means you want the **k-th permutation** in the list of all permutations (ordered **lexicographically**, like a dictionary)
Your goal: Return the **k-th permutation** as a string.
---
## ✨ Example to Understand
Let’s take `n = 3`. The numbers are `[1, 2, 3]`
All **3! = 6** permutations are:
1. `"123"`
2. `"132"`
3. `"213"` ✅
4. `"231"`
5. `"312"`
6. `"321"`
If `k = 3`, the answer is `"213"`.
---
## 🧠 Logical Way to Solve (without generating all permutations)
Here’s the trick:
We don’t need to **generate all permutations**. We can calculate **directly** where each digit should go by using **factorials**.
---
### 📌 Key Idea: Use Factorials
At each position:
* There are `(n-1)!` permutations that start with the same first number.
* So we can figure out what the first number should be based on `k`.
---
## ✅ Step-by-step for `n = 4`, `k = 9`
We want the 9th permutation of `[1, 2, 3, 4]`.
Total permutations = 4! = 24
1. Fix the first number:
   * Each number at the first position has **(4-1)! = 6** permutations.
   * So:
     * `1` → permutations 1–6
     * `2` → permutations 7–12 ✅
   * So the first digit is `2`
   * Subtract 6 from `k`: `k = 9 - 6 = 3`
   * Remove 2 from the list → remaining: `[1, 3, 4]`
2. Fix the second number:
   * Now each starting with `2` has `(3-1)! = 2` permutations
   * `1` → permutations 1–2
   * `3` → permutations 3–4 ✅
   * So second digit is `3`
   * Subtract 2: `k = 3 - 2 = 1`
   * Remaining: `[1, 4]`
3. Fix the third number:
   * Now each has (2-1)! = 1 permutation
   * `1` → permutation 1 ✅
   * Remaining: `[4]`
Answer = **"2314"**
---
## 🧮 Time Complexity:
* **O(n²)** because we remove from a list in a loop.
* No need to generate all permutations!
---
## ✅ Summary for Beginners
* You don't need to list all permutations.
* Use **math (factorials)** to calculate which number goes in each position.
* Adjust `k` as you build the answer step-by-step.
---
 */
/*
Great! Let's explain this part of the code in a **very simple, beginner-friendly way**. This is the **core logic** that builds the k-th permutation.

---

### 👀 What the Code Does:

```java
for (int i = n; i >= 1; i--) {
    int idx = k / fact[i - 1];
    result.append(numbers.get(idx));
    numbers.remove(idx);
    k %= fact[i - 1];
}
```

This loop runs from `i = n` down to `1`, and **picks one digit at a time** to build the k-th permutation.

Let’s go step-by-step.

---

## 🧠 The Idea:

You want to build the k-th permutation by **figuring out** what number should go in each position **without generating all permutations**.

---

## 🧱 Variables

* `numbers`: List of the available numbers to use, like `[1, 2, 3, 4]`
* `fact[i - 1]`: Number of permutations possible with `(i - 1)` remaining digits
* `k`: The index of the permutation (zero-based)

---

## 🔄 Step-by-step Explanation of Each Line

### 🔁 Loop: `for (int i = n; i >= 1; i--)`

You are choosing the digit for each position, starting from the first to the last.

---

### 🔢 Line: `int idx = k / fact[i - 1];`

**What this does:**

* Finds **which digit** to use from the `numbers` list at the current position.
* Every digit has `(i-1)!` permutations that begin with it.
* So we divide `k` by `(i-1)!` to find which "bucket" we're in.

**Example:**

If `fact[i-1] = 6`, and `k = 14`,
→ `idx = 14 / 6 = 2`
→ So pick the **3rd number** from the list (index `2`).

---

### 🧱 Line: `result.append(numbers.get(idx));`

* Adds that chosen digit to your result.
* For example, if the number at index 2 is `3`, then `result = "3"`.

---

### ❌ Line: `numbers.remove(idx);`

* Once you've used a number, **remove it** so it’s not reused.

---

### 🔁 Line: `k %= fact[i - 1];`

* Update `k` to reflect the **remaining permutations**.
* You’re moving to the **next smaller block** of permutations.
* For example, if `k = 14` and `fact[i-1] = 6`:
  → `k = 14 % 6 = 2`

So for the next position, you want the **2nd permutation** in the smaller set.

---

## 🔚 At the End

You will have built the permutation digit-by-digit in `result`.

---

### 👇 Example Recap (for `n = 4`, `k = 9`):

* Initial `numbers = [1, 2, 3, 4]`, and `k = 8` (since we convert to 0-based)

Step-by-step:

```
i = 4 → fact[3] = 6 → idx = 8 / 6 = 1 → pick numbers[1] = 2
i = 3 → fact[2] = 2 → k = 2 → idx = 2 / 2 = 1 → pick 3
i = 2 → fact[1] = 1 → k = 0 → idx = 0 → pick 1
i = 1 → pick last number → 4
```

Final permutation = `"2314"`

---

## ✅ Summary

| Line                     | What it does                                       |
| ------------------------ | -------------------------------------------------- |
| `idx = k / fact[i - 1];` | Chooses the right digit index                      |
| `result.append(...)`     | Adds that digit to the answer                      |
| `numbers.remove(idx);`   | Removes used digit so it's not repeated            |
| `k %= fact[i - 1];`      | Updates k to move to the next sub-block of choices |

---

Let me know if you want this explained with a visual or with real values walking through each iteration.

 */