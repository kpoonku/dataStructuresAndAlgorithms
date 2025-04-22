package data.structures.algorithms.oracle;

import java.util.Arrays;

public class SortedSquares {
    public static void main(String[] args) {
        int[] nums = {-4, -1, 0, 3, 10};
        int[] result = new SortedSquares().sortedSquares(nums);
        System.out.println(Arrays.toString(result));
        nums = new int[]{-7, -3, 2, 3, 11};
        result = new SortedSquares().sortedSquares(nums);
        System.out.println(Arrays.toString(result));
    }

    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int index = 0;
        for (int value : nums) {
            result[index++] = value * value;
        }
        Arrays.sort(result);
        return result;
    }
}
