package data.structures.algorithms.oracle;

import java.util.*;

// Separate Comparator class
class WordComparator implements Comparator<String> {
    private Map<String, Integer> wordCount;

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
            return (diff == 0) ? word2.compareTo(word1): diff;
        });
        for(String word: wordsMap.keySet()) {
            heap.offer(word);
            if (heap.size() > k) {
                System.out.println("heap.size() : " + heap.size());
                System.out.println("heap.poll() " + heap.poll());
            }
            System.out.println("heap.size() : " + heap.size());
        }
        System.out.println(heap);
        List<String> kWords = new ArrayList<>(heap.size());
        while(!heap.isEmpty()) {
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
