package data.structures.algorithms.backtracking;

import java.util.*;

public class N17LetterCombinationOfPhoneNumbers {
    public static void main(String[] args) {
        N17LetterCombinationOfPhoneNumbers sol = new N17LetterCombinationOfPhoneNumbers();
        System.out.println(sol.letterCombinations("27"));
    }

    public Set<String> letterCombinations(String digits) {
        Set<String> result = new HashSet<>();
        Map<Character, String> phoneMap = new HashMap<>();
        phoneMap.put('2', "abc");
        phoneMap.put('3', "def");
        phoneMap.put('4', "ghi");
        phoneMap.put('5', "jkl");
        phoneMap.put('6', "mno");
        phoneMap.put('7', "pqrs");
        phoneMap.put('8', "tuv");
        phoneMap.put('9', "wxyz");

        Stack<String> stack = new Stack<>();
        stack.push("");
        while (!stack.isEmpty()) {
            String current = stack.pop();

            if (current.length() == digits.length()) {
                result.add(current);
            } else {
                char digit = digits.charAt(current.length());
                String letters = phoneMap.get(digit);

                for (char letter : letters.toCharArray()) {
                    stack.push(current + letter);
                }
            }
        }
        return result;
    }
}

