package data.structures.algorithms.hard.leetCode;

import java.util.Stack;

public class N32LongestValidParentheses {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int maxLen = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else {
                int index = stack.pop();

                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }


        return 0;
    }
}
/*
To solve the problem of finding the **length of the longest valid (well-formed) parentheses substring**, we need to identify **matching pairs** of `'('` and `')'` and keep track of the longest valid sequence.

---

## ✅ Problem Summary

Given a string `s` made only of `'('` and `')'`, return the **length** of the longest substring where the parentheses are **balanced**.

---

### 🧠 Key Observations

* A valid (well-formed) parentheses string has:

  * Every opening `'('` matched with a closing `')'`.
  * Matches must be **in order**.

---

## ✨ Java Solution Using Stack

We use a **stack** to store indices of characters, which helps track the length of valid substrings.

### 🔧 Logic

1. Push `-1` onto the stack (acts as a base for the first valid substring).
2. Loop through the string:

   * If `(`: push the index.
   * If `)`:

     * Pop from the stack.
     * If the stack is empty, push the current index as the new base.
     * If not empty, calculate the length `i - stack.peek()` and update max length.

---

### ✅ Java Code

```java
import java.util.*;

public class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);  // Base for length calculation
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);  // Push index of '('
            } else {
                stack.pop();  // Match with last '('

                if (stack.isEmpty()) {
                    stack.push(i);  // Reset base index
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }
}
```

---

### 🧪 Examples

```java
s = "(()";      // Output: 2
s = ")()())";   // Output: 4
s = "";         // Output: 0
```

---

## ⏱️ Time & Space Complexity

* **Time**: O(n) – each character is processed once.
* **Space**: O(n) – for the stack (in worst case all `(` characters).

---

Let me know if you'd like an **alternative method** using dynamic programming or a **space-optimized** solution!

 */
/*
Absolutely! Let's slow it down and explain this part of the algorithm **step by step**, especially the logic:

> 🧠 "Pop from the stack. If the stack is empty, push the current index as the new base. If not empty, calculate the length `i - stack.peek()` and update max length."

---

### 📌 Context

We are using a **stack** to track the **indices** of characters in the string `s`.

* The stack helps us know **where a valid substring starts**.
* We start by pushing `-1` onto the stack. Why? Because:

  * If we find a valid substring from index `0`, `i - (-1) = i + 1` gives us correct length.
  * Think of `-1` as a **fake base** that helps with clean calculations.

---

### 🔍 What happens when we find a `')'` (closing parenthesis)?

We need to **match** it with a previous `'('`. So:

#### Step 1: `stack.pop();`

* This removes the **last unmatched `'('` index** from the stack (if it exists).

#### Step 2: Is the stack empty?

##### ➤ If YES:

* There's **no unmatched `'('` left** to match this `')'`, so this `')'` is **invalid**.
* We treat this point as a new **starting point** for future valid substrings.
* So we do:

  ```java
  stack.push(i);
  ```

  This sets a **new base**.

##### ➤ If NO:

* There is still a previous `'('` index in the stack.
* We now have a **valid pair**, and maybe even more.
* The length of the current valid substring is:

  ```java
  i - stack.peek()
  ```

  Here's why:

  * `i` is the index of the current `')'`
  * `stack.peek()` gives us the **last unmatched position before the current valid sequence started**
  * So the difference between them gives us the length of the valid sequence

---

### 🧪 Example Walkthrough

Let’s use:

```java
s = ")()())"
```

We initialize the stack with:

```
stack = [-1]
```

Now go through `s` step-by-step:

| Index | Char | Stack Before | Action                              | Stack After | Max Length |
| ----- | ---- | ------------ | ----------------------------------- | ----------- | ---------- |
| 0     | `)`  | \[-1]        | Pop → stack empty → push(0)         | \[0]        | 0          |
| 1     | `(`  | \[0]         | Push index                          | \[0, 1]     | 0          |
| 2     | `)`  | \[0, 1]      | Pop → stack not empty → len = 2-0=2 | \[0]        | 2          |
| 3     | `(`  | \[0]         | Push index                          | \[0, 3]     | 2          |
| 4     | `)`  | \[0, 3]      | Pop → stack not empty → len = 4-0=4 | \[0]        | 4 ✅        |
| 5     | `)`  | \[0]         | Pop → stack empty → push(5)         | \[5]        | 4          |

Final result: `max length = 4`

---

### 🧠 Visualization

```
Original:   ) ( ) ( ) )
Indexes:    0 1 2 3 4 5
Valid:         [1 2] [3 4] → "()()"
```

---

### 🧵 Summary

* **Pop** means: trying to match a `')'` with a `'('`.
* **If the stack is empty** after popping, it means: we have nothing to match with → invalid → reset base.
* **If not empty**, we calculate how long the valid substring is using:

  ```java
  i - stack.peek()
  ```

  because that gives us the distance from the last unmatched position (or `-1` if it’s the very start).

Let me know if you'd like this same logic as a diagram or want to trace a custom input!
 */
