package data.structures.algorithms.oracle;

import java.util.*;

public class RemoveDuplicateLetters {

    public static String removeDuplicateLettersUsingMap(String s) {
        // Map to store the last occurrence index of each character
        Map<Character, Integer> lastIndexMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastIndexMap.put(s.charAt(i), i);
        }

        Stack<Character> stack = new Stack<>();
        Map<Character, Boolean> visitedMap = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char charr = s.charAt(i);
            if (visitedMap.containsKey(charr) && visitedMap.get(charr)) {
                continue;  // Skip if the character is already added to the result
            }

            // Pop characters from stack if the current character is smaller than the stack's top
            // and there are more occurrences of the popped character in the remaining string
            while (!stack.isEmpty() && stack.peek() > charr && lastIndexMap.get(stack.peek()) > i) {
                visitedMap.put(stack.pop(), false);  // Mark the popped character as not visited
            }

            // Push the current character onto the stack and mark it as visited
            stack.push(charr);
            visitedMap.put(charr, true);
        }

        // Build the result string from the stack
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : stack) {
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }

    public static String removeDuplicateLettersTry(String s) {
        int[] indexArray = new int[26];
        for (int i = 0; i < s.length(); i++) {
            indexArray[s.charAt(i) - 'a'] = i;
        }

        Stack<Character> stack = new Stack<>();
        Set<Character> visitedSet = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char charr = s.charAt(i);
            if (visitedSet.contains(charr)) {
                continue;
            }
            while ((!stack.isEmpty()) && (stack.peek() > charr) && (indexArray[stack.peek() - 'a'] > i)) {
                visitedSet.remove(stack.peek());
                stack.pop();
            }

            stack.push(charr);
            visitedSet.add(charr);
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (char c : stack) {
            stringBuilder.append(c);
        }

        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String original = "qwertyasdfgqwertyz", result;
        result = removeDuplicateLettersTry(original);
        System.out.println(original + " - " + result);
    }
}
