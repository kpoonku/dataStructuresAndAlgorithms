package data.structures.algorithms.dp.multidimension;

public class N62UniquePaths {
    public static int uniquePaths(int m, int n) {
        int factorial1 = factorial(m + n - 2);
        int factorial2 = factorial(m - 1);
        int factorial3 = factorial(n - 1);

        int result = factorial1 / (factorial2 * factorial3);
        return result;
    }

    public static int factorial(int n) {
        int factorial = 1;
        while (n > 0) {
            factorial *= n;
            n--;
        }
        return factorial;
    }

    public static void main(String[] args) {
        System.out.println("Unique Paths : " + uniquePaths(3, 7));
        System.out.println("Unique Paths : " + uniquePaths(5, 5));
        System.out.println("Unique Paths : " + uniquePaths(3, 6));
    }
}
