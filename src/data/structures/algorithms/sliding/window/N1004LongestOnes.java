package data.structures.algorithms.sliding.window;

public class N1004LongestOnes {
    public static int longestOnes(int[] nums, int k) {
        int left = 0;  // Left pointer of the window
        int zeroCount = 0;  // Number of zeros in the current window
        int maxLength = 0;  // Maximum length of the window with at most k zeros
        for (int right = 0; right < nums.length; right++) {
            // If we encounter a zero, increase the zero count
            if (nums[right] == 0) {
                zeroCount++;
            }
            // If zero count exceeds k, move the left pointer to reduce the window size
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }
            // Calculate the maximum length of the valid window
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 3));
    }
}
/*
Given a binary array nums and an integer k, return the maximum number
of consecutive 1's in the array if you can flip at most k 0's.

Example 1:
Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
Output: 6
Explanation: [1,1,1,0,0,1,1,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Example 2:
Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
Output: 10
Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.

Constraints:
1 <= nums.length <= 105
nums[i] is either 0 or 1.
0 <= k <= nums.length
*/
/*
To understand the logic of this problem and how the sliding window approach works, let's break
it down into smaller, digestible steps that align with how our brain processes information.

### Problem Overview:
- You are given a binary array (`nums`), which consists of `0`s and `1`s, and an integer `k`
(the maximum number of `0`s you can flip into `1`s).
- The goal is to find the longest subarray (continuous sequence) where the number of `0`s is
**at most** `k`. You can flip up to `k` zeros into ones.
- This will allow us to maximize the length of the subarray containing mostly `1`s.

### Step-by-Step Breakdown of the Logic:

1. **Two Pointer Technique (Sliding Window)**:
   - **Think of it like a sliding window**: Imagine that you have a window or a frame that starts
   small and can "slide" across the array to check different sections of it. This window is
   flexible: it can expand to include more elements (by moving the right pointer), and it
   can shrink (by moving the left pointer) when necessary.

2. **Tracking the number of `0`s**:
   - **Focus on the number of zeros**: We are allowed to flip up to `k` zeros, so we need to keep
   track of how many zeros are in the current window. If the count of zeros exceeds `k`, we have
   to shrink the window from the left (move the `left` pointer to the right) until the number of
   zeros is within the allowed limit (`<= k`).
   - **Window Expansion**: As we move the `right` pointer across the array, we increase the window
   size. If the element at `right` is `0`, we count it. If it's `1`, we don't care (because we can
   always keep `1`s).

3. **Shrinking the Window When Necessary**:
   - If at any point the number of `0`s in the window exceeds `k`, we **shrink** the window
   from the left side. This means moving the `left` pointer to the right. When we move the left pointer past a `0`, we decrease the zero count. We keep adjusting the window until the number of zeros is within the allowed limit (`<= k`).

4. **Keep Track of the Maximum Window Size**:
   - After adjusting the window (expanding and possibly shrinking), we want to check the current
   size of the window (`right - left + 1`). This represents the number of elements in the current
   valid subarray.
   - If the current size is larger than the previous largest size, we update our answer to reflect
   this new maximum size.

5. **Final Result**:
   - After going through the entire array with the sliding window, the maximum window size we
   tracked will be our answer.

### Example Breakdown (Step by Step):

#### Example: `nums = [1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0]`, `k = 2`

- Start with an empty window:
  - **Left pointer (`left`)** starts at the beginning of the array.
  - **Right pointer (`right`)** will move across the array, expanding the window.

#### Iteration 1:
- Right pointer moves to the first element (`nums[0] = 1`).
- No `0` is encountered yet, so the window size is 1.

#### Iteration 2:
- Right pointer moves to `nums[1] = 1`.
- Again, no `0` encountered, so the window size is now 2.

#### Iteration 3:
- Right pointer moves to `nums[2] = 1`.
- Still no `0`, so the window size is now 3.

#### Iteration 4:
- Right pointer moves to `nums[3] = 0`.
- Now we encounter a `0`. We count the `0`s. `zeroCount = 1`.
- The window is still valid (`zeroCount <= k`), so we continue.

#### Iteration 5:
- Right pointer moves to `nums[4] = 0`.
- We encounter another `0`. Now, `zeroCount = 2`, which is still valid.
- The window size is now 5 (`[1,1,1,0,0]`).

#### Iteration 6:
- Right pointer moves to `nums[5] = 0`.
- Now, `zeroCount = 3`, which exceeds `k = 2`. We need to shrink the window by moving the `left`
pointer.
- Move `left` to `nums[1] = 1`. The `zeroCount` is now 2 (`[1,1,1,0]`), so the window is valid
again.

#### Iteration 7:
- Continue expanding the window by moving the `right` pointer to the next elements and shrinking
the left side if necessary.
- Keep tracking the maximum window size (`right - left + 1`).

#### Result:
- The maximum window size where the number of zeros is `<= k` is 6, which happens with the
window `[1,1,1,0,0,1]`.

### Key Insights:
1. **Window size tracking**: We use the two pointers to expand and shrink the window dynamically,
keeping track of the window's size when the condition is satisfied.
2. **Efficient shrinking**: By moving the left pointer when the window becomes invalid (i.e.,
zero count exceeds `k`), we avoid checking all possible subarrays and instead focus on the
largest valid window.
3. **No extra space required**: We only use variables to track the window’s size and the number
of zeros, making the solution space-efficient.

### Final Thought:
Imagine you have a window that can grow as long as it doesn’t have more than `k` zeros.
Every time it does, you slide the window from the left side to shrink it and make it valid again.
You continue until you've explored the entire array, and you keep track of the maximum length
where the window was valid. This is the essence of the sliding window technique!
 */