package data.structures.algorithms.oracle;

public class PowerOf2 {
    public static void main(String[] args) {
        int n = 4;
        System.out.println("is " + n + " power of 2 : " + (n > 0 && ((n & (n-1))==0)));

        n = 3;
        System.out.println("is " + n + " power of 2 : " + (n > 0 && ((n & (n-1))==0)));
    }
}
