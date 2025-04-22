package data.structures.algorithms.array.strings;

public class N334IncreasingTriplet {
    public static boolean increasingTriplet(int[] nums) {
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (first > num) {
                first = num;
            } else if (second > num) {
                second = num;
            } else {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println("Is increasing sequence : " + increasingTriplet(new int[]{1, 2, 3, 4, 5}));
        System.out.println("Is increasing sequence : " + increasingTriplet(new int[]{5, 4, 3, 2, 1}));
        System.out.println("Is increasing sequence : " + increasingTriplet(new int[]{2, 1, 5, 0, 4, 6}));
    }
}
/*
Given an integer array nums, return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.



Example 1:

Input: nums = [1,2,3,4,5]
Output: true
Explanation: Any triplet where i < j < k is valid.
Example 2:

Input: nums = [5,4,3,2,1]
Output: false
Explanation: No triplet exists.
Example 3:

Input: nums = [2,1,5,0,4,6]
Output: true
Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.


Constraints:

1 <= nums.length <= 5 * 105
-231 <= nums[i] <= 231 - 1

 */
