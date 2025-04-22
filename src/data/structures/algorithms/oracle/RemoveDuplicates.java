package data.structures.algorithms.oracle;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

class StackV {
    char aChar;
    int cnt;

    StackV(char aChar) {
        this.aChar = aChar;
    }

    int getCnt() {
        return cnt;
    }

    char getaChar() {
        return aChar;
    }
}

public class RemoveDuplicates {

    public static String removeDuplicatesTry(String s, int k) {
        Stack<StackV> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().getaChar() == c) {
                StackV sv = stack.peek();
                sv.cnt++;
                if (sv.cnt == k -1) {
                    stack.pop();
                }
            } else {
                stack.push(new StackV(c));
            }
        }
        StringBuilder sb = new StringBuilder();
        for (StackV sv : stack) {
            while(sv.cnt-- >= 0) {
                sb.append(sv.getaChar());
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("Result : " + removeDuplicatesTry("deeedbbcccbdaa", 3));
    }

    public String removeDuplicates(String s, int k) {
        // Initialize a stack to store the character and its frequency count
        Deque<int[]> stack = new ArrayDeque<>();

        // Traverse through the string characters
        for (char ch : s.toCharArray()) {
            int index = ch - 'a'; // Convert character to an index (0 for 'a', 1 for 'b', etc.)

            // If stack is not empty and the character on the top of the stack is the same as the current one
            if (!stack.isEmpty() && stack.peek()[0] == index) {
                // Increment the frequency count
                stack.peek()[1]++;

                // If the frequency count reaches k, pop the element from the stack
                if (stack.peek()[1] == k) {
                    stack.pop();
                }
            } else {
                // If the top of the stack is different, push the new character with a frequency of 1
                stack.push(new int[]{index, 1});
            }
        }

        // Build the result string from the stack
        StringBuilder result = new StringBuilder();
        for (int[] item : stack) {
            // Append the character to the result, repeated by its frequency
            result.append(String.valueOf((char) (item[0] + 'a')).repeat(item[1]));
        }

        // Return the result string
        return result.toString();
    }
}
