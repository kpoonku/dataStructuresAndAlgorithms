package data.structures.algorithms.oracle;

public class FindTheFirstMissingPositiveInteger {
    public static int firstMissingPositive(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (i == nums[i] - 1) {
                continue;
            }
            if (nums[i] > 0 && nums[i] < nums.length) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != i+1) {
                return i+1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int result = firstMissingPositive(new int[]{3,4,-1,1});
        System.out.println(result);
    }
}
