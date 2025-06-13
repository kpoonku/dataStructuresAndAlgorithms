package data.structures.algorithms.hard.leetCode;

import java.util.*;

public class N127FindLadder1 {
    public static void main(String[] args) {
        N127FindLadder1 solver = new N127FindLadder1();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int length = solver.ladderLength(beginWord, endWord, wordList);
        System.out.println(length);
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int length = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();

            // Process one level at a time
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                if (word.equals(endWord)) return length;

                for (String neighbor : getNeighbors(word, wordSet)) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
            length++;
        }

        return 0; // no path found
    }

    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> neighbors = new ArrayList<>();
        char[] chars = word.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char old = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == old) continue;
                chars[i] = c;
                String newWord = new String(chars);
                if (wordSet.contains(newWord)) {
                    neighbors.add(newWord);
                }
            }
            chars[i] = old;
        }

        return neighbors;
    }
}
/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].



Example 1:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation: There are 2 shortest transformation sequences:
"hit" -> "hot" -> "dot" -> "dog" -> "cog"
"hit" -> "hot" -> "lot" -> "log" -> "cog"
Example 2:

Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
Output: []
Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.


Constraints:

1 <= beginWord.length <= 5
endWord.length == beginWord.length
1 <= wordList.length <= 500
wordList[i].length == beginWord.length
beginWord, endWord, and wordList[i] consist of lowercase English letters.
beginWord != endWord
All the words in wordList are unique.
The sum of all shortest transformation sequences does not exceed 105.
 */
/*
This `bfs` method is part of a **Word Ladder II** solution in Java. It's used to build a **graph of word transformations** and record the **shortest path levels** from the `beginWord` to every reachable word in the `wordSet`.

---

### ✅ Method Signature:

```java
private void bfs(String beginWord, String endWord,
                 Set<String> wordSet, Map<String, List<String>> graph,
                 Map<String, Integer> level)
```

| Parameter   | Purpose                                                             |
| ----------- | ------------------------------------------------------------------- |
| `beginWord` | Starting word of the transformation.                                |
| `endWord`   | Target word to reach.                                               |
| `wordSet`   | Set of valid words for transformation.                              |
| `graph`     | Maps each word to its **neighbors** (words one letter away).        |
| `level`     | Maps each word to the **minimum number of steps** from `beginWord`. |

---

### 🔄 High-Level Flow:

1. Use **Breadth-First Search (BFS)** to explore all possible word transformations from `beginWord`.
2. **Build the graph** of valid transformations: each word connects to its one-letter-different neighbors.
3. **Track the shortest level** (number of transformations) for each word using the `level` map.
4. Stop early if `endWord` is found.

---

### 🧠 Line-by-Line Explanation:

```java
Queue<String> queue = new LinkedList<>();
queue.offer(beginWord);
level.put(beginWord, 0);
```

* Start BFS from `beginWord`.
* Initialize the level (distance) of `beginWord` to `0`.

---

```java
while (!queue.isEmpty()) {
    int size = queue.size();
    boolean foundEnd = false;
```

* Process each level of BFS.
* `foundEnd` is used to **stop early** if `endWord` is found at the current level.

---

```java
for (int i = 0; i < size; i++) {
    String word = queue.poll();
    List<String> neighbors = getNeighbors(word, wordSet);
```

* For each word at the current level, get all possible **one-letter neighbors** using `getNeighbors()`.

---

```java
    for (String neighbor : neighbors) {
        graph.computeIfAbsent(word, k -> new ArrayList<>()).add(neighbor);
```

* Add a directed edge from `word` to its neighbor in the `graph`.

---

```java
        if (!level.containsKey(neighbor)) {
            level.put(neighbor, level.get(word) + 1);
```

* If the neighbor hasn't been visited yet (i.e., doesn't exist in `level`), assign it a level one more than `word`.

---

```java
            if (neighbor.equals(endWord)) {
                foundEnd = true;
            } else {
                queue.offer(neighbor);
            }
        }
    }
```

* If this neighbor is the `endWord`, set `foundEnd` to stop the BFS early.
* Otherwise, enqueue it to be processed in the next BFS layer.

---

```java
if (foundEnd) break;
```

* Exit BFS early after discovering `endWord` at the current level (to ensure **only shortest paths** are considered).

---

### ✅ Purpose of `bfs`:

* Build a **graph of valid word transformations**.
* Record the **shortest distance** from `beginWord` to every reachable word using the `level` map.
* Supports a later **DFS or backtracking phase** to reconstruct all the shortest transformation sequences.

---

Would you like an explanation of `getNeighbors()` or how this BFS connects to the full solution?

 */
/*
Here's a **simplified Java version** of the `findLadders` method that returns all the shortest transformation sequences using BFS and backtracking. This version keeps things straightforward and readable, suitable for interviews or understanding the core idea.

---

### ✅ Java Code:

```java
import java.util.*;

public class WordLadderII {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();
        if (!wordSet.contains(endWord)) return result;

        Map<String, List<String>> parents = new HashMap<>();
        Set<String> currentLevel = new HashSet<>();
        currentLevel.add(beginWord);
        boolean found = false;

        while (!currentLevel.isEmpty() && !found) {
            wordSet.removeAll(currentLevel);
            Set<String> nextLevel = new HashSet<>();

            for (String word : currentLevel) {
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char old = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String newWord = new String(chars);
                        if (wordSet.contains(newWord)) {
                            nextLevel.add(newWord);
                            parents.computeIfAbsent(newWord, k -> new ArrayList<>()).add(word);
                            if (newWord.equals(endWord)) found = true;
                        }
                    }
                    chars[i] = old;
                }
            }

            currentLevel = nextLevel;
        }

        if (found) {
            List<String> path = new ArrayList<>();
            backtrack(endWord, beginWord, parents, path, result);
        }

        return result;
    }

    private void backtrack(String word, String beginWord, Map<String, List<String>> parents,
                           List<String> path, List<List<String>> result) {
        path.add(word);
        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            result.add(temp);
        } else {
            List<String> prevWords = parents.get(word);
            if (prevWords != null) {
                for (String prev : prevWords) {
                    backtrack(prev, beginWord, parents, path, result);
                }
            }
        }
        path.remove(path.size() - 1);
    }
}
```

---

### 🔍 Example:

```java
WordLadderII solver = new WordLadderII();
List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
List<List<String>> result = solver.findLadders("hit", "cog", wordList);
System.out.println(result);
```

---

### ✨ Output:

```
[[hit, hot, dot, dog, cog], [hit, hot, lot, log, cog]]
```

---

Let me know if you’d like a version with comments or even more minimal syntax.

 */