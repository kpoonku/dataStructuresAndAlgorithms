package data.structures.algorithms.hard.leetCode;

public class N37SolveSudoku {

}
/*
Write a program to solve a Sudoku puzzle by filling the empty cells.
A sudoku solution must satisfy all of the following rules:
Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

Example 1:
Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:

Constraints:
board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
 */
/*
Absolutely! Let me explain this **stack-based Sudoku solver** at a beginner-friendly level — step by step — like you're just starting out in programming.

---

## 🧩 The Goal

You are given a 9x9 Sudoku board with some numbers filled and some empty spots (shown as `'.'`).
Your job is to fill all the empty spots **with numbers 1 to 9** so that:

* Each row has all numbers from 1 to 9 (no repeats).
* Each column has all numbers from 1 to 9.
* Each 3x3 box has all numbers from 1 to 9.

---

## 💡 The Idea

We’ll try to **fill each empty spot one by one**, and if we get stuck, we’ll go **back and fix** the previous choices. This is called **backtracking**.

But instead of using **recursion** (a function calling itself), we'll use a **stack** to keep track of what we’ve tried.

---

## 📦 What is a Stack?

Think of a **stack like a pile of plates**:

* You can only add (push) to the top.
* You can only remove (pop) from the top.
* We use it to **remember our choices**.

---

## ✅ What Are We Doing?

### Step-by-step:

1. **Find all empty cells** and store their positions.
2. Start with the **first empty cell**.
3. Try placing numbers from **'1' to '9'** in it.

   * If a number is valid (doesn’t break Sudoku rules), we put it in and **move to the next empty cell**.
   * If no number works, we **go back (pop the last cell from the stack)** and try a different number there.
4. Keep doing this until all empty cells are filled!

---

## 🧠 Code Summary

Let’s walk through the code like a story:

```java
List<int[]> emptyCells = new ArrayList<>();
```

### ➤ Step 1: Find empty cells

We scan the whole board. For every cell that is `'.'`, we store its position (row and column).

---

```java
Stack<int[]> stack = new Stack<>();
int idx = 0;
```

* `idx` is the index of which empty cell we are working on.
* `stack` remembers what we've placed (like saving progress).

---

```java
while (idx < emptyCells.size()) {
```

### ➤ Step 2: Go through each empty cell one by one.

---

```java
int row = pos[0];
int col = pos[1];
```

Get the position of the current empty cell.

---

### ➤ Step 3: Try numbers from 1 to 9

```java
for (char num = start; num <= '9'; num++) {
    if (isValid(board, row, col, num)) {
        board[row][col] = num;
        stack.push(new int[]{idx, num});
        idx++;
        placed = true;
        break;
    }
}
```

* We try numbers `'1'` to `'9'`.
* If it’s valid (doesn’t break the rules), we **place it** and **remember the choice** by pushing it to the stack.
* Then we move to the **next empty cell**.

---

### ➤ Step 4: Backtrack if stuck

```java
if (!placed) {
    board[row][col] = '.'; // Clear the wrong guess

    if (stack.isEmpty()) return; // No solution possible

    int[] last = stack.pop(); // Go back
    idx = last[0];            // Go back to the cell before
    board[emptyCells.get(idx)[0]][emptyCells.get(idx)[1]] = (char)(last[1]); // Try next number next time
}
```

* If no number worked:

  * We **erase** the current cell (`'.'`)
  * We **pop** the last decision from the stack (go back)
  * We’ll retry with the **next number** in that previous cell

---

## ✅ Example Flow

Let’s say:

* You try `'5'` in the first cell, it works.
* You move to the next cell and try `'3'`, it fails.
* You backtrack and try `'6'` instead, and so on...

Eventually, the board gets filled.

---

## 📌 isValid Function

```java
private boolean isValid(char[][] board, int row, int col, char c) {
    // Check row, column, and 3x3 box for duplicates
}
```

This function checks whether placing `c` at `board[row][col]` is okay.

---

## 🧪 Summary for Beginners

| Step      | What Happens                                          |
| --------- | ----------------------------------------------------- |
| Start     | Find all `.` spots (empty cells)                      |
| Try       | Put numbers 1 to 9 in each spot                       |
| Check     | Use `isValid` to make sure it doesn’t break any rules |
| Stack     | Save each successful number on the stack              |
| Backtrack | If stuck, undo the last move and try the next number  |
| Repeat    | Keep going until all cells are filled                 |

 */
