package data.structures.algorithms;

import java.util.*;

/**
 * 3. Longest Substring Without Repeating Characters
 * Given a string, find the length of the longest
 * substring without repeating characters.
 * <p>
 * Example 1:
 * <p>
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 * <p>
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 * <p>
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Note that the answer must be a substring,
 * "pwke" is a subsequence and not a substring.
 */
public class LongestSubStringMedium {
    static final int MAX_CHAR = 26;

    static int longestUniqueSubstring(String s) {
        int n = s.length();
        int res = 0;
        for (int i = 0; i < n; i++) {
            //init all chars as not visited
            boolean[] vis = new boolean[MAX_CHAR];
            for (int j = i; j < n; j++) {
                //If current char is visited break the loop
                System.out.println("vis[s.charAt(j) - 'a']" + vis[s.charAt(j) - 'a']);
                System.out.println("s.charAt(j) : " + s.charAt(j));
                System.out.println("s.charAt(j) - 'a' : " + (s.charAt(j) - 'a'));
                if (vis[s.charAt(j) - 'a']) {
                    break;
                } else {
                    // Else update the result if this window is larger,
                    // and mark current character as visited
                    System.out.println(" Math.max(res, j - i + 1) : " + Math.max(res, j - i + 1));
                    res = Math.max(res, j - i + 1);
                    vis[s.charAt(j) - 'a'] = true;
                    System.out.println(Arrays.toString(vis));
                }
            }
        }
        return res;
    }

    static int longestUniqueSubstringUsingSet(String s) {
        int stringLength = s.length();
        int res = 0;
        Set<Character> visitedSet = new LinkedHashSet<>(s.length());
        int visitedIndex = 0;
        System.out.println("String : " + s);
        for (int index = 0; index < stringLength; ) {
            if (visitedSet.contains(s.charAt(index))) {
                visitedSet.remove(s.charAt(visitedIndex));
                visitedIndex++;
                System.out.println("s.charAt(visitedIndex) : " + s.charAt(visitedIndex));
            } else {
                System.out.println("s.charAt(index) : " + s.charAt(index));
                visitedSet.add(s.charAt(index));
                index++;
            }
        }
        System.out.println(visitedSet);
        return visitedSet.size();
    }

    public static void main(String[] args) {
        int result = LongestSubStringMedium.longestUniqueSubstringUsingSet("pwkew");
        System.out.println("result : " + result);
        result = LongestSubStringMedium.longestUniqueSubstringUsingSet("bbbbb");
        System.out.println("result : " + result);
        result = LongestSubStringMedium.longestUniqueSubstringUsingSet("qwerty");
        System.out.println("result : " + result);
    }
}
