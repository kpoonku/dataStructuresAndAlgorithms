package data.structures.algorithms.twopointers;

import java.util.HashMap;
import java.util.Map;

public class N1679MaxOperation {
    public static int maxOperations(int[] nums, int k) {
        int count = 0;
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int complement = k - num;
            if (frequencyMap.containsKey(complement) && frequencyMap.get(complement) > 0) {
                if (num == complement && frequencyMap.get(num) > 1) {
                    count++;
                    frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) - 2);
                } else if (num != complement) {
                    count++;
                    frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) - 1);
                    frequencyMap.put(complement, frequencyMap.getOrDefault(complement, 0) - 1);
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("Max Operation : " + maxOperations(new int[]{1, 2, 3, 4}, 5));
        System.out.println("Max Operation : " + maxOperations(new int[]{3, 1, 3, 4, 3}, 5));
    }
}
/*
You are given an integer array nums and an integer k.
In one operation, you can pick two numbers from the array whose sum equals k
and remove them from the array.
Return the maximum number of operations you can perform on the array.
Example 1:
Input: nums = [1,2,3,4], k = 5
Output: 2
Explanation: Starting with nums = [1,2,3,4]:
- Remove numbers 1 and 4, then nums = [2,3]
- Remove numbers 2 and 3, then nums = []
There are no more pairs that sum up to 5, hence a total of 2 operations.
Example 2:
Input: nums = [3,1,3,4,3], k = 6
Output: 1
Explanation: Starting with nums = [3,1,3,4,3]:
- Remove the first two 3's, then nums = [1,4,3]
There are no more pairs that sum up to 6, hence a total of 1 operation.
*/
/*
Great question! Let's break this down **super simply** so it’s easy to understand if you're new to coding. 🧠✨

---

## 🎯 Problem Summary (Beginner-Level):

You're given:
- An array of numbers: `nums`
- A number `k`

You need to:
- Find **pairs** of numbers that **add up to `k`**
- For each pair you find, you remove **both numbers** from the array
- Your goal is to count **how many such pairs** (operations) you can do

---

## 🧪 Example 1:
```java
nums = [1, 2, 3, 4]
k = 5
```

Which numbers can add up to 5?
- 1 + 4 = 5 ✅
- 2 + 3 = 5 ✅

Now remove them:
- After removing 1 & 4 → [2, 3]
- After removing 2 & 3 → []

✅ We did **2 operations**

**Output: 2**

---

## 🧪 Example 2:
```java
nums = [3,1,3,4,3]
k = 6
```

We want pairs that add up to 6:
- 3 + 3 = 6 ✅ (we can only use the **first two 3s**)
- Now we’re left with [1, 4, 3] → No more pairs that add up to 6

✅ Only **1 operation**

**Output: 1**

---

## 🧠 Key Idea (How to Think About It):

Instead of checking all combinations (slow!), we can do this smartly using a **HashMap**
to count how many numbers we have.

For every number `num`, we check if we already have a **partner** (i.e. `k - num`) saved in the map.

---

### 🤓 Simple Step-by-Step Logic:

1. Go through each number in the array.
2. For each number `num`, check:
   - Do we have already seen `k - num` before? (stored in map?)
     - ✅ Yes → It's a pair! Remove both → increase operation count.
     - ❌ No → Save this number in the map to use it later if its pair shows up.

---

## ✅ Beginner Java Code:

```java
import java.util.HashMap;

public class MaxNumberOfKSumPairs {
    public int maxOperations(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;

        for (int num : nums) {
            int target = k - num;

            if (map.getOrDefault(target, 0) > 0) {
                // Found a pair
                count++;
                map.put(target, map.get(target) - 1); // Use up the target number
            } else {
                // Save current number for future pair
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }

        return count;
    }

    public static void main(String[] args) {
        MaxNumberOfKSumPairs solution = new MaxNumberOfKSumPairs();

        int[] nums1 = {1, 2, 3, 4};
        System.out.println(solution.maxOperations(nums1, 5)); // Output: 2

        int[] nums2 = {3, 1, 3, 4, 3};
        System.out.println(solution.maxOperations(nums2, 6)); // Output: 1
    }
}
```

---

## 🔍 What's Happening?

Imagine the `map` as a little notebook:
- You write down numbers you can't match **yet**
- When their **partner shows up**, you say "Hey! I have your match!" and make a pair 💥

---

## 🧠 Time & Space:

- **Time:** O(n) → Fast! Just loop once
- **Space:** O(n) → For the HashMap

---

Let me know if you want this in Python, or a visual version that shows how the map changes step by step!
 */
