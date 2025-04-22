package data.structures.algorithms.oracle;

public class PowerOf2 {
    public static void main(String[] args) {
        int n = 4;
        System.out.println("is 3 power of 2 : " + (n > 0 && ((n & (n-1))==0)));
    }
}
