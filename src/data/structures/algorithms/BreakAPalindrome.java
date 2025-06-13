package data.structures.algorithms;

public class BreakAPalindrome {
    public static void main(String[] args) {
        System.out.println(new BreakAPalindrome()
                .breakAPalindrome("Anna"));
        System.out.println(new BreakAPalindrome()
                .breakAPalindrome("AAAa"));
    }

    public String breakAPalindrome
            (String stringTobeCheckedForPalindrome) {
        int length = stringTobeCheckedForPalindrome.length();
        if (length <= 1) {
            return "";
        }
        char[] charArray = stringTobeCheckedForPalindrome.
                toLowerCase().toCharArray();

        int index = 0;
        System.out.println(length);
        System.out.println("index < (length/2) " +
                "&& charArray[index] == 'a' : " +
                (index < (length/2) && charArray[index] == 'a'));
        while(index < (length/2) && charArray[index] == 'a') {
            ++index;
            System.out.println("CA :: " + charArray[index]);
        }
        System.out.println(index);
        if(index == length /2) {
            charArray[length - 1] = 'b';
        } else {
            charArray[index] = 'a';
        }
        return new String(charArray);
    }
}
