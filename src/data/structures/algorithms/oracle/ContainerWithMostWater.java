package data.structures.algorithms.oracle;

public class ContainerWithMostWater {
    public static int containerWithMostWater(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int maxArea = 0;

        while(start != end) {
            int currentArea = Math.min(height[start], height[end]) * (end - start);
            maxArea = Math.max(maxArea, currentArea);
            if(height[start] < height[end]) {
                start++;
            } else {
                end--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println("Max Area : " + containerWithMostWater(new int[]{1,8,6,2,5,4,8,3,8,1}));
    }
}
