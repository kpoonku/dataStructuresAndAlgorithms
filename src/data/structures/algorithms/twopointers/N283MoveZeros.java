package data.structures.algorithms.twopointers;

import java.util.Arrays;

public class N283MoveZeros {
    public static void moveZeroes(int[] nums) {
        int write = 0;
        for (int read = 0; read < nums.length; read++) {
            if (nums[read] != 0) {
                if (nums[write] == 0) { // or if(write!=read) {
                    swap(nums, read, write);
                }
                write++;
            }
        }
        System.out.println("nums : " + Arrays.toString(nums));
    }

    public static void swap(int[] nums, int i1, int i2) {
        int temp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZeroes(nums);
    }
}
/*
Given an integer array nums, move all 0's to the end of it while
 maintaining the relative order of the non-zero elements.

Note that you must do this in-place without making a copy of the
array.
Example 1:

Input: nums = [0,1,0,3,12]
Output: [1,3,12,0,0]
Example 2:

Input: nums = [0]
Output: [0]

Constraints:

1 <= nums.length <= 104
-231 <= nums[i] <= 231 - 1
 */
