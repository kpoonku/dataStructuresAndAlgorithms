package data.structures.algorithms;

import java.util.Arrays;

/*
* You are given two non-empty linked lists representing two non-negative integers.
* The digits are stored in reverse order, and each of their nodes contains a single digit.
* Add the two numbers and return the sum as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.
Example 1:
Input: l1 = [2,4,3], l2 = [5,6,4]
Output: [7,0,8]
Explanation: 342 + 465 = 807.
Example 2:
Input: l1 = [0], l2 = [0]
Output: [0]
Example 3:
Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
Output: [8,9,9,9,0,0,0,1]
Constraints:
The number of nodes in each linked list is in the range [1, 100].
0 <= Node.val <= 9
It is guaranteed that the list represents a number that does not have leading zeros.
* */
public class AddTwoSum {

    public int[] addTwoNumsUsingArrays(int[] nums1, int[] nums2) {
        int[] smallArray = nums1;
        int smallArrayLength = nums1.length;
        int[] largeArray = nums2;
        int largeArrayLength = nums2.length;
        int[] resultArray = new int[largeArrayLength+1];
        int resultArrayLength = largeArrayLength+1;

        if (nums2.length <= nums1.length) {
            smallArray = nums2;
            smallArrayLength = nums2.length;
            largeArray = nums1;
            largeArrayLength = nums1.length;
        }
        int carryOver = 0;
        while (smallArrayLength > 0) {
            System.out.println("smallArrayLength : " + smallArrayLength);
            System.out.println("largeArrayLength : " + largeArrayLength);
            int value = smallArray[--smallArrayLength] + largeArray[--largeArrayLength] + carryOver;
            carryOver = value / 10;
            System.out.println("Value: " + value);
            System.out.println("carryOver : " + carryOver);
            System.out.println("value / 10 :: " + value % 10);
            resultArray[--resultArrayLength] = value % 10;

        }
        while(largeArrayLength>0) {
            System.out.println("largeArrayLength : " + largeArrayLength);
            System.out.println("resultArrayLength : " + resultArrayLength);
            int value = largeArray[--largeArrayLength] + carryOver;
            carryOver = value / 10;
            resultArray[--resultArrayLength] = value % 10;
            System.out.println("carryOver : " + carryOver);
            System.out.println("value / 10 :: " + value % 10);
        }
        System.out.println("resultArrayLength : " + resultArrayLength);
        resultArray[--resultArrayLength] = carryOver;
        return resultArray;
    }

    public static void main(String[] args) {
        int[] nums1 = {9,9,9,9};
        int[] nums2 = {9,9,9,9,9,9,9,9,9};
        int[] resultArray= new AddTwoSum().addTwoNumsUsingArrays(nums1, nums2);
        System.out.println(9999+999999999);
        System.out.println(Arrays.toString(resultArray));
    }
}
