package data.structures.algorithms.dp.multidimension;

import java.util.ArrayList;
import java.util.List;

public class N714MaxProfit {
    public static int maxProfit(int[] prices, int fee) {
        int cash = 0;
        int hold = -prices[0];
        List<String> actions = new ArrayList<>();
        for (int i = 1; i < prices.length; i++) {
            if (cash < hold + prices[i] - fee) {
                cash = hold + prices[i] - fee;
                actions.add("Sell on day " + i + " at price " + prices[i]);
            }
            if (hold < cash - prices[i]) {
                hold = cash - prices[i];
                actions.add("Buy on day " + i + " at price " + prices[i]);
            }
        }
        for (String action : actions) {
            System.out.println(action);
        }
        return cash;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1, 2, 3, 8, 4, 9};
        int maxProfit = maxProfit(prices, 2);
        System.out.println("Profit : " + maxProfit);
    }
}
/*
You are given an array prices where prices[i] is the price of a given stock on the ith day,
and an integer fee representing a transaction fee.

Find the maximum profit you can achieve. You may complete as many transactions as you like,
but you need to pay the transaction fee for each transaction.

Note:

You may not engage in multiple transactions simultaneously (i.e., you must sell the stock
before you buy again).
The transaction fee is only charged once for each stock purchase and sale.


Example 1:

Input: prices = [1,3,2,8,4,9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
- Buying at prices[0] = 1
- Selling at prices[3] = 8
- Buying at prices[4] = 4
- Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Example 2:

Input: prices = [1,3,7,5,10,3], fee = 3
Output: 6


Constraints:

1 <= prices.length <= 5 * 104
1 <= prices[i] < 5 * 104
0 <= fee < 5 * 104
 */
/*
Great question! Let's break it down like you're writing your first stock bot 🤖💰

---

## 📌 You're asking:
> What does `int hold = -prices[0];` mean, and how should I **add** this line?

### 🧠 Quick Answer:
This line means:
> "If I buy stock on **day 0**, then my profit becomes `-prices[0]` because I spent money."

### 💸 For example:

If `prices[0] = 1`, and you buy:
```java
hold = -1
```

You're **in the negative** because you spent $1.
We haven't sold anything yet = no profit yet.

---

## ✅ Where and How to Add It in Code

Here’s the **starting template** for this problem:

```java
public int maxProfit(int[] prices, int fee) {
    int cash = 0;               // You have no stock, and $0 profit
    int hold = -prices[0];      // You buy the first stock on day 0

    for (int i = 1; i < prices.length; i++) {
        cash = Math.max(cash, hold + prices[i] - fee);
        hold = Math.max(hold, cash - prices[i]);
    }

    return cash; // Max profit after all days, no stock in hand
}
```

You always add:
```java
int hold = -prices[0];
```
**right after** initializing `cash`, and **before the loop**.

---

## 🎯 Why Negative?

You didn’t make a profit yet — you *spent* money to buy.
So holding stock = "I'm down by the amount I paid."

We’ll only make profit **when we sell** and subtract the fee.

---

## 🧪 Tiny Dry Run

Let's test this for:
```java
prices = [1, 3, 2], fee = 1
```

| Day | Price | cash | hold | Action               |
|-----|-------|------|------|----------------------|
| 0   | 1     | 0    | -1   | Buy stock (down $1)  |
| 1   | 3     | 1    | -1   | Sell → (3 - 1 - 1) = 1 |
| 2   | 2     | 1    | -1   | No better move       |

Answer: `1`

---

## ✅ TL;DR

Add this:
```java
int hold = -prices[0];
```
- Right after you declare `cash = 0`
- It means: “I bought stock on day 0, so I’m down that much money”

---

Wanna go next-level and also **print the transactions** ("buy/sell on day X")? Or try another version of this problem? 😎
 */
