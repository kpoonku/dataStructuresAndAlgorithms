package data.structures.algorithms;

/*
Problem Description
The task is to rearrange the characters of a given string s in a zigzag pattern on a
specified number of rows and then read off the characters line by line to create a new string.
The zigzag pattern means that the characters of s are placed diagonally in a zigzag manner in downwards and
upwards directions successively. After placing all the characters in this pattern, we would read
 the characters in each row horizontally and concatenate to form the final string.

To visualize the process, you can think of writing down the characters in the following way:

Starting from the top row, write characters downwards.
Upon reaching the last row, switch direction and start writing characters upwards, forming a diagonal line
 until you reach the top row again.
Alternate the process until every character is written in this zigzag fashion.
For example, given a string "PAYPALISHIRING" and 3 rows, the characters should be placed like this:

P   A   H   N
A P L S I I G
Y   I   R
After arranging the characters, the string "PAHNAPLSIIGYIR" is obtained by reading each row sequentially.
To form this new string programmatically, the code should simulate the reading process.
 */
class Solution {
    public String convert(String inputString, int numRows) {
        // If numRows is 1, no pattern is required, so return the string as it is.
        if (numRows == 1) {
            return inputString;
        }

        // StringBuilder is more efficient when appending characters in a loop.
        StringBuilder convertedStringBuilder = new StringBuilder();
        // Length of the pattern cycle.
        int cycleLength = 2 * numRows - 2;

        // Loop over each row.
        for (int row = 0; row < numRows; row++) {
            // Calculate the interval for the current row.
            // For the first and last row, it is the cycle length,
            // for the others, it depends on the row number.
            int interval = (row == numRows - 1) ? cycleLength : 2 * (numRows - row - 1);
            // Index to keep track of the position on the string.
            int index = row;

            // Continue looping until the end of the string is reached.
            while (index < inputString.length()) {
                // Append character at index to the result.
                convertedStringBuilder.append(inputString.charAt(index));
                // Proceed to the next character in the current row.
                index += interval;
                // Toggle the interval for the middle rows.
                // This does not affect the first and last rows.
                interval = (interval == cycleLength || interval == 0) ? cycleLength : cycleLength - interval;
            }
        }

        // Convert StringBuilder back to String and return.
        return convertedStringBuilder.toString();
    }
}

public class ZigZagConversion {
}
