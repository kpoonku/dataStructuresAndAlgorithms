package data.structures.algorithms.oracle;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidBracketSequence {
    public static boolean isValidBracketSequence(String s) {
        Map<Character, Character> bracketSetMap = new HashMap<>();
        bracketSetMap.put('{', '}');
        bracketSetMap.put('[', ']');
        bracketSetMap.put('(', ')');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (bracketSetMap.containsKey(c)) {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && bracketSetMap.get(stack.pop()) != c) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        //System.out.println(" is valid {([[)} : " + isValidBracketSequence("{([[)}"));
        System.out.println(" is valid {([])} : " + isValidBracketSequence("{([])}"));
        //System.out.println(" is valid {([](} : " + isValidBracketSequence("{([](}"));
        //System.out.println(" is valid {([[){ : " + isValidBracketSequence("{([[){"));
        //System.out.println(" is valid {([[({ : " + isValidBracketSequence("{([[({"));
    }
}

/*
20. Valid Parentheses
Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
determine if the input string is valid.

An input string is valid if:
Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Every close bracket has a corresponding open bracket of the same type.

Example 1:
Input: s = "()"
Output: true

Example 2:
Input: s = "()[]{}"
Output: true

Example 3:
Input: s = "(]"
Output: false

Example 4:
Input: s = "([])"
Output: true

Constraints:
1 <= s.length <= 104
s consists of parentheses only '()[]{}'.
*/
/*
Sure! Let's go step-by-step to write and explain a **Java solution** for the **Valid Parentheses** problem — in a way that a **beginner** can easily understand.

---

### ✅ Problem Recap (Simplified)
Check if a string of brackets like `"()[]{}"` is **valid**.

A string is valid **if:**
- Every open bracket is closed.
- Brackets are closed **in the right order**.

---

### 🧠 Concept: Use a **Stack**
A **stack** is like a box where:
- You **push** things on top.
- You **pop** the top off — like a pile of plates.

In this problem:
- We push **opening brackets** (`(`, `[`, `{`) onto the stack.
- When we see a **closing bracket** (`)`, `]`, `}`), we check if it matches the top of the stack.

---

### 🧑‍💻 Java Code (with simple comments)

```java
import java.util.Stack;

public class ValidParentheses {
    public boolean isValid(String s) {
        // Stack to hold opening brackets
        Stack<Character> stack = new Stack<>();

        // Loop through each character in the string
        for (char ch : s.toCharArray()) {
            // If it's an opening bracket, push to stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                // If stack is empty, there's no matching opening bracket
                if (stack.isEmpty()) return false;

                // Check if the top of the stack matches the closing bracket
                char top = stack.pop();
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // If stack is empty, all brackets matched correctly
        return stack.isEmpty();
    }
}
```

---

### 🔍 How It Works (Example: `"([])"`)
1. Push `'('` → stack: `(`
2. Push `'['` → stack: `([`
3. See `']'` → matches `'['`, pop it → stack: `(`
4. See `')'` → matches `'('`, pop it → stack: empty ✅
=> All brackets matched → **Valid**

---

### 🧪 Quick Test

You can test this with:

```java
public class Main {
    public static void main(String[] args) {
        ValidParentheses vp = new ValidParentheses();
        System.out.println(vp.isValid("()[]{}")); // true
        System.out.println(vp.isValid("([)]"));   // false
        System.out.println(vp.isValid("{[]}"));   // true
    }
}
```

---

Would you like a diagram to visualize how the stack changes as the string is processed?
 */