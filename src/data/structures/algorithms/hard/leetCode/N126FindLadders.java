package data.structures.algorithms.hard.leetCode;

import java.util.*;

public class N126FindLadders {
    public static void main(String[] args) {
        N126FindLadders solver = new N126FindLadders();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        List<List<String>> result = solver.findLadders(beginWord, endWord, wordList);
        for (List<String> path : result) {
            System.out.println(path);
        }
    }

    public List<List<String>> findLadders(String beginWord,
                                          String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();
        if (!wordSet.contains(endWord)) return result;
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> level = new HashMap<>();
        bfs(beginWord, endWord, wordSet, graph, level);
        List<String> path = new ArrayList<>();
        dfs(beginWord, endWord, graph, level, path, result);
        return result;
    }

    // Build graph using BFS
    private void bfs(String beginWord, String endWord,
                     Set<String> wordSet, Map<String, List<String>> graph,
                     Map<String, Integer> level) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        level.put(beginWord, 0);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < size; i++) {
                String word = queue.poll();
                List<String> neighbors = getNeighbors(word, wordSet);
                for (String neighbor : neighbors) {
                    graph.computeIfAbsent(word, k -> new ArrayList<>()).add(neighbor);
                    if (!level.containsKey(neighbor)) {
                        level.put(neighbor, level.get(word) + 1);
                        if (neighbor.equals(endWord)) {
                            foundEnd = true;
                        } else {
                            queue.offer(neighbor);
                        }
                    }
                }
            }
            if (foundEnd) break;
        }
    }

    // Get all one-letter-different neighbors
    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> res = new ArrayList<>();
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char old = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == old) continue;
                chars[i] = c;
                String newWord = new String(chars);
                if (wordSet.contains(newWord)) {
                    res.add(newWord);
                }
            }
            chars[i] = old;
        }
        return res;
    }

    // Collect all shortest paths using DFS
    private void dfs(String word, String endWord, Map<String, List<String>> graph,
                     Map<String, Integer> level, List<String> path,
                     List<List<String>> result) {
        path.add(word);
        if (word.equals(endWord)) {
            result.add(new ArrayList<>(path));
        } else if (graph.containsKey(word)) {
            for (String neighbor : graph.get(word)) {
                if (level.get(neighbor) == level.get(word) + 1) {
                    dfs(neighbor, endWord, graph, level, path, result);
                }
            }
        }
        path.remove(path.size() - 1);  // Backtrack
    }
}
/*
A transformation sequence from word beginWord to word endWord using a dictionary wordList is a
sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

Every adjacent pair of words differs by a single letter.
Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
sk == endWord
Given two words, beginWord and endWord, and a dictionary wordList, return all the shortest
transformation sequences from beginWord to endWord, or an empty list if no such sequence exists.
Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].



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
