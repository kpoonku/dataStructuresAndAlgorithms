package data.structures.algorithms.array.strings;

public class N1071GreatestCommonDivisorOfStrings {
    public static void main(String[] args) {
        System.out.println(gcd(3, 6));
        String str1 = "ABCABC", str2 = "ABC";
        System.out.println("Output : " + gcdOfStrings(str1, str2));
        str1 = "ABABAB"; str2 = "ABAB";
        System.out.println("Output : " + gcdOfStrings(str1, str2));
        str1 = "LEET"; str2 = "CODE";
        System.out.println("Output : " + gcdOfStrings(str1, str2));
    }

    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }

    public static String gcdOfStrings(String str1, String str2) {
        int gcdLen = gcd(str1.length(), str2.length());
        System.out.println("gcdLen " + gcdLen);
        String repeatString = str1.substring(0, gcdLen);
        System.out.println("repeatString : "+ repeatString);
        String newStr1 = repeatString(repeatString, str1.length() / gcdLen);
        String newStr2 = repeatString(repeatString, str2.length() / gcdLen);
        System.out.println("Str1 : " + newStr1);
        System.out.println("Str2 : " + newStr2);
        if (newStr2.equals(str2) && newStr1.equals(str1)) {
            return repeatString;
        }
        return "";
    }

    public static String repeatString(String candidate, int times) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < times) {
            sb.append(candidate);
            i++;
        }
        return sb.toString();
    }
}
/*
For two strings s and t, we say "t divides s" if and only if s = t + t + t + ... + t + t
(i.e., t is concatenated with itself one or more times).
Given two strings str1 and str2, return the largest string x such that x divides both str1 and str2.

Example 1:
Input: str1 = "ABCABC", str2 = "ABC"
Output: "ABC"

Example 2:
Input: str1 = "ABABAB", str2 = "ABAB"
Output: "AB"

Example 3:
Input: str1 = "LEET", str2 = "CODE"
Output: ""
*/
