package data.structures.algorithms.oracle.tree.bst;

public class ShipWithinDays {
    public int shipWithinDays(int[] weights, int days) {
        int left = 0, right = 0;

        // Set left to max weight and right to sum of all weights
        for (int weight : weights) {
            left = Math.max(left, weight);
            right += weight;
        }

        // Binary search to find minimum valid ship capacity
        while (left < right) {
            int mid = (left + right) / 2;
            if (canShip(weights, mid, days)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // Helper method to check if we can ship within given days
    private boolean canShip(int[] weights, int capacity, int days) {
        int currentLoad = 0, dayCount = 1;

        for (int weight : weights) {
            if (currentLoad + weight > capacity) {
                currentLoad = weight;
                dayCount++;
            } else {
                currentLoad += weight;
            }
        }

        return dayCount <= days;
    }
}

