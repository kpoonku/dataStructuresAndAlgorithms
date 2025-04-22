package data.structures.algorithms;

import java.util.ArrayDeque;
import java.util.Deque;

/*
Given a string s, the task is to find the longest substring which is a palindrome. If there are multiple answers, then return the first appearing substring.

Examples:

Input: s = “forgeeksskeegfor”
Output: “geeksskeeg”
Explanation: There are several possible palindromic substrings like “kssk”, “ss”, “eeksskee” etc. But the substring “geeksskeeg” is the longest among all.


Input: s = “Geeks”
Output: “ee”


Input: s = “abc”
Output: “a”

Input: s = “”
Output: “”
 */

// TODO: Visit
public class FindTheLongestPalindrome {
    public static String findTheLongestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";

        // 2D array to store results of subproblems
        boolean[][] dp = new boolean[n][n];
        int start = 0, maxLen = 1;

        // Every single character is a palindrome
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // Check for substrings of length 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < n - len + 1; i++) {
                int j = i + len - 1;
                // If the characters are equal and the inner substring is a palindrome
                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        if (len > maxLen) {
                            maxLen = len;
                            start = i;
                        }
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        String s = "forgeeksskeegfor";
        System.out.println(findTheLongestPalindrome(s));
    }

}
