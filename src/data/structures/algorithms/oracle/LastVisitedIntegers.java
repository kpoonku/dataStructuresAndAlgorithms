package data.structures.algorithms.oracle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LastVisitedIntegers {
    public static int[] lastVisitedIntegers(int[] nums) {
        List<Integer> seen = new ArrayList<>(nums.length),
                ans = new ArrayList<>(nums.length);
        int k = 0;
        for (int value : nums) {
            if (value == -1) {
                k++;
                if (seen.size() >= k) {
                    ans.add(seen.get(k - 1));
                } else {
                    ans.add(-1);
                }
            } else {
                seen.addFirst(value);
                k = 0;
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[] result = lastVisitedIntegers(new int[]{1,2,3,-1,-1,-1});
        System.out.println(Arrays.toString(result));
    }
}
