package data.structures.algorithms.oracle;

import java.util.HashMap;
import java.util.Map;

/*
* A String can be formed using another string s by repeatedly
* concatenating the anagrams of s any number of times.
* Given a string input_str of length n, find out the length of the
* smallest string s that can be used to create input_str.

Note that the string input_str only consists of lowercase
* english letters.

input_str = "ababbaab";
output : One of the possible strings s can be "aabb",
* First append "abab" and then append "baab".
Another possible string s can be "ab" append the anagrams of s
* in the order : "ab", "ab", "ba" abd "ab".
* */
public class InterviewQuestionSmallestString {
    // Helper function to find GCD of two numbers
    public static int gcd(int a, int b) {
        System.out.println(a + " a - b " + b);
        while (b != 0) {
            System.out.println(a + " a - b " + b);
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Helper function to find GCD of an array of numbers
    public static int findGCD(int[] arr) {
        int result = arr[0];
        System.out.println("length : " + arr.length);
        for (int i = 1; i < arr.length; i++) {
            System.out.println("gcd : " + gcd(result, arr[i]));
            System.out.println("arr[0]" + arr[0]);
            System.out.println("i : " + i + "arr[i] : " + arr[i]);
            result = gcd(result, arr[i]);
        }
        return result;
    }

    // Function to find the smallest string `s`
    public static String smallestString(String inputStr) {
        // Step 1: Count frequency of each character
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : inputStr.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        System.out.println(freqMap);

        // Step 2: Find the GCD of all the frequencies
        int[] freqArray = new int[freqMap.size()];
        int i = 0;
        for (int count : freqMap.values()) {
            freqArray[i] = count;
            System.out.println(i + " - " + freqArray[i++]);
        }

        int commonGCD = findGCD(freqArray);

        System.out.println("commonGCD : " + commonGCD);
        // Step 3: Construct the smallest string `s`
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            result.append(String.valueOf(c).repeat(count / commonGCD));
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Test case
        System.out.println("15, 35 : " + gcd(15, 35));
        String inputStr = "ababbaab";
        String result = "";
        smallestString(inputStr);
        System.out.println("Output: " + result + "\n\n\n"); // Expected output: "ab"
        inputStr = "ababba";
        result = smallestString(inputStr);
        System.out.println("Output: " + result + "\n\n\n"); // Expected output: "ab"
        inputStr = "abcbcacab";
        result = smallestString(inputStr);
        System.out.println("Output: " + result + "\n\n\n");
        inputStr = "aabbbaaaabbb";
        result = smallestString(inputStr);
        System.out.println("Output: " + result + "\n\n\n");
    }
}
