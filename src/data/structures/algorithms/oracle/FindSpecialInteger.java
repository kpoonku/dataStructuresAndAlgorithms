package data.structures.algorithms.oracle;

public class FindSpecialInteger {
    public static int findSpecialInteger(int[] arr) {
        int length = arr.length;
        for (int i = 0; i + (length / 4) < length; i++) {
            System.out.println(i + " " + (i + (length / 4)));
            if(arr[i] == arr[i + (length / 4)]) {
                return arr[i];
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int result = findSpecialInteger(new int[]{1,2,2,6,6,6,6,7,10});
        System.out.println("result : " + result);
    }
}
/*
*
1287. Element Appearing More Than 25% In Sorted Array
Given an integer array sorted in non-decreasing order,
* there is exactly one integer in the array that
* occurs more than 25% of the time, return that integer.

Example 1:
Input: arr = [1,2,2,6,6,6,6,7,10]
Output: 6
Example 2:
Input: arr = [1,1]
Output: 1
* */

