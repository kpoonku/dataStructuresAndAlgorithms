package data.structures.algorithms;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSums(int[] nums, int target) {
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(indexMap.containsKey(complement)) {
                return new int[] {indexMap.get(complement), i};
            }
            indexMap.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two Sums is found");
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 3, 5, 8, 7};
        int[] resultNums = new TwoSum().twoSums(nums, 10);
        System.out.println("resultNums[0], resultNums[1] : " + resultNums[0] + " " + resultNums[1]);
    }
}
