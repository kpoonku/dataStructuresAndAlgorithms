package data.structures.algorithms.hashmap.set;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class N1207UniqueOccurrences {
    public static boolean uniqueOccurrences(int[] arr) {
        Set<Integer> numSet = new HashSet<>();
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : arr) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
        return freqMap.values().stream().allMatch(new HashSet<>()::add);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 1, 1, 3};
        System.out.println("Is it Unique : " + uniqueOccurrences(nums));
        nums = new int[]{1, 2};
        System.out.println("Is it Unique : " + uniqueOccurrences(nums));
        nums = new int[]{-3, 0, 1, -3, 1, 1, 1, -3, 10, 0};
        System.out.println("Is it Unique : " + uniqueOccurrences(nums));
    }
}
/*
1207. Unique Number of Occurrences
Given an array of integers arr, return true if the number of
occurrences of each value in the array is unique or false otherwise.

Example 1:
Input: arr = [1,2,2,1,1,3]
Output: true
Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1.
No two values have the same number of occurrences.

Example 2:
Input: arr = [1,2]
Output: false

Example 3:
Input: arr = [-3,0,1,-3,1,1,1,-3,10,0]
Output: true
*/
