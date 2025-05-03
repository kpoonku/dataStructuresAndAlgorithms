package data.structures.algorithms.dp.onedimension;

public class N1137TribanocciSeries {
    public static void main(String[] args) {
        N1137TribanocciSeries sol = new N1137TribanocciSeries();
        sol.getTribonacciSequence(4);
        sol.getTribonacciSequence(25);
    }
    public int getTribonacciSequence(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int x = 0, y = 1, z = 1;
        int output = 0;
        for (int i = 3; i <= n; i++) {
           // System.out.println(i + "th round :  x = " + x + " , y = " + y + " , z = " + z);
            output = x + y + z;
            x = y;
            y = z;
            z = output;
            System.out.print(" " + output + " ");
        }
        System.out.println(" \n Output: " + output);
        return output;
    }
}
/*
The Tribonacci sequence Tn is defined as follows:
T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
Given n, return the value of Tn.

Example 1:

Input: n = 4
Output: 4
Explanation:
T_3 = 0 + 1 + 1 = 2
T_4 = 1 + 1 + 2 = 4
Example 2:

Input: n = 25
Output: 1389537


Constraints:

0 <= n <= 37
The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1.
 */
/*
Absolutely! Let’s explain the **Tribonacci sequence** in a super simple and clear way, like we're teaching someone brand new to programming or math patterns.

---

### 🧠 What is the Tribonacci Sequence?

It’s a number sequence, just like the **Fibonacci sequence**—but instead of adding the **last 2 numbers**, we add the **last 3 numbers** to get the next one.

---

### 🪜 The Rules:

We start with:
```
T0 = 0
T1 = 1
T2 = 1
```

Then, for any number after that:
```
Tn = T(n-1) + T(n-2) + T(n-3)
```

In other words:
> To get the current number, add the last three numbers before it.

---

### 🔢 Let's Build It Step by Step

Let’s say we want to calculate **T4**.

We already know:

```
T0 = 0
T1 = 1
T2 = 1
```

Now let’s compute:
```
T3 = T0 + T1 + T2 = 0 + 1 + 1 = 2
T4 = T1 + T2 + T3 = 1 + 1 + 2 = 4
```

✅ So **T4 = 4**

---

### ✨ Another Example: What is T5?

Now we know:
```
T3 = 2
T4 = 4
```

So:
```
T5 = T2 + T3 + T4 = 1 + 2 + 4 = 7
```

---

### 🔁 How Do We Code It?

In Java (or any language), you just keep three variables to track the last three values, then keep moving forward.

Here’s a **simple way to think about the logic**:
1. Start with `a = 0`, `b = 1`, `c = 1` (these are T0, T1, T2).
2. Loop from 3 to `n`, and each time:
   - Compute the next number as `a + b + c`
   - Shift the values: `a = b`, `b = c`, `c = next`

---

### ✅ Java Code for Simple Brain:

```java
public int tribonacci(int n) {
    if (n == 0) return 0;
    if (n == 1 || n == 2) return 1;

    int a = 0; // T0
    int b = 1; // T1
    int c = 1; // T2

    for (int i = 3; i <= n; i++) {
        int next = a + b + c; // current Tribonacci number
        a = b; // move forward
        b = c;
        c = next;
    }

    return c; // This is Tn
}
```

---

### 🔍 Example: For n = 4

Let’s walk through it:
- a = 0, b = 1, c = 1
- i = 3 → next = 0 + 1 + 1 = 2 → a = 1, b = 1, c = 2
- i = 4 → next = 1 + 1 + 2 = 4 → a = 1, b = 2, c = 4

✅ Final result: **T4 = 4**

---

Want me to show how it works for `n = 25` step-by-step too? Or would a visual chart help more?
 */
/*

 */