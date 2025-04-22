package data.structures.algorithms.binary.search;

public class N875KokoEatingBananas {

    public static void main(String[] args) {
        N875KokoEatingBananas koko = new N875KokoEatingBananas();

        System.out.println(koko.minEatingSpeed(new int[]{3, 6, 7, 11}, 8)); // Output: 4
        System.out.println(koko.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 5)); // Output: 30
        System.out.println(koko.minEatingSpeed(new int[]{30, 11, 23, 4, 20}, 6)); // Output: 23
    }

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = getMaxPiles(piles);
        int result = right;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            System.out.println("left : " + left + ", right : " + right + ", mid : " + mid);
            if (canFinish(piles, h, mid)) {
                result = mid; // try to find a smaller k
                right = mid - 1;
            } else {
                left = mid + 1; // too slow, try faster k
            }
        }
        return result;
    }

    public boolean canFinish(int[] piles, int h, int k) {
        int hours = 0;
        for (int pile : piles) {
            //hours += (pile + k - 1) / k;
            hours += Math.ceil((double) pile/k);
        }
        System.out.println("Hours for k : " + k + " : " + hours);
        return hours <= h;
    }

    public int getMaxPiles(int[] piles) {
        int maxPile = 0;
        for (int pile : piles) {
            maxPile = Math.max(pile, maxPile);
        }
        return maxPile;
    }
}
/*
Koko loves to eat bananas. There are n piles of bananas, the ith pile has piles[i] bananas. The guards have gone and will come back in h hours.

Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and will not eat any more bananas during this hour.

Koko likes to eat slowly but still wants to finish eating all the bananas before the guards return.

Return the minimum integer k such that she can eat all the bananas within h hours.



Example 1:

Input: piles = [3,6,7,11], h = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], h = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], h = 6
Output: 23


Constraints:

1 <= piles.length <= 104
piles.length <= h <= 109
1 <= piles[i] <= 109
 */
