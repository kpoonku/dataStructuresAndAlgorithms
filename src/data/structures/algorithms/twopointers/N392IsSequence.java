package data.structures.algorithms.twopointers;

public class N392IsSequence {
    public static boolean isSequence(String s, String t) {
        int slength = s.length();
        int i = 0, j = 0;
        while (j < slength) {
            if (s.charAt(j) == t.charAt(i)) {
                i++;
            }
            j++;
        }
        return j == slength && i == t.length();
    }

    public static void main(String[] args) {
        String t = "abcd", s = "ahbgdc";
        System.out.println("is Sequence : " + isSequence(s, t));
        t = "axc";
        s = "ahbgdc";
        System.out.println("is Sequence : " + isSequence(s, t));
    }
}
/*
Given two strings s and t, return true if s is a subsequence of t,
or false otherwise.

A subsequence of a string is a new string that is formed from the
original string by deleting some (can be none) of the characters
 without disturbing the relative positions of the remaining
 characters. (i.e., "ace" is a subsequence of "abcde"
 while "aec" is not).

Example 1:
Input: s = "abc", t = "ahbgdc"
Output: true

Example 2:
Input: s = "axc", t = "ahbgdc"
Output: false

 */
