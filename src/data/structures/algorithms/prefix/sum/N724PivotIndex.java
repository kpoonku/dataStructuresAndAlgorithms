package data.structures.algorithms.prefix.sum;

public class N724PivotIndex {
    public static int pivotIndex(int[] nums) {
        int totalSum = 0, leftSum = 0;
        for (int num : nums) {
            totalSum += num;
        }
        for (int i = 0; i < nums.length; i++) {
            if(leftSum == totalSum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,7,3,6,5,6};
        System.out.println("Pivot Index : " + pivotIndex(nums));
        nums = new int[]{1,2,3};
        System.out.println("Pivot Index : " + pivotIndex(nums));
        nums = new int[]{2,1,-1};
        System.out.println("Pivot Index : " + pivotIndex(nums));
    }
}
/*
Given an array of integers nums, calculate the pivot index of
this array.

The pivot index is the index where the sum of all the numbers
strictly to the left of the index is equal to the sum of all
the numbers strictly to the index's right.

If the index is on the left edge of the array, then the left sum
is 0 because there are no elements to the left. This also applies
to the right edge of the array.

Return the leftmost pivot index. If no such index exists, return -1.

Example 1:
Input: nums = [1,7,3,6,5,6]
Output: 3
Explanation:
The pivot index is 3.
Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
Right sum = nums[4] + nums[5] = 5 + 6 = 11

Example 2:
Input: nums = [1,2,3]
Output: -1
Explanation:
There is no index that satisfies the conditions in the problem statement.

Example 3:
Input: nums = [2,1,-1]
Output: 0
Explanation:
The pivot index is 0.
Left sum = 0 (no elements to the left of index 0)
Right sum = nums[1] + nums[2] = 1 + -1 = 0

Constraints:

1 <= nums.length <= 104
-1000 <= nums[i] <= 1000

Note: This question is the same as 1991: https://leetcode.com/problems/find-the-middle-index-in-array/
 */
/*
To solve this problem, we need to find the pivot index of an array, where the sum of the numbers to the left of the index is equal to the sum of the numbers to the right of the index.

Approach:
Calculate Total Sum of the Array: First, calculate the total sum of all elements in the array. This will help us compute the right sum efficiently by keeping track of the left sum as we iterate over the array.

Iterate and Track Left Sum: As we iterate over the array, we can maintain a running sum of the elements to the left of the current index (leftSum). We can compute the right sum by subtracting the leftSum and the value at the current index from the totalSum.

Check for Pivot: For each index, if the leftSum is equal to the right sum (which can be computed as totalSum - leftSum - nums[i]), then that index is the pivot index. If such an index exists, return the index.

Update Left Sum: After checking the condition, update the leftSum to include the current element (leftSum += nums[i]).

Return the Pivot Index: If no pivot index is found after iterating through the array, return -1.
 */
