package data.structures.algorithms.oracle;

import java.util.PriorityQueue;

public class LongestPalindromicString {

    public static String expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        String longest = "";

        for (int i = 0; i < s.length(); i++) {
            String oddPalindrome = expandAroundCenter(s, i, i);
            System.out.println("oddPalindrome : " + oddPalindrome);
            if (oddPalindrome.length() > longest.length()) {
                longest = oddPalindrome;
            }
            String evenPalindrome = expandAroundCenter(s, i, i + 1);
            System.out.println("evenPalindrome : " + evenPalindrome);
            if (evenPalindrome.length() > longest.length()) {
                longest = evenPalindrome;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(longestPalindrome("forgeeksskeegfor"));  // Output: "geeksskeeg"
        System.out.println(longestPalindrome("Geeks"));             // Output: "ee"
        System.out.println(longestPalindrome("abc"));              // Output: "a"
        System.out.println(longestPalindrome(""));
    }
}
