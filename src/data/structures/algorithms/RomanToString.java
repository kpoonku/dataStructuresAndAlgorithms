package data.structures.algorithms;

import java.util.HashMap;
import java.util.Map;

public class RomanToString {
    public int RomanToInt(String s) {
        String romanSymbols = "IVXLCDM";
        int[] values = {1, 5, 10, 50, 100, 500, 1000};
        Map<Character, Integer> numeralToValue = new HashMap<>();

        for (int i = 0; i < values.length; ++i) {
            numeralToValue.put(romanSymbols.charAt(i), values[i]);
        }

        int length = s.length();
        int totalValue = numeralToValue.get(s.charAt(length - 1));
        System.out.println("totalValue : " + totalValue);
        for (int i = 0; i < length - 1; i++) {
            System.out.println(" numeralToValue.get(s.charAt(i)) : " +  numeralToValue.get(s.charAt(i)));
            System.out.println(" numeralToValue.get(s.charAt(i + 1)) : " + numeralToValue.get(s.charAt(i + 1)));

            int sign = numeralToValue.get(s.charAt(i)) < numeralToValue.get(s.charAt(i + 1)) ? -1 : 1;
            totalValue += sign * numeralToValue.get(s.charAt(i));
            System.out.println("total Value : " + totalValue);
        }
        return totalValue;
    }

    public static void main(String[] args) {
        System.out.println(new RomanToString().RomanToInt("MCMIV"));
    }
}
