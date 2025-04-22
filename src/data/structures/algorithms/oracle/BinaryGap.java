package data.structures.algorithms.oracle;

public class BinaryGap {
    public static int solution(int N) {
        // Convert the number N to binary representation (as a string)
        String binary = Integer.toBinaryString(N);

        int maxGap = 0; // The maximum gap length
        int currentGap = 0; // The current gap length

        // Iterate over the binary string (skip the first character because it's a leading '1')
        for (int i = 1; i < binary.length(); i++) {
            char currentChar = binary.charAt(i);
            if (currentChar == '0') {
                // If it's a 0, increase the current gap length
                currentGap++;
            } else {
                // If it's a 1, we end the current gap
                if (currentGap > maxGap) {
                    maxGap = currentGap;
                }
                currentGap = 0; // Reset the current gap counter for the next possible gap
            }
        }

        return maxGap;
    }

    public static void main(String[] args) {
        System.out.println("Binary value of 2 : " + (18 & 17));
        System.out.println(Integer.parseInt("10000", 2));
        System.out.println(BinaryGap.solution(1));
        int n = 8;
        double x = Math.log(n) / Math.log(2);
        System.out.println("Double Value : " + x);
    }
}
