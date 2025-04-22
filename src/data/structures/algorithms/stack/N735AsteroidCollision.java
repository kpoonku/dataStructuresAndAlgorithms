package data.structures.algorithms.stack;

import java.util.Arrays;
import java.util.Stack;

public class N735AsteroidCollision {
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int num : asteroids) {
            if (num < 0) {
                while (!stack.isEmpty() && stack.peek() > 0) {
                    int top = stack.peek();
                    if (top <= Math.abs(num)) {
                        stack.pop();
                        break;
                    } else {
                        break;
                    }

                }
            } else {
                stack.push(num);
            }
            if (stack.isEmpty() || stack.peek() < 0) {
                stack.push(num);
            }
        }
        int[] result = new int[stack.size()];
        for (int i = 0; i < stack.size(); i++) {
            result[i] = stack.pop();
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("Output: " + Arrays.toString(asteroidCollision(new int[]{5, 10, -5})));
        System.out.println("Output: " + Arrays.toString(asteroidCollision(new int[]{8, -8})));
        System.out.println("Output: " + Arrays.toString(asteroidCollision(new int[]{10, 2, -5})));
        System.out.println("Output: " + Arrays.toString(asteroidCollision(new int[]{-10, 2})));
    }
}

/*
We are given an array asteroids of integers representing asteroids in a row. The indices of the asteriod in the array represent their relative position in space.

For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.



Example 1:

Input: asteroids = [5,10,-5]
Output: [5,10]
Explanation: The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
Example 2:

Input: asteroids = [8,-8]
Output: []
Explanation: The 8 and -8 collide exploding each other.
Example 3:

Input: asteroids = [10,2,-5]
Output: [10]
Explanation: The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.


Constraints:

2 <= asteroids.length <= 104
-1000 <= asteroids[i] <= 1000
asteroids[i] != 0
 */
/*
Explanation:
Stack for Simulating Movement:

The stack holds the asteroids that are still alive.

When an asteroid moves right (positive value), it's added to the stack.

When an asteroid moves left (negative value), we check for potential collisions with the
asteroids in the stack (those moving right).

Handling Collisions:

We check the top of the stack and determine if it will explode or not:

If the top of the stack is smaller, it explodes (we pop it).

If both asteroids have the same size, both explode (we pop the stack and stop).

If the top of the stack is larger, the current asteroid explodes (do nothing, move to the next asteroid).

Final Result:

The stack contains the remaining asteroids after all possible collisions. We then convert
the stack to an array for the result.
 */