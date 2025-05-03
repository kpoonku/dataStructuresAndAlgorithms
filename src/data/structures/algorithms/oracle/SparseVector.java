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

/*
1570. Dot Product of Two Sparse Vectors
Given two sparse vectors, compute their dot product.

Implement class SparseVector:

SparseVector(nums) Initializes the object with the vector nums
dotProduct(vec) Compute the dot product between the instance of SparseVector and vec
A sparse vector is a vector that has mostly zero values, you should store the sparse vector efficiently and compute the dot product between two SparseVector.

Follow up: What if only one of the vectors is sparse?



Example 1:

Input: nums1 = [1,0,0,2,3], nums2 = [0,3,0,4,0]
Output: 8
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 1*0 + 0*3 + 0*0 + 2*4 + 3*0 = 8
Example 2:

Input: nums1 = [0,1,0,0,0], nums2 = [0,0,0,0,2]
Output: 0
Explanation: v1 = SparseVector(nums1) , v2 = SparseVector(nums2)
v1.dotProduct(v2) = 0*0 + 1*0 + 0*0 + 0*0 + 0*2 = 0
Example 3:

Input: nums1 = [0,1,0,0,2,0,0], nums2 = [1,0,0,0,3,0,4]
Output: 6


Constraints:

n == nums1.length == nums2.length
1 <= n <= 10^5
0 <= nums1[i], nums2[i] <= 100
 */