package data.structures.algorithms.prefix.sum;

import java.util.Arrays;

public class N1732LargestAltitude {
    public static int largestAltitude(int[] gain) {
        int maxAltitude = 0, currentAltitude = 0;
        int[] result = new int[gain.length + 1];
        result[0] = currentAltitude;
        for (int i = 0; i < gain.length; i++) {
            currentAltitude += gain[i];
            result[i + 1] = currentAltitude;
            maxAltitude = Math.max(maxAltitude, currentAltitude);
        }
        System.out.println(Arrays.toString(result));
        return maxAltitude;
    }

    public static void main(String[] args) {
        int[] nums = {-5,1,5,0,-7};
        System.out.println("Max Altitude : " + largestAltitude(nums));
        nums = new int[] {-4,-3,-2,-1,4,3,2};
        System.out.println("Max Altitude : " + largestAltitude(nums));
    }
}
