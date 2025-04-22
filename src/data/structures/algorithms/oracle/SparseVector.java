package data.structures.algorithms.oracle;

import java.util.HashMap;
import java.util.Map;

public class SparseVector {

    private final Map<Integer, Integer> numberMap = new HashMap<>();

    public SparseVector(int[] nums) {
        populateMap(nums);
    }

    public static void main(String[] args) {
        SparseVector v1 = new SparseVector(new int[]{1, 0, 0, 2, 3});
        SparseVector v2 = new SparseVector(new int[]{0, 3, 0, 4, 0});
        int ans = v1.dotProduct(v2);
        System.out.println("Output: " + ans);
        v1 = new SparseVector(new int[]{0, 1, 0, 0, 0});
        v2 = new SparseVector(new int[]{0, 0, 0, 0, 2});
        ans = v1.dotProduct(v2);
        System.out.println("Output: " + ans);
        v1 = new SparseVector(new int[]{0, 1, 0, 0, 2, 0, 0});
        v2 = new SparseVector(new int[]{1, 0, 0, 0, 3, 0, 4});
        ans = v1.dotProduct(v2);
        System.out.println("Output: " + ans);
    }

    public void populateMap(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0 && nums[i] <= 100) {
                numberMap.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        if (numberMap.size() != vec.numberMap.size()) {
            return 0;
        }
        int result = 0;
        for (int index : numberMap.keySet()) {
            result += vec.numberMap.getOrDefault(index, 0)
                    * numberMap.getOrDefault(index, 0);
        }
        return result;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
