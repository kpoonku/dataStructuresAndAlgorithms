package data.structures.algorithms.array.strings;

public class N443StringCompression {
    public static int compress(char[] chars) {
        int read = 0, write = 0;
        while (read < chars.length) {
            char currentC = chars[read];
            int count = 0;
            while (read < chars.length && chars[read] == currentC) {
                read++;
                count++;
            }
            chars[write++] = currentC;
            if(count > 1) {
                for(char digit: Integer.toString(count).toCharArray()) {
                    chars[write++] = digit;
                }
            }
        }
        System.out.println(new String(chars));
        return write;
    }

    public static void main(String[] args) {
        System.out.println(compress(new char[]{'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
        System.out.println(compress(new char[]{'a'}));
        System.out.println(compress(new char[]{'a', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b', 'b'}));
    }
}
/*
Given an array of characters chars, compress it using the following algorithm:
Begin with an empty string s. For each group of consecutive repeating characters in chars:
If the group's length is 1, append the character to s.
Otherwise, append the character followed by the group's length.
The compressed string s should not be returned separately, but instead,
be stored in the input character array chars. Note that group lengths that are 10 or longer
will be split into multiple characters in chars.
After you are done modifying the input array, return the new length of the array.
You must write an algorithm that uses only constant extra space.

Example 1:
Input: chars = ["a","a","b","b","c","c","c"]
Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".

Example 2:
Input: chars = ["a"]
Output: Return 1, and the first character of the input array should be: ["a"]
Explanation: The only group is "a", which remains uncompressed since it's a single character.

Example 3:
Input: chars = ["a","b","b","b","b","b","b","b","b","b","b","b","b"]
Output: Return 4, and the first 4 characters of the input array should be: ["a","b","1","2"].
Explanation: The groups are "a" and "bbbbbbbbbbbb". This compresses to "ab12".

Constraints:
1 <= chars.length <= 2000
chars[i] is a lowercase English letter, uppercase English letter, digit, or symbol.
*/
/*
Absolutely! Let’s break this down into **very simple steps** that a **beginner brain**
can clearly **picture and understand** — no programming knowledge needed.
---
## 🧠 Imagine You’re Packing a Box of Letters...
Let’s say you have this row of letters:
```
["a", "a", "b", "b", "c", "c", "c"]
```
You want to **compress** it like this:
- Instead of writing each letter **every time it appears**,
- You write the **letter once**, and if it repeats, you write the **number** of times next to it.
So:
- `"a", "a"` becomes `"a", "2"`
- `"b", "b"` becomes `"b", "2"`
- `"c", "c", "c"` becomes `"c", "3"`
Final packed result:
```
["a", "2", "b", "2", "c", "3"]
```
---
## 🪜 Step-by-Step Like a Human
Let’s pretend **you are the computer** and doing it by hand.
1. **Start at the beginning** of the list.
2. Look at the **current letter**.
3. Count how many times it **repeats in a row**.
4. Write the letter once.
5. If it repeats more than once, write the **number** next to it.
6. Move to the **next different letter**.
7. Repeat this until you finish the list.
---
## 🧺 Imagine This in Real Life
Think of it like sorting beads:

```
Beads: a a a b b b b c
```

You’d say:
- 3 a’s → write “a 3”
- 4 b’s → write “b 4”
- 1 c → just write “c”

Result: `a3b4c`
---
## 🎯 Goal for the Computer
- Don’t use extra space.
- Overwrite the **original list** as you go.
- Keep track of where to **write** using a finger (that’s called a “write pointer”).
---
### ✅ You can visualize this:
- One hand is **counting** (reading).
- The other hand is **writing** the compressed version.
---
Would you like a visual story or analogy to further help you remember this?
*/