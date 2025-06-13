package data.structures.algorithms.hard.leetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class N30FindSubString {
    public static void main(String[] args) {
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        N30FindSubString solution = new N30FindSubString();
        System.out.println(solution.findSubstring(s, words));
        s = "barfoofoobarthefoobarman";
        words = new String[] {"bar","foo","the"};
        System.out.println(solution.findSubstring(s, words));
    }

    public List<Integer> findSubstring(String s, String[] words) {
        // s = "barfoothefoobarman", words = ["foo","bar"]
        List<Integer> result = new ArrayList<>();

        Map<String, Integer> wordCount = new HashMap<>();

        int wordLength = words[0].length();
        int numWords = words.length;
        int totalLength = wordLength * numWords;

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for (int i = 0; i <= s.length() - totalLength; i++) {
            if (isValid(s.substring(i, i + totalLength), wordLength, wordCount)) {
                result.add(i);
            }
        }
        return result;
    }

    public boolean isValid(String s, int wordLength, Map<String, Integer> wordCount) {
        Map<String, Integer> seen = new HashMap<>();

        for (int i = 0; i < s.length(); i += wordLength) {
            String wordUnit = s.substring(i, i + wordLength);

            if (!wordCount.containsKey(wordUnit)) {
                return false;
            }
            seen.put(wordUnit, seen.getOrDefault(wordUnit, 0) + 1);
            if (seen.get(wordUnit) > wordCount.get(wordUnit)) {
                return false;
            }
        }
        System.out.println(seen);
        return true;
    }
}
/*
You are given a string s and an array of strings words.
All the strings of words are of the same length.
A concatenated string is a string that exactly contains all
the strings of any permutation of words concatenated.
For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd",
"cdabef", "cdefab", "efabcd", and "efcdab" are all concatenated
strings. "acdbef" is not a concatenated string because it is not
the concatenation of any permutation of words.
Return an array of the starting indices of all the concatenated
substrings in s. You can return the answer in any order.
Example 1:
Input: s = "barfoothefoobarman", words = ["foo","bar"]
Output: [0,9]
Explanation:
The substring starting at 0 is "barfoo". It is the concatenation of
["bar","foo"] which is a permutation of words.
The substring starting at 9 is "foobar". It is the concatenation of
["foo","bar"] which is a permutation of words.
Example 2:
Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best",
"word"]
Output: []
Explanation:
There is no concatenated substring.
Example 3:
Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
Output: [6,9,12]
Explanation:
The substring starting at 6 is "foobarthe". It is the concatenation
of ["foo","bar","the"].
The substring starting at 9 is "barthefoo". It is the concatenation
of ["bar","the","foo"].
The substring starting at 12 is "thefoobar". It is the concatenation
of ["the","foo","bar"].

Constraints:
1 <= s.length <= 104
1 <= words.length <= 5000
1 <= words[i].length <= 30
s and words[i] consist of lowercase English letters.
 */
