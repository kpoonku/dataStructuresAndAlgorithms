package data.structures.algorithms.oracle;

import java.util.Arrays;

public class MaxWidthOfVerticalArea {
    public static int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, (a, b) -> Integer.compare(a[0], b[0]));
       /* for (int i = 0; i < points.length; i++) {
            System.out.println(Arrays.toString(points[i]));
        }*/
        int maxGap = 0;
        for (int i = 0; i < points.length - 1; i++) {
            int diff = points[i + 1][0] - points[i][0];
            System.out.println("diff : " + diff);
            if (maxGap < diff) {
                maxGap = diff;
            }
        }
        return maxGap;
    }

    public static void main(String[] args) {
        int[][] points = {{8, 7}, {9, 9}, {7, 4}, {9, 6}};
        System.out.println("Gap : " + maxWidthOfVerticalArea(points));
        points = new int[][]{{3, 1}, {9, 0}, {1, 0}, {1, 4}, {5, 3}, {8, 8}};
        System.out.println("Gap : " + maxWidthOfVerticalArea(points));
    }
}
