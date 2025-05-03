package data.structures.algorithms.dp.multidimension;

public class N1143LongestCommonSequence {
    public static int getLongestCommonSequence(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // Initialize base cases
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Deleting all characters from word1
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Inserting all characters to word1
        }

        // Fill DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // Characters match, no operation
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j],    // Delete
                            Math.min(dp[i][j - 1],    // Insert
                                    dp[i - 1][j - 1] // Replace
                            ));
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "abcde", t = "ace";
        System.out.println("Output : " + getLongestCommonSequence(s, t));
    }
}
/*
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.

You have the following three operations permitted on a word:

Insert a character
Delete a character
Replace a character


Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')
Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation:
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')


Constraints:

0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
*/
/*
Absolutely! Let's break it down **step by step**, assuming you're just starting out with
programming and want to understand what’s really happening when we solve the **Edit Distance**
problem in Java.
---
## 🎯 Problem Recap (Simple Words)
You are given **two words**. Your job is to find **the smallest number of steps** needed to make the
first word exactly the same as the second word.
You can:
* **Insert** a letter
* **Delete** a letter
* **Replace** a letter with another
Example:
```
word1 = "horse"
word2 = "ros"
You can:
horse → rorse  (replace 'h' with 'r')
rorse → rose   (delete 'r')
rose → ros     (delete 'e')
So the answer is 3 operations.
```
---
## 🧠 How Do We Solve This?
We use something called **Dynamic Programming (DP)** — don’t worry, think of it like filling out a
**table** that tells us the answer step by step.
We'll create a **2D grid (table)** where:
* Rows represent each letter of `word1`
* Columns represent each letter of `word2`
Each cell in this table `dp[i][j]` will tell us:
> "How many steps does it take to convert the **first i letters** of word1 into the **first j letters**
of word2?"
---
## 🪜 Step-by-Step Explanation
Let’s take:
```java
word1 = "horse";
word2 = "ros";
```
We'll create a table that is:
* `word1.length + 1` rows (because we include the empty string)
* `word2.length + 1` columns
We initialize the table like this:
### 📦 Base Cases:
* If one of the words is empty, we need to **insert/delete** all the letters from the other word.
  * To turn "" into "ros" → insert 'r', 'o', 's' → 3 steps
  * To turn "horse" into "" → delete 5 letters → 5 steps
### 🤖 Filling the Table:
Now we go cell by cell and make decisions:
#### For each letter:
* **If the letters match**, no new step needed → just copy the value diagonally: `dp[i][j] = dp[i-1][j-1]`
* **If the letters don’t match**, we do 1 operation + best of:
  * **Insert** a letter: `dp[i][j-1]`
  * **Delete** a letter: `dp[i-1][j]`
  * **Replace** a letter: `dp[i-1][j-1]`
---
## 🧮 Final Answer:
After filling the table, the **bottom-right corner** of the table (`dp[m][n]`) will contain the
**minimum number of operations needed**.
---
## 🧑‍💻 In Very Simple Java
Imagine this code is just a way to **build the table** and get the answer from the bottom right:
```java
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length(); // rows
        int n = word2.length(); // columns
        // Create table
        int[][] dp = new int[m + 1][n + 1];
        // Fill first row and column
        for (int i = 0; i <= m; i++) dp[i][0] = i; // delete steps
        for (int j = 0; j <= n; j++) dp[0][j] = j; // insert steps
        // Fill rest of the table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // same letter, no new step
                } else {
                    dp[i][j] = 1 + Math.min(
                        dp[i - 1][j],    // delete
                        Math.min(
                            dp[i][j - 1],    // insert
                            dp[i - 1][j - 1] // replace
                        )
                    );
                }
            }
        }
        return dp[m][n]; // Final answer
    }
}
```
---
### 📌 Summary:
* Build a table
* Fill it using simple rules
* Each cell says: "how many steps to match these parts of the words?"
* Final answer is in the last cell
Would you like a visual diagram of the table for `"horse"` vs `"ros"` to help it click better?
 */
