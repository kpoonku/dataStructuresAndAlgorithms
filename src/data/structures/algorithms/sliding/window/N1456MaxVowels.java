package data.structures.algorithms.sliding.window;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class N1456MaxVowels {
    public static int maxVowels(String s, int k) {
        Set<Character> vowels = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
        int maxVowels = 0;
        int currentVowels = 0;
        for (int i = 0; i < k; i++) {
            if (vowels.contains(s.charAt(i))) {
                currentVowels++;
            }
        }
        System.out.println("currentVowels : " + currentVowels);
        for (int i = k; i < s.length(); i++) {
            if (vowels.contains(s.charAt(i - k))) {
                currentVowels--;
            }
            if(vowels.contains(s.charAt(i))) {
                currentVowels++;
            }
            maxVowels = Math.max(maxVowels, currentVowels);
        }
        return maxVowels;
    }

    public static void main(String[] args) {
        String s = "abciiidef";
        int k = 3;
        System.out.println(maxVowels(s, k));
    }
}
/*
Given a string s and an integer k, return the maximum number of vowel letters in
any substring of s with length k.
Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

Example 1:
Input: s = "abciiidef", k = 3
Output: 3
Explanation: The substring "iii" contains 3 vowel letters.
Example 2:
Input: s = "aeiou", k = 2
Output: 2
Explanation: Any substring of length 2 contains 2 vowels.
Example 3:
Input: s = "leetcode", k = 3
Output: 2
Explanation: "lee", "eet" and "ode" contain 2 vowels.
 */
/*
Explain for the human brain to perceive this logic

To help your brain perceive this logic step by step, let's
break it down into easy-to-understand concepts:

### **Goal:**
You need to find the maximum number of vowels in any
substring of length `k` within a string `s`. Vowels are
the characters 'a', 'e', 'i', 'o', 'u'.

### **Key Concepts:**
1. **Vowels**: These are the letters you care about, so
you first need to define what vowels are. In our case,
vowels are 'a', 'e', 'i', 'o', and 'u'. We'll use a
**set** to store these vowels for easy lookup.
2. **Sliding Window**: A sliding window allows us to
efficiently process a substring of the string. This
method helps us avoid recomputing the entire count of
vowels every time we move the window one position to
the right. Instead, we **just update** the count by
removing the character that leaves the window and adding
the character that enters the window.

### **Step-by-Step Walkthrough of the Approach:**

#### **Step 1: Initialize a Set of Vowels**
We begin by defining what characters are considered vowels.
Using a **set** allows us to quickly check if a character
is a vowel or not.

- Set of vowels = `{'a', 'e', 'i', 'o', 'u'}`

#### **Step 2: Look at the First Window of Size `k`**
Think of the string as a sequence of blocks. The first
block is the first `k` characters. In this block, you
count how many vowels there are.

Example:
For `s = "abciiidef"` and `k = 3`, the first window is `"abc"`.
- From the first block, count how many vowels are in this
 substring: `a` is a vowel, so we count it as 1 vowel.
- Now, `currentVowels = 1`.

#### **Step 3: Slide the Window Across the String**
Now, to avoid going over the entire string and recounting
the vowels every time, we **slide** the window by one
character. Each time we slide:
- **Remove the character that is leaving the window**:
This character is no longer part of the substring, so
we check if it's a vowel and subtract 1 from `currentVowels`
if it's a vowel.
- **Add the character that is entering the window**:
This new character becomes part of the window, so we
check if it's a vowel and add 1 to `currentVowels` if
it's a vowel.

Example:
For the next window (`"bci"`):
- We remove `a` (which left the window) and check if it's a
vowel. It is, so `currentVowels` decreases by 1.
- We add `i` (which enters the window) and check if it's a
vowel. It is, so `currentVowels` increases by 1.
Now, `currentVowels` will be updated to the correct count.

#### **Step 4: Keep Track of the Maximum Number of Vowels**
As you slide the window across the string, you keep track of
 the **maximum** number of vowels you encounter in any
 window. After each window, check if the number of vowels
 in the current window (`currentVowels`) is greater than
 the `maxVowels` so far. If so, update `maxVowels`.

Example:
If the window `"iii"` gives 3 vowels, update `maxVowels` to 3.

#### **Step 5: Return the Maximum Count**
Once the window has slid over all possible substrings,
 you have the maximum number of vowels in any window of
 size `k`.

### **Visualizing the Sliding Window Process:**
- **Initial window**: First `k` characters → count vowels
- **Slide one step**: Remove old character,
add new character → count vowels for new window
- Repeat this until you've checked all possible windows.

### **Why This Works:**
- **Efficiency**: Instead of counting vowels from
scratch for every new substring, you're only updating the
count based on what enters and leaves the window.
- **Set**: The set allows you to check quickly whether a
character is a vowel, which helps speed up the sliding process.
- **O(n) Time Complexity**: This method processes each
character in the string only once, making the approach
efficient.

### **Putting It All Together:**
Imagine you're walking through a park where each character
is a tree. You have a window of size `k` where you can look
at a group of trees at a time. You're counting how many
 "fruit-bearing trees" (vowels) you can see in each group.
 As you walk one step forward, you remove the tree that's
 now behind you and add the new tree in front of you to
 your view. You keep track of the best (maximum) number
 of fruit-bearing trees you've seen in any group.

This visualization helps your brain understand the sliding window technique and how it allows you to keep track of vowels in a very efficient way.

### **Summary of the Steps in Simple Terms:**
1. Create a set of vowels.
2. Start by counting vowels in the first `k` characters.
3. Slide the window: subtract vowels going out, add vowels coming in.
4. Track the maximum vowels you encounter.
5. Return the maximum.

With this explanation and visualization, your brain should be able to understand the process step by step.
 */