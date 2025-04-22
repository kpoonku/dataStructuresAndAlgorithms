package data.structures.algorithms.hashmap.set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class N2215FindDifference {
    public static List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for(int num: nums1) {
            set1.add(num);
        }
        for(int num: nums2) {
            set2.add(num);
        }

        System.out.println("Set 1 : " + set1);
        System.out.println("Set 2 : " + set2);
        Set<Integer> distinctInNum1 = new HashSet<>(set1);
        distinctInNum1.removeAll(set2);

        Set<Integer> distinctInNum2 = new HashSet<>(set2);
        distinctInNum2.removeAll(set1);

        System.out.println("distinctInNum 2 : " + distinctInNum2);
        System.out.println("distinctInNum 1 : " + distinctInNum1);

        result.add(new ArrayList<>(distinctInNum1));
        result.add(new ArrayList<>(distinctInNum2));

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {2, 4, 6};
        System.out.println("List : " + findDifference(nums1, nums2));
    }
}
/*
2215. Find the Difference of Two Arrays
Given two 0-indexed integer arrays nums1 and nums2, return a list answer of size 2 where:

answer[0] is a list of all distinct integers in nums1 which are not present in nums2.
answer[1] is a list of all distinct integers in nums2 which are not present in nums1.
Note that the integers in the lists may be returned in any order.

Example 1:

Input: nums1 = [1,2,3], nums2 = [2,4,6]
Output: [[1,3],[4,6]]
Explanation:
For nums1, nums1[1] = 2 is present at index 0 of nums2, whereas nums1[0] = 1 and nums1[2] = 3
are not present in nums2. Therefore, answer[0] = [1,3].
For nums2, nums2[0] = 2 is present at index 1 of nums1, whereas nums2[1] = 4 and nums2[2] = 6
are not present in nums1. Therefore, answer[1] = [4,6].
Example 2:

Input: nums1 = [1,2,3,3], nums2 = [1,1,2,2]
Output: [[3],[]]
Explanation:
For nums1, nums1[2] and nums1[3] are not present in nums2. Since nums1[2] == nums1[3],
their value is only included once and answer[0] = [3].
Every integer in nums2 is present in nums1. Therefore, answer[1] = [].
*/
/*
Approach:
Convert Arrays to Sets: We will convert both arrays (nums1 and nums2) to sets to handle
duplicates automatically and enable efficient set operations.
Find Differences:
The elements in nums1 but not in nums2 can be found using set1.removeAll(set2).
The elements in nums2 but not in nums1 can be found using set2.removeAll(set1).
Return Results: The result should be a list of two lists: the first for elements unique to
nums1, and the second for elements unique to nums2.
*/
