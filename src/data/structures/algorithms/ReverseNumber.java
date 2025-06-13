package data.structures.algorithms;

public class ReverseNumber {
    public int reverseNumber(int numberToBeReversed) {
        int result = 0;
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        while (numberToBeReversed != 0) {
            if (result < Integer.MIN_VALUE / 10 ||
                    result > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int modValue = numberToBeReversed % 10;
            numberToBeReversed = numberToBeReversed / 10;
            result = (result * 10) + modValue;
        }
        return result;
    }

    public static void main(String[] args) {
        int numberToBeReversed = 2147483647;
        int result = new ReverseNumber().reverseNumber(numberToBeReversed);
        System.out.println( "reverse of " +  numberToBeReversed + " is:  " + result);
    }
}
