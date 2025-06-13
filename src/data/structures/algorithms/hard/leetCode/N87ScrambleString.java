package data.structures.algorithms.hard.leetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class N87ScrambleString {
    private Map<String, Boolean> memo = new HashMap<>();

    public static void main(String[] args) {
        N87ScrambleString sol = new N87ScrambleString();
        System.out.println(sol.isScrambleR("great", "rgeat"));  // true
        System.out.println(sol.isScramble("abcde", "caebd"));  // false
        System.out.println(sol.isScramble("a", "a"));
    }

    public boolean isScrambleR(String s1, String s2) {
        // Base cases
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;

        // Check if result is already in memo
        String key = s1 + "#" + s2;
        if (memo.containsKey(key)) return memo.get(key);

        int n = s1.length();

        // Quick check: if sorted characters don't match, return false
        if (!isAnagram(s1, s2)) {
            memo.put(key, false);
            return false;
        }

        // Try all possible split positions
        for (int i = 1; i < n; i++) {
            // Option 1: No swap
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                    isScramble(s1.substring(i), s2.substring(i))) {
                memo.put(key, true);
                return true;
            }

            // Option 2: Swap
            if (isScramble(s1.substring(0, i), s2.substring(n - i)) &&
                    isScramble(s1.substring(i), s2.substring(0, n - i))) {
                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }


    public boolean isScramble(String s1, String s2) {
        // Early exits
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;

        int n = s1.length();
        Stack<State> stack = new Stack<>();
        Map<String, Boolean> memo = new HashMap<>();
        stack.push(new State(s1, s2, 0));

        while (!stack.isEmpty()) {
            State current = stack.pop();
            String key = current.key();

            if (memo.containsKey(key)) continue;

            if (current.s1.equals(current.s2)) {
                memo.put(key, true);
                continue;
            }

            // Optimization: If letters are not anagrams, skip
            if (!isAnagram(current.s1, current.s2)) {
                memo.put(key, false);
                continue;
            }

            boolean matchFound = false;

            // Try all possible splits
            for (int i = 1; i < current.s1.length(); i++) {
                String s1_left = current.s1.substring(0, i);
                String s1_right = current.s1.substring(i);

                String s2_left = current.s2.substring(0, i);
                String s2_right = current.s2.substring(i);

                String s2_left_swap = current.s2.substring(0, current.s2.length() - i);
                String s2_right_swap = current.s2.substring(current.s2.length() - i);

                // Non-swapped case
                if (memo.getOrDefault(s1_left + "#" + s2_left, null) == null || memo.getOrDefault(s1_right + "#" + s2_right, null) == null) {
                    stack.push(new State(s1_left, s2_left, current.depth + 1));
                    stack.push(new State(s1_right, s2_right, current.depth + 1));
                }

                // Swapped case
                if (memo.getOrDefault(s1_left + "#" + s2_right_swap, null) == null || memo.getOrDefault(s1_right + "#" + s2_left_swap, null) == null) {
                    stack.push(new State(s1_left, s2_right_swap, current.depth + 1));
                    stack.push(new State(s1_right, s2_left_swap, current.depth + 1));
                }
            }

            memo.put(key, false); // Set false by default — will be fixed if we find a match
        }

        return memo.get(s1 + "#" + s2);
    }

    // Helper to check if two strings have the same characters
    private boolean isAnagram(String s1, String s2) {
        int[] count = new int[26];
        for (char c : s1.toCharArray()) count[c - 'a']++;
        for (char c : s2.toCharArray()) count[c - 'a']--;
        for (int c : count) if (c != 0) return false;
        return true;
    }

    private static class State {
        String s1, s2;
        int depth;

        State(String s1, String s2, int depth) {
            this.s1 = s1;
            this.s2 = s2;
            this.depth = depth;
        }

        String key() {
            return s1 + "#" + s2;
        }
    }
}
/*
We can scramble a string s to get a string t using the following algorithm:

If the length of the string is 1, stop.
If the length of the string is > 1, do the following:
Split the string into two non-empty substrings at a random index, i.e., if the string is s, divide it to x and y where s = x + y.
Randomly decide to swap the two substrings or to keep them in the same order. i.e., after this step, s may become s = x + y or s = y + x.
Apply step 1 recursively on each of the two substrings x and y.
Given two strings s1 and s2 of the same length, return true if s2 is a scrambled string of s1, otherwise, return false.



Example 1:

Input: s1 = "great", s2 = "rgeat"
Output: true
Explanation: One possible scenario applied on s1 is:
"great" --> "gr/eat" // divide at random index.
"gr/eat" --> "gr/eat" // random decision is not to swap the two substrings and keep them in order.
"gr/eat" --> "g/r / e/at" // apply the same algorithm recursively on both substrings. divide at random index each of them.
"g/r / e/at" --> "r/g / e/at" // random decision was to swap the first substring and to keep the second substring in the same order.
"r/g / e/at" --> "r/g / e/ a/t" // again apply the algorithm recursively, divide "at" to "a/t".
"r/g / e/ a/t" --> "r/g / e/ a/t" // random decision is to keep both substrings in the same order.
The algorithm stops now, and the result string is "rgeat" which is s2.
As one possible scenario led s1 to be scrambled to s2, we return true.
Example 2:

Input: s1 = "abcde", s2 = "caebd"
Output: false
Example 3:

Input: s1 = "a", s2 = "a"
Output: true
 */
