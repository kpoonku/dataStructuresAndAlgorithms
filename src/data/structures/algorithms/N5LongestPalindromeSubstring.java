package data.structures.algorithms;

public class N5LongestPalindromeSubstring {
    public static void main(String[] args) {
        N5LongestPalindromeSubstring sol = new N5LongestPalindromeSubstring();
       // System.out.println("Output : " + sol.longestPalindrome("babad"));
       // System.out.println("Output : " + sol.longestPalindrome("cbbd"));
        System.out.println("Output : " + sol.longestPalindrome("fcbababcf"));
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int maxLen = 1;
        int start = 0;

        // Base case 1: single letters
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // Base case 2: double letters
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                start = i;
                maxLen = 2;
            }
        }

        // Substrings of length 3 or more
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    start = i;
                    maxLen = len;
                }
            }
        }

        return s.substring(start, start + maxLen);
    }
}
/*
Given a string s, return the longest palindromic substring in s.



Example 1:

Input: s = "babad"
Output: "bab"
Explanation: "aba" is also a valid answer.
Example 2:

Input: s = "cbbd"
Output: "bb"


Constraints:

1 <= s.length <= 1000
s consist of only digits and English letters.
 */
/*
Absolutely! Let's walk through the DP matrix with a simple example so you can
**visualize** how it's being filled step by step.

---

## 🧪 Example String: `"babad"`

Length: 5
Indices: `0 1 2 3 4`
Characters: `'b' 'a' 'b' 'a' 'd'`

---

## 🧩 Step 1: Initialize `dp[i][i] = true` (single letters)

All single characters are palindromes by default.

```
Initial DP matrix (diagonal filled with true):

  0   1   2   3   4
+---+---+---+---+---+
| T |   |   |   |   |  0 (b)
|   | T |   |   |   |  1 (a)
|   |   | T |   |   |  2 (b)
|   |   |   | T |   |  3 (a)
|   |   |   |   | T |  4 (d)
```

---

## 🧩 Step 2: Check for substrings of length 2

If two adjacent characters are the same → mark as true.

- `"ba"` → b ≠ a → false
- `"ab"` → a ≠ b → false
- `"ba"` → b ≠ a → false
- `"ad"` → a ≠ d → false

No changes this round.

---

## 🧩 Step 3: Substrings of length ≥ 3 (using your condition)

Now use:
```java
if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1])
```

Let’s look at one case:
- i = 0, j = 2 → `"bab"`
  - s[0] == s[2] ('b' == 'b') ✅
  - dp[1][1] == true ✅ (inner "a")
  - So dp[0][2] = true

Another one:
- i = 1, j = 3 → `"aba"`
  - s[1] == s[3] ('a' == 'a') ✅
  - dp[2][2] == true ✅ ("b")
  - So dp[1][3] = true

---

### ✅ Final DP Matrix:

```
    0   1   2   3   4
  +---+---+---+---+---+
0 | T |   | T |   |   |
1 |   | T |   | T |   |
2 |   |   | T |   |   |
3 |   |   |   | T |   |
4 |   |   |   |   | T |
```

---

### 🔍 Meaning:

- `dp[0][2] = true` → "bab" is a palindrome
- `dp[1][3] = true` → "aba" is a palindrome
- Diagonal (`dp[i][i]`) is always true (single letters)

---

Let me know if you want this shown as a table with the **actual substrings**
inside instead of just T/F values!
 */