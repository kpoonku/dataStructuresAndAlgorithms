package data.structures.algorithms.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class N1268SuggestedProducts {
    public static void main(String[] args) {
        N1268SuggestedProducts sol = new N1268SuggestedProducts();
        String[] products = {"mobile", "mouse", "moneypot", "monitor", "mousepad"};
        String searchWord = "mouse";
        System.out.println(sol.suggestedProducts(products, searchWord));
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        Arrays.sort(products);
        List<List<String>> result = new ArrayList<>();
        String prefix = "";
        int start = 0;
        for (char c : searchWord.toCharArray()) {
            prefix += c;
            // Use binary search to find the first index with the prefix
            start = lowerBound(products, prefix, start);
            List<String> suggestions = new ArrayList<>();
            // Collect up to 3 matching products
            for (int i = start; i < Math.min(start + 3, products.length); i++) {
                if (products[i].startsWith(prefix)) {
                    suggestions.add(products[i]);
                } else {
                    break;
                }
            }
            result.add(suggestions);
        }
        return result;
    }

    // Custom binary search to find the first index >= prefix
    private int lowerBound(String[] products, String prefix, int start) {
        int low = start;
        int high = products.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (products[mid].compareTo(prefix) < 0) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
}
/*
You are given an array of strings products and a string searchWord.

Design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

Return a list of lists of the suggested products after each character of searchWord is typed.



Example 1:

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [["mobile","moneypot","monitor"],["mobile","moneypot","monitor"],["mouse","mousepad"],["mouse","mousepad"],["mouse","mousepad"]]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"].
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"].
After typing mou, mous and mouse the system suggests ["mouse","mousepad"].
Example 2:

Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Explanation: The only word "havana" will be always suggested while typing the search word.


Constraints:

1 <= products.length <= 1000
1 <= products[i].length <= 3000
1 <= sum(products[i].length) <= 2 * 104
All the strings of products are unique.
products[i] consists of lowercase English letters.
1 <= searchWord.length <= 1000
searchWord consists of lowercase English letters.
 */
/*
Absolutely! Let's explain this whole idea step by step in a way that's easy for a beginner to **visualize** and **understand**.

---

### 🎯 The Problem (Real Life Example):

Let’s say you’re building a search bar for a shopping website like Amazon. You have a list of products like:

```
["mobile", "mouse", "moneypot", "monitor", "mousepad"]
```

Now, a user starts typing:

```
m → mo → mou → mous → mouse
```

At each step, you want to show up to **3 product suggestions** that:
- Start with what the user has typed so far
- Are in **alphabetical order** (lexicographically smallest)

---

### 🪄 What's the Trick to Solve It Efficiently?

1. **Sort the products list** alphabetically once
   ```
   Sorted list:
   ["mobile", "moneypot", "monitor", "mouse", "mousepad"]
   ```

2. As the user types each letter, build a prefix:
   - First letter: `"m"`
   - Then: `"mo"`
   - Then: `"mou"`
   - ...

3. At each step:
   - Find all words starting with that prefix
   - Take the **first 3 suggestions**

---

### 🧠 What Makes It Fast?

Imagine you're looking through a phone book:
- If names are **sorted**, it's easier to find a match, right?
- You don’t have to check every single name
- You can jump to the section that starts with `"mo"` directly

This jump is done using **binary search**, which is super fast (like looking in the middle of the phone book and narrowing down).

---

### 🚶 Step-by-Step Example

Input:
```java
products = ["mobile", "mouse", "moneypot", "monitor", "mousepad"];
searchWord = "mouse";
```

Sort products:
```
["mobile", "moneypot", "monitor", "mouse", "mousepad"]
```

Now, we go letter by letter:

#### 1. Typing `"m"`:
Matches: `["mobile", "moneypot", "monitor", "mouse", "mousepad"]`
Suggestions: `["mobile", "moneypot", "monitor"]`

#### 2. Typing `"mo"`:
Still matches the same list
Suggestions: `["mobile", "moneypot", "monitor"]`

#### 3. Typing `"mou"`:
Now only: `["mouse", "mousepad"]`
Suggestions: `["mouse", "mousepad"]`

#### 4. Typing `"mous"`:
Still: `["mouse", "mousepad"]`

#### 5. Typing `"mouse"`:
Still: `["mouse", "mousepad"]`

So final output:
```
[
 ["mobile", "moneypot", "monitor"],
 ["mobile", "moneypot", "monitor"],
 ["mouse", "mousepad"],
 ["mouse", "mousepad"],
 ["mouse", "mousepad"]
]
```

---

### 🛠 Why Binary Search?

Instead of checking every product one by one, we **jump** to the first matching product using binary search.

Think of it like flipping to the right page in a dictionary instead of scanning every single word.

---

### 💬 Summary

- Sort the list first → makes things easier
- Go one letter at a time from the search word
- Use fast searching (binary search) to find where the matching products start
- Pick the first 3 that match

---

Would you like a version where we print out what’s happening at each step? Or want to build it in your own words and I help correct or guide?
 */
