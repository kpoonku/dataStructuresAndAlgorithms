package data.structures.algorithms.hashmap.set;

import java.util.*;

public class N1657CloseStrings {
    public static boolean closeStrings(String word1, String word2) {
        if (word2.length() != word1.length()) {
            return false;
        }
        Map<Character, Integer> freqMap1 = new HashMap<>();
        Map<Character, Integer> freqMap2 = new HashMap<>();
        for (char c : word1.toCharArray()) {
            freqMap1.put(c, freqMap1.getOrDefault(c, 0) + 1);
        }
        for (char c : word2.toCharArray()) {
            freqMap2.put(c, freqMap2.getOrDefault(c, 0) + 1);
        }
        if (!freqMap2.keySet().equals(freqMap1.keySet())) {
            return false;
        }
        List<Integer> freq1 = new ArrayList<>(freqMap1.values());
        List<Integer> freq2 = new ArrayList<>(freqMap2.values());
        return freq2.equals(freq1);
    }

    public static boolean closeStringsTry(String word1, String word2) {
        if (word2.length() != word1.length()) {
            return false;
        }
        char[] w1Array = word1.toCharArray();
        char[] w2Array = word2.toCharArray();

        Arrays.sort(w1Array);
        Arrays.sort(w2Array);
        for (int i = 0; i < w1Array.length; i++) {
            if (w1Array[i] != w2Array[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Is a close String : " + closeStrings("abcde", "aecdb"));
        System.out.println("Is a close String : " + closeStringsTry("abcde", "aecdb"));
        // ->
        System.out.println("Is a close String : " + closeStrings("abcf", "abcg"));
        System.out.println("Is a close String : " + closeStringsTry("abcf", "abcg"));

        System.out.println("Is a close String : " + closeStrings("a", "aa"));
        System.out.println("Is a close String : " + closeStringsTry("a", "aa"));
    }
}
/*
Two strings are considered close if you can attain one from the other
using the following operations:

Operation 1: Swap any two existing characters.
For example, abcde -> aecdb
Operation 2: Transform every occurrence of one existing character into
another existing character, and do the same with the other character.
For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn
into a's)
You can use the operations on either string as many times as necessary.

Given two strings, word1 and word2, return true if word1 and word2 are
close, and false otherwise.

Example 1:
Input: word1 = "abc", word2 = "bca"
Output: true
Explanation: You can attain word2 from word1 in 2 operations.
Apply Operation 1: "abc" -> "acb"
Apply Operation 1: "acb" -> "bca"

Example 2:
Input: word1 = "a", word2 = "aa"
Output: false
Explanation: It is impossible to attain word2 from word1, or vice versa,
in any number of operations.

Example 3:
Input: word1 = "cabbba", word2 = "abbccc"
Output: true
Explanation: You can attain word2 from word1 in 3 operations.
Apply Operation 1: "cabbba" -> "caabbb"
Apply Operation 2: "caabbb" -> "baaccc"
Apply Operation 2: "baaccc" -> "abbccc"

Constraints:
1 <= word1.length, word2.length <= 105
word1 and word2 contain only lowercase English letters.
 */
/*
Sure! Let me break down the explanation into simpler terms, so it's easier to understand, especially if you're just starting out.

What is the Problem?
You have two strings. The goal is to figure out if you can change one string into another using certain allowed operations:

Swapping any two characters (like switching positions of two characters).

Transforming one character into another, but only if both characters are present in the string.

For example, if you have the string "abc", you can swap the characters to form "bac", or you can transform one character
into another as long as they exist in both strings. So, you could transform 'a' into 'b' if both 'a' and 'b' are present in
both strings.

What the Code Does (Step-by-Step)
Check the Length: First, we check if the two strings have the same length. If the strings are of different lengths, they can't
possibly be close, so we return false.

Example: "abc" and "abcd" can never be close because one has 3 characters and the other has 4.

Convert Strings to Arrays: Both strings are converted into arrays of characters. For example, "abc" would become the array
['a', 'b', 'c'].

Sort the Arrays: We sort both arrays. This helps us see if the strings contain the same characters in any order.

Example: For the strings "abc" and "bac", sorting both gives ['a', 'b', 'c'] in both cases. So, they look the same after sorting.

Compare the Arrays: Finally, we compare the sorted arrays. If they match exactly (i.e., they have the same characters in the
same order after sorting), we return true. This means the two strings are "close", meaning they can be turned into one another
by sorting, swapping, or transforming characters.

Why It Might Not Work for the Original Problem
The problem you're dealing with is more complex because it involves not just sorting and comparing the characters, but also
allowing transformations between characters (like changing 'a' to 'b' if both 'a' and 'b' appear in both strings).

For example:

If string 1 has 'a' and string 2 has 'b', but both strings have a way to swap or transform those characters, they are
considered close.

However, sorting alone doesn't account for this transformation capability. It just checks if the characters are the same after
sorting, but it doesn't handle the case where you need to swap or change characters.

The Problem with the Approach
The code you provided only works for a simpler problem where we just want to check if two strings are anagrams (meaning they
contain the same characters, just in different orders). But it doesn't fully solve the original problem because it doesn’t
check whether you can swap characters or transform one character into another.

The Core Concept (for Beginners):
Anagram Check: The code essentially checks if both strings have the same characters in any order.

Transformation and Swapping: The original problem allows for transforming characters and swapping them. You need to ensure
that you can swap or transform characters (not just check if they exist in both strings).

 Explanation:
Length Check: If the two strings don't have the same length,
they can't be close. So, we return false early.

Frequency Maps: We calculate the frequency of each character in both word1 and word2
using freqMap1 and freqMap2.

Same Character Set: We check if both words have the same set of unique characters.
If they don't, they cannot be made close by the given operations, and we return false.

Same Frequency Distribution: We compare the frequency distribution
(i.e., the frequency counts) of both words. To do this, we convert
the frequency values into lists, sort them, and then check if the sorted lists are equal.
 Sorting ensures that if the frequencies are the same but ordered differently, they can still match.

Why This Works:
Character Set Check: Ensures that both strings contain the same characters,
which is essential for the transformation to be possible.

Sorted Frequency Check: By sorting the frequency counts, you ensure
that swapping characters based on frequency is possible. This satisfies the second operation
described in the problem (transforming characters to match the frequency distribution).
 */