/*
Let's go deep into this — **step by step**, slow and super beginner-friendly 💡🐢
You're gonna **feel like a stock market genius** by the end of this. Let's go! 💸📈

---

## 🎯 Goal Recap

We are given:
- `prices[]` = stock prices for each day
- `fee` = cost for each **sale** (we pay it **only when we sell**)

> ✅ Our goal is to **buy and sell** smartly to **maximize profit**

**We can't hold more than one stock at a time**
📛 No buying again before selling

---

### 🧠 Intuition: Two States

At any day, we are in **one of two states**:

1. 🤲 **"Cash"** = We **don’t hold any stock**
   - We either **do nothing**, or **sell today**
2. ✊ **"Hold"** = We **do hold a stock**
   - We either **do nothing**, or **buy today**

We'll use two variables to represent these:
```java
int cash = 0;
int hold = -prices[0]; // if we bought on day 0
```

---

## 🔁 State Transitions (Core of the Algorithm)

On each day (from 1 to n-1):

We decide what’s better: do nothing or buy/sell?

### If we **sell today**:
```java
cash = Math.max(cash, hold + prices[i] - fee);
```

- `hold + prices[i] - fee` → profit after selling stock and paying fee
- `cash` → do nothing (keep whatever profit we had)

👉 Take the **max** of both

---

### If we **buy today**:
```java
hold = Math.max(hold, cash - prices[i]);
```

- `cash - prices[i]` → profit after buying today
- `hold` → do nothing (keep the stock we already had)

👉 Take the **max** of both

---

### ✅ Final Profit is in `cash`
Because we want to finish **not holding** any stock (fully cashed out)

---

## 🔎 Walkthrough Example (Step-by-Step)

```java
prices = [1,3,2,8,4,9]
fee = 2
```

We'll track `cash` and `hold` each day:

| Day | Price | Action            | cash | hold |
|-----|-------|-------------------|------|------|
| 0   | 1     | Buy               | 0    | -1   |
| 1   | 3     | Hold              | 0    | -1   |
| 2   | 2     | Hold              | 0    | -1   |
| 3   | 8     | Sell → 8-1-2=5    | 5    | -1   |
| 4   | 4     | Buy again         | 5    | 1    |
| 5   | 9     | Sell → 9-4-2=3    | 8    | 1    |

💰 Final answer = `cash = 8`

---

## 🔁 Java Code Again (Now You Understand It!)

```java
public int maxProfit(int[] prices, int fee) {
    int cash = 0;               // No stock in hand
    int hold = -prices[0];      // Bought on day 0

    for (int i = 1; i < prices.length; i++) {
        // Decide to sell or not
        cash = Math.max(cash, hold + prices[i] - fee);
        // Decide to buy or not
        hold = Math.max(hold, cash - prices[i]);
    }

    return cash;
}
```

---

## ✅ Want to Track Actual Buy/Sell Days?

We can enhance this code to print the **days we bought/sold**:

```java
public int maxProfit(int[] prices, int fee) {
    int cash = 0;
    int hold = -prices[0];
    List<String> actions = new ArrayList<>();

    for (int i = 1; i < prices.length; i++) {
        if (cash < hold + prices[i] - fee) {
            cash = hold + prices[i] - fee;
            actions.add("Sell on day " + i + " at price " + prices[i]);
        }

        if (hold < cash - prices[i]) {
            hold = cash - prices[i];
            actions.add("Buy on day " + i + " at price " + prices[i]);
        }
    }

    for (String action : actions) {
        System.out.println(action);
    }

    return cash;
}
```

---

## 🎉 Summary

| Variable | Meaning                      |
|----------|------------------------------|
| `cash`   | Max profit with **no stock** |
| `hold`   | Max profit **while holding** |
| `fee`    | Subtracted **when selling**  |

💡 We decide every day:
- Is it better to buy/sell, or do nothing?

---

You're now a **stock trading DP master** 🤓📈
Wanna try another variation? Like cooldown days or limited transactions?
 */