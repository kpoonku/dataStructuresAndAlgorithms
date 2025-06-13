package data.structures.algorithms.hard.leetCode;

public class N10RegularExpressionMatching {
    public static void main(String[] args) {
        N10RegularExpressionMatching regex = new N10RegularExpressionMatching();
        /*System.out.println(regex.isMatch("aa", "a"));    // false
        System.out.println(regex.isMatch("aa", "a*"));   // true
        System.out.println(regex.isMatch("ab", ".*"));
        System.out.println(regex.isMatch("", "*"));*/
        System.out.println(regex.isMatch("", "a.b*"));
        System.out.println(regex.isMatch("aa", "a.b*"));
    }

    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // dp[i][j] = true if s[0..i-1] matches p[0..j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Empty string matches empty pattern
        dp[0][0] = true;

        // Fill in dp for pattern like a*, a*b*, etc.
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*' && j >= 2) {
                // Zero occurrence of the char before '*'
                dp[0][j] = dp[0][j - 2];
            }
        }

        // Build the table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '.' || pc == sc) {
                    /* If the current characters match,
                       then the result for dp[i][j] (string up to i, pattern up to j)
                       is the same as the result for dp[i-1][j-1].
                    */
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    // Zero occurrences of char before '*'
                    dp[i][j] = dp[i][j - 2];

                    // One or more if match
                    char beforeStar = p.charAt(j - 2);
                    if (beforeStar == '.' || beforeStar == sc) {
                        dp[i][j] |= dp[i - 1][j]; // or dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}
/*
Given an input string s and a pattern p, implement regular expression matching with support for '.' and '\*' where:

'.' Matches any single character.
'\*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Example 1:

Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".
Example 2:

Input: s = "aa", p = "a\*"
Output: true
Explanation: '\*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
Example 3:

Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (\*) of any character (.)".

Constraints:

1 <= s.length <= 20
1 <= p.length <= 20
s contains only lowercase English letters.
p contains only lowercase English letters, '.', and '*'.
It is guaranteed for each appearance of the character '*', there will be a previous valid character to match. - beginner level

 */
/*
Let's solve this **regular expression matching** problem **step-by-step at a beginner level**. We’ll write a solution that can handle `.` and `*` and **match the full string**.

---

## ✅ Problem Recap

We’re given:

* A **string** `s` (like `"aa"`)
* A **pattern** `p` (like `"a*"`)

### The pattern can include:

* `.` → Matches any one character
* `*` → Matches **0 or more of the previous character**

---

## 🧠 Goal

We want to write a function `isMatch(s, p)` that returns:

* `true` if the pattern matches the whole string
* `false` otherwise

---

## 🔍 Examples

| `s`  | `p`   | Output | Reason                                  |
| ---- | ----- | ------ | --------------------------------------- |
| "aa" | "a"   | false  | Only matches one 'a'                    |
| "aa" | "a\*" | true   | '\*' means repeat 'a' 0 or more times   |
| "ab" | ".\*" | true   | '.' matches anything, '\*' means repeat |

---

## 🧰 Approach

We’ll use **Dynamic Programming (DP)** to break the problem down.

### Let’s define:

* `dp[i][j] = true` if `s[0..i-1]` matches `p[0..j-1]`

We build a 2D array `dp` where:

* `i` goes from `0` to `s.length`
* `j` goes from `0` to `p.length`

### Key Rules:

1. If characters match or `p[j-1] == '.'`:

   * `dp[i][j] = dp[i-1][j-1]`
2. If `p[j-1] == '*'`, we check:

   * Zero occurrences: `dp[i][j] = dp[i][j-2]`
   * Or one/more: if `s[i-1] == p[j-2]` or `p[j-2] == '.'`, then:
     `dp[i][j] |= dp[i-1][j]`

---

## ✅ Java Code (Beginner Friendly)

```java
public class RegexMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // dp[i][j] = true if s[0..i-1] matches p[0..j-1]
        boolean[][] dp = new boolean[m + 1][n + 1];

        // Empty string matches empty pattern
        dp[0][0] = true;

        // Fill in dp for pattern like a*, a*b*, etc.
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                // Zero occurrence of the char before '*'
                dp[0][j] = dp[0][j - 2];
            }
        }

        // Build the table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    // Zero occurrences of char before '*'
                    dp[i][j] = dp[i][j - 2];

                    // One or more if match
                    char beforeStar = p.charAt(j - 2);
                    if (beforeStar == '.' || beforeStar == sc) {
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}
```

---

## ✅ Usage Example:

```java
public class Main {
    public static void main(String[] args) {
        RegexMatching regex = new RegexMatching();

        System.out.println(regex.isMatch("aa", "a"));    // false
        System.out.println(regex.isMatch("aa", "a*"));   // true
        System.out.println(regex.isMatch("ab", ".*"));   // true
        System.out.println(regex.isMatch("mississippi", "mis*is*p*.")); // false
    }
}
```

---

## 💡 Summary

* We use **dynamic programming** to break the problem into smaller parts.
* `*` can mean "zero or more" of the previous character.
* `.` matches any single character.
* Time complexity: **O(m × n)**

Would you like this code broken down line-by-line with visual explanation (like a table of `dp` values)?

 */
/*

if (pc == '.' || pc == sc) {
dp\[i]\[j] = dp\[i - 1]\[j - 1];
} - explain this ?


Absolutely! Let's break down this piece of code **step by step** at a **beginner-friendly level**:

---

### 🔍 Code:

```java
if (pc == '.' || pc == sc) {
    dp[i][j] = dp[i - 1][j - 1];
}
```

---

### 🧠 What's going on here?

You're inside a **nested loop**, trying to fill the dynamic programming table `dp`.

You're checking if:

* The current character in the **pattern** matches the current character in the **string**.

---

### 💡 Variables:

* `sc = s.charAt(i - 1)` → current character in the **string**
* `pc = p.charAt(j - 1)` → current character in the **pattern**

So you're comparing:

* the `i`-th letter of the **string**
* with the `j`-th letter of the **pattern**

---

### ✅ Condition:

```java
if (pc == '.' || pc == sc)
```

This means:

* If the pattern character is a `.` → that matches **any** single character
* OR
* If the pattern character is exactly equal to the string character (`pc == sc`)

Then they **match**!

---

### 👉 Then What?

```java
dp[i][j] = dp[i - 1][j - 1];
```

This means:

> If the current characters match,
> then the result for `dp[i][j]` (string up to i, pattern up to j)
> is the same as the result for `dp[i-1][j-1]`.

### 📦 Example:

String: `"a"`
Pattern: `"a"`

* `s[0] = 'a'`
* `p[0] = 'a'`

→ `'a'` matches `'a'`, so:

```java
dp[1][1] = dp[0][0]
```

Which means:

> "If everything before these characters matched,
> and these two match as well, then we're still good!"

---

## 🔁 Analogy:

Imagine you're walking through both strings step-by-step:

* If the current letters are the **same**, or the pattern has `.`, you **take one step forward in both**.

And your matching status now (`dp[i][j]`) just depends on whether **everything before also matched**.

---

## ✅ Summary

This line:

```java
if (pc == '.' || pc == sc) {
    dp[i][j] = dp[i - 1][j - 1];
}
```

Means:

> If the characters match (or the pattern has `.`),
> then our match status so far is the same as the match without those two characters.

This is how we **build up** the full match from smaller pieces — step by step.

---

Would you like a visual table example showing how this works with `"ab"` and `"a."`?

 */

/*

if (pc == '*') {
// Zero occurrences of char before '*'
dp\[i]\[j] = dp\[i]\[j - 2];

```
                // One or more if match
                char beforeStar = p.charAt(j - 2);
                if (beforeStar == '.' || beforeStar == sc) {
                    dp[i][j] |= dp[i - 1][j];
                }
            } - ??
```

Great! Let's explain this part **step-by-step in a beginner-friendly way**, so you understand exactly how the `*`
is handled in regex pattern matching.

---

## 🔍 Code in Focus:

```java
if (pc == '*') {
    // Zero occurrences of char before '*'
    dp[i][j] = dp[i][j - 2];

    // One or more occurrences if match
    char beforeStar = p.charAt(j - 2);
    if (beforeStar == '.' || beforeStar == sc) {
        dp[i][j] |= dp[i - 1][j];
    }
}
```

---

## ✅ Goal:

We're trying to **fill in the `dp[i][j]` cell**, which means:

> Can `s[0...i-1]` match `p[0...j-1]`?

Here, we're handling the case where the current **pattern character** is `'*'`.

---

## 🧠 Reminder: What does `'*'` mean?

In regex:

* `*` means "**zero or more of the previous character**"

  * Example: `"a*"` means `"", "a", "aa", "aaa"`, etc.

But `*` **always refers to the character just before it**, like:

* In `"a*"`, the `*` refers to `'a'`
* In `".*"`, the `*` refers to the `.` (which matches anything)

---

## ✨ Breaking it Down

### 🔹 1. Zero Occurrences of the Character Before `*`

```java
dp[i][j] = dp[i][j - 2];
```

This means:

> “Can I **ignore** the `*` and the character before it?”

* `j-2` skips both `*` and the character before it
* For example, in `"a*b"`, `a*` can be **ignored** if zero `'a'`s are needed

### 🧠 Example:

Pattern: `"a*"`
String: `""`

We check `dp[0][2] = dp[0][0]`
✅ Because we're saying: "Try matching with no `'a'` at all"

---

### 🔹 2. One or More Occurrences

```java
char beforeStar = p.charAt(j - 2);
if (beforeStar == '.' || beforeStar == sc) {
    dp[i][j] |= dp[i - 1][j];
}
```

This means:

> "If the **current character in the string** matches the character before the `*`,
> then the `*` could be matching **this character** too — so check if previous string part matched."

* `dp[i - 1][j]` means:

  * "Does the string up to `i-1` match the pattern up to `j`?"
  * If so, and current char matches, then full string up to `i` also matches!

### 🧠 Example:

String: `"aa"`
Pattern: `"a*"`

When `i = 2`, `j = 2`, we check:

* Does the second `'a'` match `'a'` (before `*`)? ✅
* If yes, check `dp[1][2]` → does first `'a'` match `"a*"`? ✅
* So `dp[2][2] = true`

---

### 🔧 What does `|= dp[i - 1][j]` mean?

This is the **"or equals"** operator:

```java
dp[i][j] = dp[i][j] || dp[i - 1][j];
```

So if **either case is true**, the whole thing is true.

---

## 📦 Summary

When `pc == '*'`:

| Case                    | Meaning                                                | Code                       |   |                                |                       |
| ----------------------- | ------------------------------------------------------ | -------------------------- | - | ------------------------------ | --------------------- |
| Zero occurrences        | Try ignoring `*` and char before it                    | `dp[i][j] = dp[i][j - 2];` |   |                                |                       |
| One or more occurrences | If current string char matches, continue using the `*` | \`if (beforeStar == '.'    |   | beforeStar == sc) { dp\[i]\[j] | = dp\[i - 1]\[j]; }\` |

---

## 🎓 Final Analogy

Imagine `*` as a repeat button:

* **You can skip it** entirely (0 times)
* Or **keep using it** to match more characters (1 or more times)

This code handles **both situations** carefully.

---

Would you like to see a full visual table (`dp`) example using `"aab"` and `"c*a*b"`?

 */
