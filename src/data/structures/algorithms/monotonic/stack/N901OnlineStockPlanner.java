package data.structures.algorithms.monotonic.stack;

import java.util.Stack;

public class N901OnlineStockPlanner {
    // Stack to hold pairs: (price, span)
    private Stack<int[]> stack;

    public N901OnlineStockPlanner() {
        stack = new Stack<>();
    }

    public int next(int price) {
        int span = 1;
        // While the last price in the stack is less than or equal to today’s
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            span += stack.pop()[1]; // Add that day’s span to today
        }
        // Push today’s price and its span
        stack.push(new int[]{price, span});
        return span;
    }

    public static void main(String[] args) {
        N901OnlineStockPlanner sol = new N901OnlineStockPlanner();
        System.out.println(sol.next(100));
        System.out.println(sol.next(80));
        System.out.println(sol.next(60));
        System.out.println(sol.next(70));
    }
}
/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */
/*
Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.

The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.

For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2, then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8, then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
Implement the StockSpanner class:

StockSpanner() Initializes the object of the class.
int next(int price) Returns the span of the stock's price given that today's price is price.


Example 1:

Input
["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
[[], [100], [80], [60], [70], [60], [75], [85]]
Output
[null, 1, 1, 1, 2, 1, 4, 6]

Explanation
StockSpanner stockSpanner = new StockSpanner();
stockSpanner.next(100); // return 1
stockSpanner.next(80);  // return 1
stockSpanner.next(60);  // return 1
stockSpanner.next(70);  // return 2
stockSpanner.next(60);  // return 1
stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
stockSpanner.next(85);  // return 6


Constraints:

1 <= price <= 105
At most 104 calls will be made to next.
 */
