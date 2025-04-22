package data.structures.algorithms.array.strings;

public class N605CanPlaceFlowers {
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int cnt = 0;
        int length = flowerbed.length;
        for (int i = 0; i < length; i++) {
            if (cnt >= n) {
                return true;
            }
            if (flowerbed[i] == 0 &&
                    (i == 0 || flowerbed[i - 1] == 0) &&
                    (i == length - 1 || flowerbed[i + 1] == 0)) {
                cnt++;
                flowerbed[i] = 1;
            }
        }
        return cnt >= n;
    }

    public static void main(String[] args) {
        System.out.println("can Place Flowers : " + canPlaceFlowers(new int[]{1, 0, 1, 0, 0}, 1));
        System.out.println("can Place Flowers : " + canPlaceFlowers(new int[]{1, 0, 0, 0, 1}, 2));
    }
}

/*
605. Can Place Flowers
You have a long flowerbed in which some of the plots are planted, and some are not. However,
flowers cannot be planted in adjacent plots.
Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1 means not empty,
and an integer n, return true if n new flowers can be planted in the flowerbed without violating
the no-adjacent-flowers rule and false otherwise.
Example 1:
Input: flowerbed = [1,0,0,0,1], n = 1
Output: true
Example 2:
Input: flowerbed = [1,0,0,0,1], n = 2
Output: false
 */

