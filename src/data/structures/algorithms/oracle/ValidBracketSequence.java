package data.structures.algorithms.oracle;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidBracketSequence {
    public static boolean isValidBracketSequence(String s) {
        Map<Character, Character> bracketSetMap = new HashMap<>();
        bracketSetMap.put('{', '}');
        bracketSetMap.put('[', ']');
        bracketSetMap.put('(', ')');
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (bracketSetMap.containsKey(c)) {
                stack.push(c);
            } else {
                if (!stack.isEmpty() && bracketSetMap.get(stack.pop()) != c) {
                    return false;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(" is valid {([[)} : " + isValidBracketSequence("{([[)}"));
        System.out.println(" is valid {([])} : " + isValidBracketSequence("{([])}"));
        System.out.println(" is valid {([](} : " + isValidBracketSequence("{([](}"));
        System.out.println(" is valid {([[){ : " + isValidBracketSequence("{([[){"));
        System.out.println(" is valid {([[({ : " + isValidBracketSequence("{([[({"));
    }
}
