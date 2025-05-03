package data.structures.algorithms.oracle;

import java.util.*;

public class ReorganizeString {
    public static String reorganizeStringTry(String s) {
        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            int diff = a[1] - b[1];
            return (diff == 0) ? b[0] - a[0] : diff;
        });
        for (int i = 0; i < 26; i++) {
            if (charCount[i] > 0) {
                pq.offer(new int[]{i,charCount[i]});
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            int[] a = pq.poll();
            int[] b = pq.poll();

            if(a!=null) {
                sb.append((char) (a[0] + 'a'));
            }
            if(b!=null) {
                sb.append((char) (b[0] + 'a'));
            }
            if(a!=null && a[1] > 1) {
                pq.offer(new int[]{a[0], a[1]-1});
            }
            if(b!=null && b[1] > 1) {
                pq.offer(new int[]{b[0], b[1]-1});
            }
        }
        if(!pq.isEmpty()) {
            sb.append((char) pq.poll()[0]+'a');
        }
        return sb.toString();
    }

    public static String reorganizeString(String s) {
        // Count frequency of each character
        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }
        // Use a max heap to store characters by frequency
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (int i = 0; i < 26; i++) {
            if (charCount[i] > 0) {
                maxHeap.offer(new int[]{i, charCount[i]});
            }
        }
        // If the most frequent character appears more than half the length of the string,
        // it's impossible to reorganize the string.
        if (maxHeap.peek() != null && maxHeap.peek()[1] > (s.length() + 1) / 2) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        // Fill the result with characters
        while (maxHeap.size() > 1) {
            // Get the two most frequent characters
            int[] first = maxHeap.poll();
            int[] second = maxHeap.poll();
            // Append the characters to the result
            result.append((char) (first[0] + 'a'));
            if (second != null)
                result.append((char) (second[0] + 'a'));
            // Decrease their frequencies and put them back if they still have frequency left
            if (first[1] > 1) {
                maxHeap.offer(new int[]{first[0], first[1] - 1});
            }
            if (second != null && second[1] > 1) {
                maxHeap.offer(new int[]{second[0], second[1] - 1});
            }
        }
        // If there's one character left, append it
        if (!maxHeap.isEmpty()) {
            result.append((char) (maxHeap.poll()[0] + 'a'));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(reorganizeStringTry("aaabbb"));
    }
}

/*
767. Reorganize String
Given a string s, rearrange the characters of s so that any two adjacent characters are not the same.
Return any possible rearrangement of s or return "" if not possible.

Example 1:
Input: s = "aab"
Output: "aba"

Example 2:
Input: s = "aaab"
Output: ""

Constraints:
1 <= s.length <= 500
s consists of lowercase English letters.
*/
