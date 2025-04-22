package data.structures.algorithms.oracle;

import java.util.PriorityQueue;

public class LongestPalindromicString {

    public static String expandAroundCenterTry(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left+1, right);
    }

    public static String longestPalindromeTry(String s) {
        if (s.isEmpty()) {
            return "Empty or Null String";
        }
        String longestPalindrome = "";
        for (int i = 0; i < s.length(); i++) {
            String oddPalindrome = expandAroundCenterTry(s, i, i);
            if(oddPalindrome.length() > longestPalindrome.length()) {
                longestPalindrome = oddPalindrome;
            }
            String evenPalindrome = expandAroundCenterTry(s, i, i+1);
            if(evenPalindrome.length() > longestPalindrome.length()) {
                longestPalindrome = evenPalindrome;
            }
        }
        return longestPalindrome;
    }

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
            if (oddPalindrome.length() > longest.length()) {
                longest = oddPalindrome;
            }
            String evenPalindrome = expandAroundCenter(s, i, i + 1);
            if (evenPalindrome.length() > longest.length()) {
                longest = evenPalindrome;
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        // Test cases
        //System.out.println(longestPalindrome("forgeeksskeegfor"));  // Output: "geeksskeeg"
        System.out.println(longestPalindrome("Geeks"));             // Output: "ee"
        // System.out.println(longestPalindrome("abc"));              // Output: "a"
        // System.out.println(longestPalindrome(""));
        System.out.println(longestPalindromeTry("forgeeksskeegfor"));  // Output: "geeksskeeg"
        System.out.println(longestPalindromeTry("Geeks"));
    }
}
