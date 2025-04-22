package data.structures.algorithms.stack;

import java.util.Stack;

public class N2390RemoveStars {
    public static String removeStars(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '*') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println("* to be removed : " + removeStars("leet**cod*e"));
        System.out.println("* to be removed : " + removeStars("erase*****"));
    }
}
