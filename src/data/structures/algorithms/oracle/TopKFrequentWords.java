package data.structures.algorithms.oracle;

import java.util.*;

// Separate Comparator class
class WordComparator implements Comparator<String> {
    private final Map<String, Integer> wordCount;

    public WordComparator(Map<String, Integer> wordCount) {
        this.wordCount = wordCount;
    }

    @Override
    public int compare(String word1, String word2) {
        // Compare by frequency first
        System.out.println("word1 : " + word1 + " , word2 : " + word2);
        int diff = wordCount.get(word1) - wordCount.get(word2);
        System.out.println(word2 + ".compareTo(" + word1 + ") : " + word2.compareTo(word1));
        System.out.println("(diff == 0) : " + (diff == 0));
        // If frequencies are the same, compare in reverse lexicographical order
        return (diff == 0) ? word2.compareTo(word1) : diff;
    }
}

public class TopKFrequentWords {
    public static List<String> topKFrequentTry(String[] words, int k) {
        Map<String, Integer> wordsMap = new HashMap<>();
        for (String word : words) {
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }
        System.out.println(wordsMap);

        PriorityQueue<String> heap = new PriorityQueue<>((word1, word2) -> {
            int diff = wordsMap.get(word1) - wordsMap.get(word2);
            return (diff == 0) ? word2.compareTo(word1) : diff;
        });
        for (String word : wordsMap.keySet()) {
            heap.offer(word);
            if (heap.size() > k) {
                System.out.println("heap.size() : " + heap.size());
                System.out.println("heap.poll() " + heap.poll());
            }
            System.out.println("heap.size() : " + heap.size());
        }
        System.out.println(heap);
        List<String> kWords = new ArrayList<>(heap.size());
        while (!heap.isEmpty()) {
            kWords.add(heap.poll());
        }
        Collections.reverse(kWords);
        return kWords;
    }

    public static List<String> topKFrequent(String[] words, int k) {
        // Map to store the frequency count of each word
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        System.out.println(wordCount);
        // Priority Queue to store words based on frequency and lexicographical order
        PriorityQueue<String> heap = new PriorityQueue<>(new WordComparator(wordCount));

        // Add words to the heap
        for (String word : wordCount.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();  // Maintain heap size at k
        }

        // Convert heap to result list
        List<String> topKWords = new ArrayList<>();
        while (!heap.isEmpty()) {
            topKWords.add(heap.poll());
        }

        // Reverse the list to get words in descending order of frequency
        Collections.reverse(topKWords);
        return topKWords;
    }

    public static void main(String[] args) {
        //["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
        String[] words = {"the", "day", "is", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
        int k = 4;
        List<String> result = topKFrequentTry(words, k);
        System.out.println(result);
    }
}

/*
692. Top K Frequent Words
Given an array of strings words and an integer k, return the k most frequent strings.
Return the answer sorted by the frequency from highest to lowest. Sort the words with the same
frequency by
their lexicographical order.

Example 1:
Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.

Example 2:
Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number
of occurrence being 4, 3, 2 and 1 respectively.


Constraints:

1 <= words.length <= 500
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
k is in the range [1, The number of unique words[i]]


Follow-up: Could you solve it in O(n log(k)) time and O(n) extra space?
 */