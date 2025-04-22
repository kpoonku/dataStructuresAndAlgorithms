package data.structures.algorithms.sliding.window;

public class N1493LongestSubarray {
    public static int longestSubarray(int[] nums) {
        int left = 0;
        int maxLength = 0;
        int zeroCount = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                zeroCount++;
            }
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            System.out.println("maxLength " + maxLength);
            System.out.println("left : " + left + " right : " + right);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(longestSubarray(new int[]{0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0}));
    }
}

/*
Given a binary array nums, you should delete one
element from it.

Return the size of the longest non-empty subarray
containing only 1's in the resulting array. Return 0
if there is no such subarray.

Example 1:
Input: nums = [1,1,0,1]
Output: 3
Explanation: After deleting the number in position 2,
[1,1,1] contains 3 numbers with value of 1's.
Example 2:
Input: nums = [0,1,1,1,0,1,1,0,1]
Output: 5
Explanation: After deleting the number in position 4,
[0,1,1,1,1,1,0,1] longest subarray with value of 1's is
[1,1,1,1,1].
Example 3:
Input: nums = [1,1,1]
Output: 2
Explanation: You must delete one element.
Constraints:
1 <= nums.length <= 105
nums[i] is either 0 or 1.
 */
/*
Approach Explanation:
Sliding Window: We will use a sliding window to maintain a valid subarray. A valid subarray is one that contains at most one 0 because we are allowed to delete one element, and deleting a 0 allows the subarray to consist only of 1s.

Two Pointers:

We maintain two pointers: left and right.
The right pointer expands the window, and the left pointer shrinks
the window if the number of 0s exceeds 1.

Every time the window contains at most one 0,
we check the length of that window and keep track of the maximum length
found so far.

Window Validity:

We count how many 0s are in the current window. If the count of 0s exceeds 1,
we move the left pointer to shrink the window until there is at most one 0 left.

Edge Case:

If the array consists of all 1s, deleting one element will reduce the length by 1,
so the longest subarray will be length - 1.

If there's no 1 in the array or if the array has only one element, the result
should be 0 since after deleting the only element, no subarray remains.

Steps:
Initialize the sliding window with two pointers (left = 0, right = 0).

Use the right pointer to expand the window by iterating through the array.

Count the number of 0s in the window. If it's more than 1, move the left
pointer to shrink the window.

For each valid window, calculate its size and update the result with the
maximum size.

Return the result.
 */