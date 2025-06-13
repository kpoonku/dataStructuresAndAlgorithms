package data.structures.algorithms.hard.leetCode;

public class N42TrappingRainWater {
    public static void main(String[] args) {
        int[] height = new int[]{4, 2, 0, 3, 2, 5};
        N42TrappingRainWater sol = new N42TrappingRainWater();
        System.out.println("Water Units : " + sol.trap(height));
        height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Water Units : " + sol.trap(height));
    }

    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] <= height[right]) {
                if (leftMax < height[left]) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (rightMax <= height[right]) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }

        return water;
    }
}
/*
Given n non-negative integers representing an elevation map where the
width of each bar is 1, compute how much water it can trap after raining.

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by
array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section)
are being trapped.

Example 2:
Input: height = [4,2,0,3,2,5]
Output: 9

Constraints:
n == height.length
1 <= n <= 2 * 104
0 <= height[i] <= 105
*/
/*
Absolutely! Let’s break down the Trapping Rain Water problem in a way that’s easy for beginners to understand.

🌧️ Problem in Simple Words:
Imagine you're looking at a row of vertical bars of different heights. It just rained, and you want to find out how much water is trapped between the bars.

Each number in the array tells you the height of the bar at that position. Water can collect in the valleys between taller bars.

🔍 Visual Example:
Input:

ini
Copy
Edit
height = [0,1,0,2,1,0,1,3,2,1,2,1]
Draw it like this:

markdown
Copy
Edit
        |
    |   |   |
|   | | | | |
Water collects in the low spots between taller bars.

In total, this example can trap 6 units of water (think of "blue blocks" between gray bars).

🎯 Goal:
Find how much water is trapped after it rains.

🧠 Key Idea
At any position i, the amount of water it can trap is:

ini
Copy
Edit
water_at_i = min(tallest_left, tallest_right) - height[i]
Think about it this way:

If you're standing at height[i], the amount of water above you depends on how tall the bars are on your left and right.

Water level is the shorter of the tallest bar on your left and the tallest bar on your right.

You can only trap water if both sides are taller than you.

✅ Efficient Way to Solve It: Two Pointers
We’ll use two fingers (pointers): one starting from the left, and one from the right.

🔧 Steps:
Start with left = 0, right = end of array.

Keep track of the tallest bar on the left (leftMax) and on the right (rightMax).

At each step:

If the left bar is shorter, calculate water at left and move left++.

If the right bar is shorter, calculate water at right and move right--.

✅ Simple Java Code
java
Copy
Edit
public class Solution {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int water = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                // Water depends on leftMax
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                // Water depends on rightMax
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }

        return water;
    }
}
🧪 Let's Walk Through This Example:
java
Copy
Edit
height = [4,2,0,3,2,5]
Visually:

markdown
Copy
Edit
        |
    |   |
|   |   |
Water gets trapped between the bars.

Total water collected = 9

📌 Summary for Beginners
Concept Explanation
What is height[]?   A list of vertical bars (numbers represent their height)
What are we finding?    How much rainwater is stuck between the bars
What's the trick?   Water at a position = min(tallest to left, tallest to right) - current height
Why two pointers?   So we can scan from both ends and use less memory

Would you like a step-by-step table showing how left, right, and water change? Or want to see this in Python?
 */
/*
if( leftMax < height[left]) {
                   leftMax = height[left];
               } else {
                   water += leftMax - height[left];
               } - explain this beginner level
ChatGPT said:
Absolutely! Let's go step by step and explain this line of code in a beginner-friendly way.

🎯 The Goal of This Code:
We want to calculate how much water can be trapped at the current left position in the array.

The Code:
java
Copy
Edit
if (leftMax < height[left]) {
    leftMax = height[left];
} else {
    water += leftMax - height[left];
}
🧠 Variables Explained:
height[left]: The height of the current bar we're standing on.

leftMax: The tallest bar we've seen so far on the left side.

leftMax - height[left]: This tells us how much water can sit on top of the current bar.

💡 What It Means:
🔷 Case 1: Current bar is taller than or equal to all previous bars
java
Copy
Edit
if (leftMax < height[left])
This means: "Hey, this current bar is the new tallest bar on the left."

So we update leftMax to this new taller bar:

java
Copy
Edit
leftMax = height[left];
Water cannot be trapped here because it’s the highest so far.

🔷 Case 2: Current bar is shorter than the tallest on the left
java
Copy
Edit
else {
    water += leftMax - height[left];
}
This means: "The current bar is lower than the tallest bar we’ve seen on the left."

So it forms a bowl, and water can sit on top of it.

The amount of water = leftMax - height[left]

Add that amount to the total water.

📊 Example
Imagine this part of the array:

ini
Copy
Edit
height = [4, 2]
Let’s go step-by-step:

Start with left = 0, height[0] = 4, so leftMax = 4

Move to left = 1, height[1] = 2

Now:

leftMax = 4

height[left] = 2

Water trapped = 4 - 2 = 2

So, we add 2 to the total water.

🧃 Visual Example:
arduino
Copy
Edit
    |      ← leftMax = 4
|   |      ← height[left] = 2
We can trap 2 units of water on top of the small bar.

✅ Summary (in plain words):
If the current bar is taller than anything to the left, update the tallest.

If the current bar is shorter, it means water can sit on it.
The amount of water is the difference between the tallest bar to the left and the current height.

Let me know if you'd like a diagram or animation-style explanation!
 */