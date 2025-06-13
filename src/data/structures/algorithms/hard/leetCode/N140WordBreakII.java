package data.structures.algorithms.hard.leetCode;

import java.util.*;

public class N140WordBreakII {
    // Testing
    public static void main(String[] args) {
        N140WordBreakII solver = new N140WordBreakII();

        System.out.println(solver.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));

        System.out.println(solver.wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));

        System.out.println(solver.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        List<String> result = new ArrayList<>();

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(0, ""));

        Map<Integer, List<String>> memo = new HashMap<>();

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int start = node.index;
            String path = node.path;

            if (memo.containsKey(start)) {
                for (String suffix : memo.get(start)) {
                    result.add(path.isEmpty() ? suffix : path + " " + suffix);
                }
                continue;
            }

            List<String> currentResults = new ArrayList<>();

            for (int end = start + 1; end <= s.length(); end++) {
                String word = s.substring(start, end);
                if (wordSet.contains(word)) {
                    if (end == s.length()) {
                        result.add(path.isEmpty() ? word : path + " " + word);
                        currentResults.add(word);
                    } else {
                        queue.offer(new Node(end, path.isEmpty() ? word : path + " " + word));
                    }
                }
            }

            memo.put(start, currentResults);
        }

        return result;
    }

    static class Node {
        int index;
        String path;

        Node(int index, String path) {
            this.index = index;
            this.path = path;
        }
    }
}
/*
https://leetcode.com/problems/word-break-ii/?difficulty=HARD
Given a string s and a dictionary of strings wordDict, add spaces in s to construct
a sentence where each word is a valid dictionary word. Return all such possible
sentences in any order.
Note that the same word in the dictionary may be reused multiple times in the segmentation.

Example 1:
Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]

Example 2:
Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []

Constraints:
1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
Input is generated in a way that the length of the answer doesn't exceed 105.
*/
/*
✅ Java Solution: BFS with Queue
🔍 Approach:
Use a Queue to store states during traversal.

Each state holds:

Current index in string.

Sentence built so far.

Use a Set to cache failed starting indices (pruning).

BFS ensures shortest valid paths are explored first, but since we want all valid paths, we don’t stop at the first.

BFS vs DFS in This Problem
Criteria	        | DFS (Stack)	                    | BFS (Queue)
Implementation		| Simpler for recursive logic	    | 	Good for level-by-level search
Stack Overflow		| Risk in deep recursion		    | No risk
Memory Use	        | Can be high with many paths	    | Can be controlled
Path Prioritization	| Deepest paths first		        | Shorter paths first
Use Case		    | All path generation with pruning	| Efficient queue-based version

 */