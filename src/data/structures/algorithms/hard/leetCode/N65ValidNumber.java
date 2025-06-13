package data.structures.algorithms.hard.leetCode;

public class N65ValidNumber {

    public static void main(String[] args) {
        N65ValidNumber sol = new N65ValidNumber();
        System.out.println(sol.isNumber("2"));           // true
        System.out.println(sol.isNumber("3.14"));        // true
        System.out.println(sol.isNumber("-1.2e-3"));     // true
        System.out.println(sol.isNumber("+.8E5"));       // true
        System.out.println(sol.isNumber("1e"));          // false
        System.out.println(sol.isNumber("e3"));          // false
        System.out.println(sol.isNumber("95a54e53"));    // false
        System.out.println(sol.isNumber("."));           // false
    }

    public boolean isNumber(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        s = s.trim();
        int eIndex = Math.max(s.indexOf('e'), s.indexOf('E'));

        if (eIndex != -1) {
            //Split into base and exponent parts
            String base = s.substring(0, eIndex);
            String exponent = s.substring(eIndex + 1);
            return isValidDecimal(base) && isValidInteger(exponent);
        } else {
            return isValidDecimal(s);
        }
    }

    private boolean isValidInteger(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        int i = 0;
        if (s.charAt(0) == '+' || s.charAt(0) == '-') i++;

        for (; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidDecimal(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        int i = 0;
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            i++;
        }
        boolean hasDigit = false;
        boolean hasDot = false;
        for (; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (c == '.') {
                if (hasDot) return false;
                hasDot = true;
            } else {
                return false;
            }
        }
        return hasDigit;
    }
}
/*
Given a string s, return whether s is a valid number.

For example, all the following are valid numbers: "2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789", while the following are not valid numbers: "abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53".

Formally, a valid number is defined using one of the following definitions:

An integer number followed by an optional exponent.
A decimal number followed by an optional exponent.
An integer number is defined with an optional sign '-' or '+' followed by digits.

A decimal number is defined with an optional sign '-' or '+' followed by one of the following definitions:

Digits followed by a dot '.'.
Digits followed by a dot '.' followed by digits.
A dot '.' followed by digits.
An exponent is defined with an exponent notation 'e' or 'E' followed by an integer number.

The digits are defined as one or more digits.



Example 1:

Input: s = "0"

Output: true

Example 2:

Input: s = "e"

Output: false

Example 3:

Input: s = "."

Output: false



Constraints:

1 <= s.length <= 20
s consists of only English letters (both uppercase and lowercase), digits (0-9), plus '+', minus '-', or dot '.'.
 */
