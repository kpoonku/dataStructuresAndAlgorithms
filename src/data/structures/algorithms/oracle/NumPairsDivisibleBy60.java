package data.structures.algorithms.oracle;

import java.util.HashMap;
import java.util.Map;

public class NumPairsDivisibleBy60 {
    public static void main(String[] args) {
        //int[] time = {30,20,150,100,40};
        int[] time = {30, 180, 80, 50, 60, 20, 40, 120, 40, 30};
        System.out.println(new NumPairsDivisibleBy60().numPairsDivisibleBy60(time));
    }

    public int numPairsDivisibleBy60(int[] time) {
        // Map to store the frequency of remainders
        Map<Integer, Integer> countMap = new HashMap<>();
        int pairCount = 0;

        // Iterate through each time
        for (int t : time) {
            int remainder = t % 60;
            int complement = (60 - remainder) % 60; // Complement of the current remainder
            // If the complement remainder exists in the map, add its count to pairCount
            if (countMap.containsKey(complement)) {
                pairCount += countMap.get(complement);
            }
            // Increment the count of the current remainder in the map
            countMap.put(remainder, countMap.getOrDefault(remainder, 0) + 1);
        }
        return pairCount;
    }
}

/*
1010. Pairs of Songs With Total Durations Divisible by 60
You are given a list of songs where the ith song has a duration of time[i] seconds.

Return the number of pairs of songs for which their total duration
in seconds is divisible by 60. Formally, we want the number of indices i,
j such that i < j with (time[i] + time[j]) % 60 == 0.

Example 1:

Input: time = [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
Example 2:

Input: time = [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120,
which is divisible by 60.
 */
