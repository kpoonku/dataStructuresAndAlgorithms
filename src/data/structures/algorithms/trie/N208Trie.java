package data.structures.algorithms.trie;

public class N208Trie{
    private final TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public N208Trie() {
        root = new TrieNode();
    }

    public static void main(String[] args) {
        N208Trie trie = new N208Trie();
        trie.insert("apple");
        System.out.println("trie.search(\"apple\") " + trie.search("apple"));
        System.out.println("trie.search(\"app\") " + trie.search("app"));
        System.out.println("trie.startsWith(\"app\") " + trie.startsWith("app"));
        trie.insert("app");
        System.out.println("trie.search(\"app\") " + trie.search("app"));
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.isEndOfWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEndOfWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    /**
     * Helper method to traverse the trie up to the end of the prefix
     */
    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            int index = c - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }

    private class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26]; // For 'a' to 'z'
            isEndOfWord = false;
        }
    }
}
/*
A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.

Implement the Trie class:

Trie() Initializes the trie object.
void insert(String word) Inserts the string word into the trie.
boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.


Example 1:

Input
["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
[[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
Output
[null, null, true, false, true, null, true]

Explanation
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // return True
trie.search("app");     // return False
trie.startsWith("app"); // return True
trie.insert("app");
trie.search("app");     // return True


Constraints:

1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters.
At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 */
/*
Absolutely! Let’s break down what a **Trie** is and how the code works, in a **beginner-friendly** way. Think of it like building a **smart dictionary** that helps you quickly find words or check if a word starts with certain letters.

---

### 🌳 What Is a Trie?

Imagine this:

You have a bunch of words like: `"apple"`, `"app"`, `"bat"`, `"ball"`

Instead of storing them as full strings one by one, we build a **tree-like structure**, where:
- Each **node** represents a single character
- Each **path from top to bottom** forms a word or prefix

Here's a small visualization:

```
Insert: "apple", "app"

         root
          |
          a
          |
          p
          |
          p <-- (app ends here)
          |
          l
          |
          e <-- (apple ends here)
```

This makes it super fast to:
- ✅ Check if a word exists
- ✅ Check if any word starts with a given prefix
- ✅ Add new words

---

### 🧠 Key Ideas in the Code

1. **Each node holds:**
   - 26 possible children (for letters 'a' to 'z')
   - A flag to say "This is the end of a word"

2. **The `insert` method:**
   - Walks through each character in the word
   - Creates new nodes if the path doesn't exist
   - Marks the end of the word

3. **The `search` method:**
   - Walks the path of the word
   - Returns `true` only if you land on a node marked as the end of a word

4. **The `startsWith` method:**
   - Walks through the prefix
   - Returns `true` if the path exists (doesn't need to be a full word)

---

### 🛠 Real-life Analogy

Think of a Trie like a phone contact search:

- Start typing: `"jo"`
- It immediately shows: `"john"`, `"josh"`, `"jordan"`

That's `startsWith("jo")`

Then you type: `"john"`
- It checks: Does `"john"` exist in the contacts? → That's `search("john")`

If not, you can insert it with `insert("john")`.

---

### 💡 Example Walkthrough

```java
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // true ✅
trie.search("app");     // false ❌ (we only inserted "apple", not "app")
trie.startsWith("app"); // true ✅
trie.insert("app");
trie.search("app");     // true ✅
```

---

### Let Me Know

Want me to animate this with diagrams? Or build a simple version in plain English (no code)? Happy to help make it click 👌
 */