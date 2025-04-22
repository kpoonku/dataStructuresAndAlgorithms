package data.structures.algorithms.oracle;

import java.util.HashMap;
import java.util.Map;

public class LongestPalindromeLength {
    public static int longestPalindrome(String s) {
        // Step 1: Create a map to store the count of each character
        Map<Character, Integer> charCountMap = new HashMap<>();

        // Step 2: Count the occurrences of each character in the string
        for (char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        // Step 3: Calculate the length of the longest palindrome
        boolean hasOddCount = false;
        int lengthOfLongestPalindrome = 0;

        for (int count : charCountMap.values()) {
            System.out.println(charCountMap.values());
            if (count % 2 == 1) {
                hasOddCount = true;
            }
            lengthOfLongestPalindrome += (count / 2) * 2;  // Add the largest even number
        }

        // Step 4: If there's any character with an odd count, we can add one in the center of the palindrome
        return hasOddCount ? lengthOfLongestPalindrome + 1 : lengthOfLongestPalindrome;
    }
    public static void main(String[] args) {
        System.out.println(longestPalindrome("AabbcC"));
        System.out.println(longestPalindrome("AabbcCf"));
    }
}
/**
 * 409. Longest Palindrome
 * Easy
 * Topics
 * Companies
 * Given a string s which consists of lowercase or uppercase letters,
 * return the length of the longest palindrome that can be built with those letters.
 *
 * Letters are case sensitive, for example, "Aa" is not considered a palindrome.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "abccccdd"
 * Output: 7
 * Explanation: One longest palindrome that can be built is "dccaccd",
 * whose length is 7.
 * Example 2:
 *
 * Input: s = "a"
 * Output: 1
 * Explanation: The longest palindrome that can be built is "a", whose length is 1.
 */
