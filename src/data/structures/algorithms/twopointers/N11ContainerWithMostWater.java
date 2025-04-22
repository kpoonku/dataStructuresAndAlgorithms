package data.structures.algorithms.twopointers;

public class N11ContainerWithMostWater {
    public static int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int maxArea = Integer.MIN_VALUE;
        while (left != right) {
            int currentArea = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(currentArea, maxArea);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] heights = {1, 8, 6, 2, 5, 9, 8, 10, 7};
        int maxArea = maxArea(heights);
        System.out.println("Max Area : " + maxArea);
        maxArea = maxArea(new int[]{1, 1});
        System.out.println("Max Area : " + maxArea);
    }
}
