package data.structures.algorithms.stack;

import java.util.Stack;

public class N394DecodeString {
    public static String decodeString(String s) {
        Stack<Integer> nStack = new Stack<>();
        Stack<String> sStack = new Stack<>();
        StringBuilder nBuilder = new StringBuilder();
        StringBuilder sBuilder = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                nBuilder.append(c);
            } else if (c == '[') {
                sStack.push(sBuilder.toString());
                nStack.push(Integer.parseInt(nBuilder.toString()));
                nBuilder.setLength(0);
                sBuilder = new StringBuilder();
            } else if (c == ']') {
                int rNum = nStack.pop();
                String strInStack = sStack.pop();
                StringBuilder repeatString = new StringBuilder();
                while(rNum > 0) {
                    repeatString.append(sBuilder);
                    rNum--;
                }
                sBuilder = new StringBuilder().append(strInStack).append(repeatString);;
            } else {
                sBuilder.append(c);
            }
        }
        return sBuilder.toString();
    }

    public static void main(String[] args) {
       // System.out.println("Decoded Result : " + decodeString("3[a]2[bc]"));
        System.out.println("Decoded Result : " + decodeString("3[a2[c]]"));
        System.out.println("Decoded Result : " + decodeString("2[abc]3[cd]ef"));
    }
}
/*
Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string],
where the encoded_string inside the square brackets is
being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra
white spaces, square brackets are well-formed, etc. Furthermore, you may
assume that the original data does not contain any digits and that digits are only for
those repeat numbers, k. For example, there will not be input like 3a or 2[4].

The test cases are generated so that the length of the output will never exceed 105.



Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"


Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].
 */
