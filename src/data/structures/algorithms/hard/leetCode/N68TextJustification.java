package data.structures.algorithms.hard.leetCode;

import java.util.ArrayList;
import java.util.List;

public class N68TextJustification {
    public static void main(String[] args) {
        N68TextJustification tj = new N68TextJustification();
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        List<String> output = tj.fullJustify(words, maxWidth);

        for (String line : output) {
            System.out.println("\"" + line + "\"");
        }

        words = new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to",
                "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        maxWidth = 20;
        //output = tj.fullJustify(words, maxWidth);
        System.out.println();
        for (String line : output) {
            System.out.println("\"" + line + "\",");
        }
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int lineLength = words[i].length();
            int j = i + 1;

            // Try to fit as many words as possible in the line
            while (j < words.length && lineLength + 1 + words[j].length() <= maxWidth) {
                lineLength += 1 + words[j].length(); // 1 for space
                j++;
            }

            int numWords = j - i;
            int numSpaces = maxWidth - lineLength + (numWords - 1); // extra spaces to distribute

            StringBuilder line = new StringBuilder();

            // If last line or line has only one word → left-justified
            if (j == words.length || numWords == 1) {
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) line.append(" ");
                }
                // Fill the rest with spaces
                while (line.length() < maxWidth) {
                    line.append(" ");
                }
            } else {
                int spaces = maxWidth - (lineLength - (numWords - 1)); // total extra spaces
                int spaceBetweenWords = spaces / (numWords - 1);
                int extra = spaces % (numWords - 1); // extra spaces to add at the left slots

                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) {
                        for (int s = 0; s < spaceBetweenWords; s++) line.append(" ");
                        if (extra > 0) {
                            line.append(" ");
                            extra--;
                        }
                    }
                }
            }

            result.add(line.toString());
            i = j;
        }

        return result;
    }
}
/*
Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.


Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one word.
Example 3:

Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]


Constraints:

1 <= words.length <= 300
1 <= words[i].length <= 20
words[i] consists of only English letters and symbols.
1 <= maxWidth <= 100
words[i].length <= maxWidth
 */
/*
Sure! Let me break this down **step-by-step in very simple terms** and give you a clear **Java solution** for full text justification.

---

## 🧠 The Problem (Explained Simply)

You are given:

* A list of words like:
  `["This", "is", "an", "example", "of", "text", "justification."]`
* And a number `maxWidth`, like `16`, which is the number of characters each line should have.

### Your Job:

👉 Put the words into lines:

* Each line should be exactly `maxWidth` characters long.
* If the words don’t fill the line, **add spaces** between them to stretch the line.
* **Spaces must be distributed evenly.**
* **The last line** should be **left-aligned** (just like normal text).

---

## 🔧 Step-by-Step Plan

### 1. Pack as many words into a line as possible.

Keep adding words to the current line **until adding another would go over maxWidth**.

### 2. Once the line is full:

* Count how many spaces need to be added.
* Distribute them between the words.

  * If there's extra space that can't be evenly split, give **more to the left**.

### 3. Special case:

* The **last line** is left-aligned (no extra spacing between words).

---

## ✅ Java Code (Fully Explained)

```java
import java.util.*;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int lineLength = words[i].length();
            int j = i + 1;

            // Try to fit as many words as possible in the line
            while (j < words.length && lineLength + 1 + words[j].length() <= maxWidth) {
                lineLength += 1 + words[j].length(); // 1 for space
                j++;
            }

            int numWords = j - i;
            int numSpaces = maxWidth - lineLength + (numWords - 1); // extra spaces to distribute

            StringBuilder line = new StringBuilder();

            // If last line or line has only one word → left-justified
            if (j == words.length || numWords == 1) {
                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) line.append(" ");
                }
                // Fill the rest with spaces
                while (line.length() < maxWidth) {
                    line.append(" ");
                }
            } else {
                int spaces = maxWidth - (lineLength - (numWords - 1)); // total extra spaces
                int spaceBetweenWords = spaces / (numWords - 1);
                int extra = spaces % (numWords - 1); // extra spaces to add at the left slots

                for (int k = i; k < j; k++) {
                    line.append(words[k]);
                    if (k < j - 1) {
                        for (int s = 0; s < spaceBetweenWords; s++) line.append(" ");
                        if (extra > 0) {
                            line.append(" ");
                            extra--;
                        }
                    }
                }
            }

            result.add(line.toString());
            i = j;
        }

        return result;
    }

    // Test the method
    public static void main(String[] args) {
        TextJustification tj = new TextJustification();
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        List<String> output = tj.fullJustify(words, maxWidth);

        for (String line : output) {
            System.out.println("\"" + line + "\"");
        }
    }
}
```

---

## 🧪 Output:

```text
"This    is    an"
"example  of text"
"justification.  "
```

---

## 💡 Beginner-Friendly Notes

### 📦 How words are packed

* We keep adding words until we can’t fit another word with at least one space.

### 📏 Calculating spaces

* We figure out how many spaces we need by subtracting word lengths from maxWidth.
* If it's not even, we put more spaces on the **left side**.

### 🧨 Last line

* It just joins words with a single space.
* Fills the rest of the line with spaces at the end.

---

Would you like me to:

* Walk through one example line in detail?
* Convert this to Python?
* Visualize spacing distribution?

Let me know how you'd like to learn further!
 */