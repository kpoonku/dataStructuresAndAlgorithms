package data.structures.algorithms.array.strings;

import java.util.Arrays;

public class N238ProductExceptionSelf {
    public static int[] productExceptSelf(int[] num) {
        int length = num.length;
        int[] result = new int[length];
        result[0] = 1;
        for (int i = 1; i < length; i++) {
            result[i] = num[i - 1] * result[i - 1];
        }
        int sp = 1;
        for (int i = length - 1; i >= 0; i--) {
            result[i] = sp * result[i];
            sp = sp * num[i];
        }
        System.out.println(Arrays.toString(result));
        return result;
    }

    public static void main(String[] args) {
        System.out.println(productExceptSelf(new int[]{1, 2, 3, 4}));
    }
}
/*
Given an integer array nums, return an array answer such that answer[i]
 is equal to the product of all the elements of nums except nums[i].

The product of any prefix or suffix of nums is guaranteed to fit in a
32-bit integer.

You must write an algorithm that runs in O(n) time and without using
the division operation.

Example 1:

Input: nums = [1,2,3,4]
Output: [24,12,8,6]
Example 2:

Input: nums = [-1,1,0,-3,3]
Output: [0,0,9,0,0]
 */
