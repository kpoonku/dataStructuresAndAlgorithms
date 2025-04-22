package data.structures.algorithms.oracle;

public class TipsAndTricksOfBitWiseOperator {
    public static void main(String[] args) {
        int a = 5;   // binary: 0101
        int b = 3;   // binary: 0011
        int result = a & b;   // result: 0001 (1 in decimal)
        System.out.println(result);  // Output: 1

        int num = 5;
        if ((num & 1) == 1) {
            System.out.println("Odd");
        } else {
            System.out.println("Even");
        }

        int a1 = 5;   // binary: 0101
        int b1 = 3;   // binary: 0011
        int result1 = a1 | b1;   // result: 0111 (7 in decimal)
        System.out.println(result1);  // Output: 7

        int a2 = 5;   // binary: 0101
        int b2 = 3;   // binary: 0011
        int result2 = a2 ^ b2;   // result: 0110 (6 in decimal)
        System.out.println(result2);  // Output: 6

        int a3 = 5;
        int b3 = 3;
        a3 = a3 ^ b3;  // a becomes 6
        b3 = a3 ^ b3;  // b becomes 5
        a3 = a3 ^ b3;  // a becomes 3
        System.out.println("a3 = " + a3 + ", b3 = " + b3);  // Output: a = 3, b = 5

        int a4 = 5;   // binary: 0101
        int result4 = ~a4;   // result: 1010 (in 32-bit system, it's -6)
        System.out.println(result4);  // Output: -6 (in decimal)

        int x = 1;
        int y = 2;
        x = x ^ y;
        y = x ^ y;
        x = x ^ y;
        System.out.println("x = " + x + " y = " + y);
    }
}
