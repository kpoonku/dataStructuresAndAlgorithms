package data.structures.algorithms;

public class StringToInteger {
    public int StringToInteger ( String strToInteger) {
        int numberValue = 0;
        strToInteger = strToInteger.trim();
        final int SIGN_VALUE = strToInteger.charAt(0) == '-' ? -1 : 1;
        if(strToInteger.charAt(0) == '-' || strToInteger.charAt(0) == '+')
            strToInteger = strToInteger.substring(1);

        System.out.println("1 : " + strToInteger);
        for(char charValue : strToInteger.toCharArray()) {
            System.out.println("numberValue : " + numberValue);
            if(!Character.isDigit(charValue)) {
                System.out.println("break");
                break;
            }
            numberValue = numberValue * 10 + ( charValue - '0');
            System.out.println("( charValue - '0') : " + ( charValue - '0'));
            if(SIGN_VALUE * numberValue <= Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            if ( SIGN_VALUE * numberValue >= Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
        }

        return SIGN_VALUE * numberValue;
    }

    public static void main(String[] args) {
        String strValue = " -54321. ";
        int result = new StringToInteger().StringToInteger(strValue);
        System.out.println("Int Value of " + strValue + " is : " + result);
    }
}
