package data.structures.algorithms.oracle.tree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordPatternMatch {
    private static Set<String> visited;
    private static Map<Character, String> patternMapping;
    private String pattern;
    private String str;
    private static int patternLength;
    private static int stringLength;
    private static int count = 0;

    public boolean wordPatternMatch(String pattern, String str) {
        visited = new HashSet<>();
        patternMapping = new HashMap<>();
        this.pattern = pattern;
        this.str = str;
        patternLength = pattern.length();
        stringLength = str.length();
        System.out.println("patternLength : " + patternLength);
        System.out.println("stringLength : " + stringLength);
        return depthFirstSearch(0, 0);
    }

    private boolean depthFirstSearch(int patternIndex, int strIndex) {
        System.out.println("Loop Counter : " + count++ + "\n\n");
        // If we've processed the entire pattern and string, return true
        if (patternIndex == patternLength && strIndex == stringLength) {
            return true;
        }

        // If one is finished but not both, it's a mismatch
        if (patternIndex == patternLength || strIndex == stringLength) {
            return false;
        }
        System.out.println("patternIndex : " + patternIndex);
        System.out.println("strIndex : " + strIndex);
        System.out.println("visited Set : " + visited);
        System.out.println("Pattern Mapping: " + patternMapping);

        char currentPatternChar = pattern.charAt(patternIndex);

        System.out.println("pattern : " + currentPatternChar);
        // Try to match the pattern character with every possible substring
        for (int end = strIndex+1; end <= stringLength; ++end) {
            System.out.println("end : " + end);
            String currentSubString = str.substring(strIndex, end);
            System.out.println("sub-string : " + currentSubString);
            // If the pattern character is already mapped, check for equality
            System.out.println("1st if : " + patternMapping.containsKey(currentPatternChar));
            System.out.println("2nd if : " + (!visited.contains(currentSubString)));
            if (patternMapping.containsKey(currentPatternChar)) {
                System.out.println(currentPatternChar + " != " +  currentSubString + " : " + !patternMapping.get(currentPatternChar).equals(currentSubString));
                if (!patternMapping.get(currentPatternChar).equals(currentSubString)) {
                    continue; // Skip this substring, as it doesn't match the mapping
                }
                // If it matches, recurse with the next part of the pattern and string
                if (depthFirstSearch(patternIndex + 1, end)) {
                    return true;
                }
            } else if (!visited.contains(currentSubString)) {
                // If the pattern character isn't mapped, try to create a new mapping
                patternMapping.put(currentPatternChar, currentSubString);
                visited.add(currentSubString);

                // Recurse with the next part of the pattern and string
                if (depthFirstSearch(patternIndex + 1, end)) {
                    return true;
                }

                // If the mapping didn't work, backtrack
                visited.remove(currentSubString);
                patternMapping.remove(currentPatternChar);
            }
        }

        // If no match is found, return false
        return false;
    }

    public static void main(String[] args) {
        WordPatternMatch patternMatch = new WordPatternMatch();
        boolean result = patternMatch.wordPatternMatch("aba", "catdogcat");
        System.out.println("Result: " + result);  // Should print true
    }
}
