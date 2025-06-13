package data.structures.algorithms.array.strings;

import java.util.Arrays;
import java.util.List;

public class N345ReverseVowels {
    public static String reverseVowels(String s) {
        List<Character> vowels = Arrays.asList
                ('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        char[] c = s.toCharArray();
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while(left < right && !vowels.contains(c[left])) {
                left++;
            }
            while(left < right && !vowels.contains(c[right])) {
                right--;
            }
            if(left < right) {
                char temp = c[left];
                c[left] = c[right];
                c[right] = temp;
                left++;
                right--;
            }
        }
        return new String(c);
    }

    public static void main(String[] args) {
        String s = reverseVowels("IceCreAm");
        System.out.println("s : " + s);
    }
}
/*
Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.



Example 1:

Input: s = "IceCreAm"

Output: "AceCreIm"

Explanation:

The vowels in s are ['I', 'e', 'e', 'A']. On reversing the vowels, s becomes "AceCreIm".

Example 2:

Input: s = "leetcode"

Output: "leotcede"
 */