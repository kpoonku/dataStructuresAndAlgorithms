package data.structures.algorithms.hard.leetCode;

public class N132PalindromePartition {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = i; // maximum cuts = i (e.g. "a|b|c|...")
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || isPalindrome[j + 1][i - 1])) {
                    isPalindrome[j][i] = true;
                    if (j == 0) {
                        dp[i] = 0; // whole substring is a palindrome
                    } else {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }
        return dp[n - 1];
    }
}
/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.



Example 1:

Input: s = "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
Example 2:

Input: s = "a"
Output: 0
Example 3:

Input: s = "ab"
Output: 1


Constraints:

1 <= s.length <= 2000
s consists of lowercase English letters only.
 */
/*
Great question! The line:

```java
if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || isPalindrome[j + 1][i - 1]))
```

is used to **check if the substring `s[j..i]` is a palindrome**, efficiently.

---

### 🔍 Let's break it down:

#### 🧩 Part 1: `s.charAt(i) == s.charAt(j)`

* A basic condition for a palindrome: the **first and last characters must match**.

#### 🧩 Part 2: `(i - j <= 2 || isPalindrome[j + 1][i - 1])`

* This checks if the **middle part** of the substring is a palindrome.

##### ✅ Case 1: `i - j <= 2`

* This means the substring `s[j..i]` is:

  * 1 character (`"a"`), or
  * 2 characters (`"aa"`), or
  * 3 characters (`"aba"`)

In all these cases:

* If the outer characters match, we **don't need to check the middle** (it's either one char or already small enough to assume or check trivially).

##### ✅ Case 2: `isPalindrome[j + 1][i - 1]`

* For longer substrings (length > 3), we rely on previously computed results:

  * If the substring inside (`s[j+1..i-1]`) is a palindrome, and
  * The outer characters match (`s[j] == s[i]`), then the whole substring `s[j..i]` is a palindrome.

---

### ✅ Combined Meaning:

> This condition checks whether `s[j..i]` is a palindrome **based on the outer characters and the inner substring**.

---

### 🔁 Example:

Let’s take `s = "aab"` and `i = 1`, `j = 0` → checking `"aa"`:

* `s.charAt(0) == s.charAt(1)` → `'a' == 'a'`
* `i - j = 1` → which is `<= 2`
* ✅ So, `"aa"` is a palindrome.

---

### 🔁 Another Example:

`s = "abca"`, checking `"abcba"`:

* Let's say `i = 4`, `j = 0` → checking `"abcba"`
* `s.charAt(0) == s.charAt(4)` → `'a' == 'a'`
* `i - j = 4` → so check `isPalindrome[1][3]` which is `"bcb"`
* If `"bcb"` is already marked as a palindrome, then `"abcba"` is also a palindrome

---

Let me know if you want this concept visualized step-by-step with an example string!

 */
/*
public int minCut(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = i; // maximum cuts = i (e.g. "a|b|c|...")
            for (int j = 0; j <= i; j++) {
                if (s.charAt(i) == s.charAt(j) && (i - j <= 2 || isPalindrome[j + 1][i - 1])) {
                    isPalindrome[j][i] = true;
                    if (j == 0) {
                        dp[i] = 0; // whole substring is a palindrome
                    } else {
                        dp[i] = Math.min(dp[i], dp[j - 1] + 1);
                    }
                }
            }
        }

        return dp[n - 1];
    }
 */
/*
Absolutely! This Java function calculates the **minimum number of cuts** needed to partition a string `s` such that **every substring in the partition is a palindrome**.

Let’s break it down step by step:

---

### 🧠 Goal:

Given a string `s`, return the **minimum number of cuts** required to make **every substring a palindrome**.

---

### 🧩 Step-by-Step Code Explanation:

#### ✅ Step 1: Setup

```java
int n = s.length();
boolean[][] isPalindrome = new boolean[n][n];
int[] dp = new int[n];
```

* `n` is the length of the string.
* `isPalindrome[i][j]` will be `true` if `s[i..j]` is a palindrome.
* `dp[i]` stores the **minimum cuts needed for substring `s[0..i]`**.

---

#### ✅ Step 2: Initialize maximum cuts

```java
dp[i] = i;
```

* In the **worst case**, each character is a separate palindrome (e.g., "a|b|c|...").
* So the max cuts for `s[0..i]` = `i`.

---

#### ✅ Step 3: Check all substrings `s[j..i]` for being palindromes

```java
for (int i = 0; i < n; i++) {
    for (int j = 0; j <= i; j++) {
        if (s.charAt(i) == s.charAt(j) &&
            (i - j <= 2 || isPalindrome[j + 1][i - 1])) {
```

This condition checks whether the substring `s[j..i]` is a **palindrome**:

* Characters at the ends must match.
* The substring inside (`s[j+1..i-1]`) must also be a palindrome **unless** the current substring is small (length ≤ 3), in which case it's trivially a palindrome if the ends match.

---

#### ✅ Step 4: Update the `isPalindrome` table

```java
isPalindrome[j][i] = true;
```

* Record that `s[j..i]` is a palindrome to use later.

---

#### ✅ Step 5: Update minimum cuts in `dp[i]`

```java
if (j == 0) {
    dp[i] = 0;
} else {
    dp[i] = Math.min(dp[i], dp[j - 1] + 1);
}
```

* If the whole substring `s[0..i]` is a palindrome (i.e., `j == 0`), **no cut needed** → `dp[i] = 0`.
* Otherwise, make a cut before `j`, and the remaining substring `s[j..i]` is a palindrome:

  * So we update: `dp[i] = min(dp[i], dp[j - 1] + 1)`

---

### 🔁 Example: `s = "aab"`

1. `i = 0`: `s[0..0] = "a"` → palindrome → `dp[0] = 0`
2. `i = 1`: `s[0..1] = "aa"` → palindrome → `dp[1] = 0`
3. `i = 2`:

   * `s[1..2] = "ab"` → not palindrome
   * `s[2..2] = "b"` → palindrome → cut before `2` → `dp[2] = dp[1] + 1 = 1`

✅ Final output: `dp[2] = 1`

---

### 📦 Return Result

```java
return dp[n - 1];
```

* Return the **minimum number of cuts** for the entire string `s`.

---

### ✅ Time and Space Complexity:

* **Time**: `O(n^2)` — Nested loops over the string length.
* **Space**: `O(n^2)` for `isPalindrome`, `O(n)` for `dp`.

---

Would you like a visual walkthrough with an example string?

 */