package data.structures.algorithms.oracle.tree;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    private final List<String> answers = new ArrayList<>();
    private int maxPairs;

    public static void main(String[] args) {
        GenerateParenthesis generateParenthesis = new GenerateParenthesis();
        List<String> list = generateParenthesis.generateParenthesis(5);
        System.out.println(list);
    }

    /**
     * Generates all combinations of n pairs of well-formed parentheses.
     *
     * @param n the number of pairs of parentheses
     * @return a list of all possible combinations of n pairs of well-formed parentheses
     */
    public List<String> generateParenthesis(int n) {
        maxPairs = n;
        generate(0, 0, "");
        return answers;
    }

    private void generateTry(int openCount, int closeCount, String currentString) {
        if (closeCount == maxPairs && openCount == maxPairs) {
            answers.add(currentString);
            return;
        }
        if (openCount < maxPairs) {
            generate(openCount + 1, closeCount, currentString + "(");
        }
        if (closeCount < openCount) {
            generate(openCount, closeCount + 1, currentString + ")");
        }
    }

    /**
     * Helper method to generate the parentheses using depth-first search.
     */
    private void generate(int openCount, int closeCount, String currentString) {
        if (openCount == maxPairs && closeCount == maxPairs) {
            answers.add(currentString);
            return;
        }
        if (openCount < maxPairs) generate(openCount + 1, closeCount, currentString + "(");
        if (closeCount < openCount) generate(openCount, closeCount + 1, currentString + ")");
    }
}
