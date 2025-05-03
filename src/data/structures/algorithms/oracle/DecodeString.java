package data.structures.algorithms.oracle;

import java.util.*;

public class DecodeString {
    public static String decodeStringTry(String s) {
        // 2[bc]3[a]
        StringBuilder currentString = new StringBuilder();
        StringBuilder numberString = new StringBuilder();

        Stack<StringBuilder> stringStack = new Stack<>();
        Stack<Integer> numberStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if(Character.isDigit(c)) {
                numberString.append(c);
            } else if (c == '[') {
                numberStack.push(Integer.parseInt(numberString.toString()));
                stringStack.push(currentString);
                currentString = new StringBuilder();
                numberString.setLength(0);
            } else if(c == ']') {
               int repeatNumber = numberStack.pop();
                StringBuilder repeatString = new StringBuilder();
                repeatString.append(
                        String.valueOf(currentString)
                        .repeat(Math.max(0, repeatNumber)));
                System.out.println("repeatString : " + repeatString);
                currentString = stringStack.pop().append(repeatString);
            } else {
                currentString.append(c);
            }
        }
        return currentString.toString();
    }

    public static void main(String[] args) {
        String input = "3[a]2[bc]";

        String result = decodeStringTry(input);
        System.out.println(result);
        result = decodeStringTry("2[abc]3[cd]ef");
        System.out.println(result); // Output: "aaabcbc"
        result = decodeStringTry("3[a2[c]]");
        System.out.println(result);// accaccacc
    }
}