/*
Absolutely! Let's break it down for a beginner. Think of this like a puzzle and we're trying to find pieces in a bigger picture that fit a specific pattern.

---

### 🧩 Problem Summary (Simple Words)

You’re given:

* A big string `s` (like a paragraph or sentence).
* A list of small words called `words` — they are all the **same length**.

Your job:
👉 Find all the **starting positions** in the big string where all the small words are stuck **together** (without anything in between), and in **any order**, **exactly once**.

---

### ✅ Example

#### Input:

```python
s = "barfoothefoobarman"
words = ["foo", "bar"]
```

Each word is 3 letters long (`"foo"` and `"bar"`).
We want to find all places in `s` where `"foo"` and `"bar"` appear together one after the other (any order).

So we check substrings of length `6` (3 + 3).

**Look at every 6-letter piece of `s`:**

1. `"barfoo"` ✅ ← contains "bar" and "foo"
2. `"arfoot"` ❌ not the words we want
3. `"rfooth"` ❌
4. `"foothe"` ❌
5. `"oothef"` ❌
6. `"othefo"` ❌
7. `"thefoo"` ❌
8. `"hefoob"` ❌
9. `"efooba"` ❌
10. `"foobar"` ✅ ← contains "foo" and "bar"

So the answer is:

```python
[0, 9]
```

---

### 🧠 How the Code Works

Here’s the step-by-step idea:

1. **Figure out how long the combined words will be**
   → If you have 2 words each of length 3, then total is `6`.

2. **Go through the string** and look at every chunk of 6 characters.

3. For each chunk:

   * Break it into 3-letter parts (`"barfoo"` → `"bar"`, `"foo"`).
   * Check if these parts match the words you were given.
   * If yes, save the starting index.

4. Do this for the whole string.

---

### 🪄 Imagine It Like This

You’re scanning a book line with a little 6-letter window:

🔍 Move the window one letter at a time and ask:

* "Hey, does this window have all the little words I need?"

If yes → Save where it starts.
If no → Keep moving.

---

### 🧪 Why It Works

We use a map (like a dictionary) to count how many times each word appears in `words`.

Then, for every part of the string, we check if we see **the exact same words** the same number of times.

---

### 💡 Final Tip

If you’re new to programming, try printing things inside the loop to **see what’s happening**. Example:

```python
print(f"Checking from index {i}: {s[i:i+total_len]}")
```

This helps you understand how the program “thinks” as it goes step by step.

---

Let me know if you'd like me to add drawings or use a real visual example with positions marked!

 */
/*
Sure! Here's a **beginner-friendly Java solution** to the problem of finding all starting indices in a string `s` where all words from the list `words` are concatenated together exactly once, in any order.

---

### ✅ Java Solution (with explanation)

```java
import java.util.*;

public class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s == null || s.length() == 0 || words == null || words.length == 0)
            return result;

        int wordLength = words[0].length();            // Length of each word
        int numWords = words.length;                   // Total number of words
        int totalLength = wordLength * numWords;       // Total length of all words combined

        // Map to count each word in the list
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words)
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);

        // Slide a window of size totalLength over the string
        for (int i = 0; i <= s.length() - totalLength; i++) {
            String substring = s.substring(i, i + totalLength);  // Window of words
            if (isValid(substring, wordLength, wordCount))
                result.add(i);
        }

        return result;
    }

    // Helper method to check if a substring is a valid combination of words
    private boolean isValid(String s, int wordLength, Map<String, Integer> wordCount) {
        Map<String, Integer> seen = new HashMap<>();

        for (int i = 0; i < s.length(); i += wordLength) {
            String word = s.substring(i, i + wordLength);

            if (!wordCount.containsKey(word))
                return false;

            seen.put(word, seen.getOrDefault(word, 0) + 1);

            if (seen.get(word) > wordCount.get(word))
                return false;
        }

        return true;
    }
}
```

---

### 🧠 How it Works:

1. **Word Counting**:

   * We use a `Map` (`wordCount`) to remember how many times each word should appear.

2. **Sliding Window**:

   * We go through the big string `s`, and for every starting point, we look at a substring of size `wordLength * words.length`.

3. **Check Validity**:

   * Split the substring into parts of size `wordLength`.
   * Check if these words match the expected ones using a second map (`seen`).

4. **Save Index**:

   * If it's a valid combination, we save the starting index.

---

### 🧪 Example

Input:

```java
s = "barfoothefoobarman";
words = ["foo", "bar"];
```

Output:

```java
[0, 9]
```

---

### 📦 Time Complexity:

* **O(N \* M)**, where:

  * `N` = length of the string `s`
  * `M` = number of words

It's efficient and handles the input constraints well.

---

Let me know if you'd like this rewritten using more advanced Java features like streams or with performance optimization (like sliding window without rebuilding the map each time).

 */