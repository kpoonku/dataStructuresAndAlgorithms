package data.structures.algorithms;

public class IntegerToRoman {
    public String intToRoman(int num) {
        String[] romanNumerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};

        StringBuilder romanString = new StringBuilder();

        for (int i = 0; i < romanNumerals.length; i++) {
            System.out.println( i + " num : " + num);
            while (num >= values[i]) {
                num -= values[i];
                System.out.println("num num : " + num);
                System.out.println("values[i] : " + values[i]);
                romanString.append((romanNumerals[i]));
                System.out.println("RS: " + romanString);
            }
        }
        return romanString.toString();
    }

    public static void main(String[] args) {
        int num = 1234;
        String str = new IntegerToRoman().intToRoman(num);
        System.out.println("Roman value of " + num + " is : " + str);
    }
}
