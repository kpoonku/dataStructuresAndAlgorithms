package data.structures.algorithms.backtracking;

import java.util.*;

public class N17LetterCombinationOfPhoneNumbers {
    public static void main(String[] args) {
        N17LetterCombinationOfPhoneNumbers sol = new N17LetterCombinationOfPhoneNumbers();
        System.out.println(sol.letterCombinations("27"));
    }

    public Set<String> letterCombinations(String digits) {
        Set<String> result = new HashSet<>();
        Map<Character, String> phoneMap = new HashMap<>();
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");

        Stack<String> stack = new Stack<>();
        stack.push("");
        while (!stack.isEmpty()) {
            String current = stack.pop();

            if (current.length() == digits.length()) {
                result.add(current);
            } else {
                char digit = digits.charAt(current.length());
                String letters = phoneMap.get(digit);

                for (char letter : letters.toCharArray()) {
                    stack.push(current + letter);
                }
            }
        }
        return result;
    }
}
/*
17. Letter Combinations of a Phone Number
Given a string containing digits from 2-9 inclusive,
return all possible letter combinations
that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons)
is given below. Note that
1 does not map to any letters.

Example 1:
Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]

Example 2:
Input: digits = ""
Output: []

Example 3:
Input: digits = "2"
Output: ["a","b","c"]

Constraints:
0 <= digits.length <= 4
digits[i] is a digit in the range ['2', '9'].
*/
/*
Absolutely! Let's break down **Leetcode 17: Letter Combinations of a Phone Number** into
very **beginner-friendly steps** so it's easy to understand, even if you're new to programming.
---
## 📱 Problem Idea (Like a Phone Keypad)
Imagine the **old mobile phone keypads**, where pressing numbers typed letters.
Here’s how the **numbers map to letters**:
```
2 → a b c
3 → d e f
4 → g h i
5 → j k l
6 → m n o
7 → p q r s
8 → t u v
9 → w x y z
```
---
## 🎯 What You Have to Do
Given a string of numbers like `"23"`, you need to:
- Look at the letters each number can represent.
- Create **all possible combinations** by picking **one letter per number**.
So:
- `"2"` → a, b, c
- `"3"` → d, e, f
Now build all combinations by taking one letter from `"2"` and one from `"3"`:
✅ Output →
`["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]`
---
## 🧠 Step-by-Step Logic
Let’s say `digits = "23"`:
1. From `"2"`, you can choose: a, b, or c.
2. For **each** of those, pair it with one from `"3"`: d, e, or f.
   - So:
     - a + d → "ad"
     - a + e → "ae"
     - a + f → "af"
     - b + d → "bd"
     - ... and so on
This is called a **combinatorial problem** — you are finding all the combinations.
---
## 🔁 How Do We Solve It?
We can use a method called **recursion** or **backtracking**:
- Try one letter from the first digit,
- Then go to the next digit and try each of its letters,
- Continue until you've used all digits,
- Then "go back" and try the next letter from the previous digit.
---
## 🧑‍💻 Java-Like Pseudocode (Simplified)
```java
// Mapping from digits to letters
Map<Character, String> phoneMap = {
    '2': "abc", '3': "def", '4': "ghi", '5': "jkl",
    '6': "mno", '7': "pqrs", '8': "tuv", '9': "wxyz"
};
List<String> result = [];
void backtrack(String combination, String nextDigits) {
    if (nextDigits.isEmpty()) {
        result.add(combination); // complete combo built
    } else {
        char digit = nextDigits.charAt(0);
        String letters = phoneMap.get(digit);
        for (char letter : letters.toCharArray()) {
            backtrack(combination + letter, nextDigits.substring(1));
        }
    }
}
```
Call this with:
```java
if (!digits.isEmpty()) {
    backtrack("", digits);
}
```
---
## ❓ What Happens If...
- **Input is empty ("")** → Just return `[]` (no combinations).
- **Input is one digit ("2")** → Just return `["a", "b", "c"]`.
---
## 🧒 Real-Life Analogy:
Imagine a vending machine keypad:
- You press "2" → it highlights A, B, C
- You press "3" → it highlights D, E, F
- Now it wants to **show every possible way you could press A/B/C then D/E/F**
So combinations = every letter from 2 × every letter from 3
---
Would you like a full Java version of the code with comments, or a visual chart of how the
combinations are built?
 */