package data.structures.algorithms.oracle.bit;

public class BinaryIndexedTree {
    private final int[] bit; // The Binary Indexed Tree (BIT)
    private final int n; // Size of the array

    // Constructor to initialize BIT
    public BinaryIndexedTree(int n) {
        this.n = n;
        this.bit = new int[n + 1]; // BIT is 1-indexed
    }

    // Function to update the value at index 'i' by 'delta'
    public void update(int i, int delta) {
        while (i <= n) {
            bit[i] += delta;  // Update BIT[i] by delta
            i += (i & -i);    // Move to the next index that stores the sum
        }
    }

    // Function to calculate prefix sum from index 1 to 'i'
    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += bit[i];  // Add BIT[i] to the sum
            i -= (i & -i);  // Move to the parent index
        }
        return sum;
    }

    // Function to calculate the sum from range [left, right]
    public int rangeQuery(int left, int right) {
        return query(right) - query(left - 1);
    }

    public static void main(String[] args) {
        int n = 8;  // Size of the array
        BinaryIndexedTree bit = new BinaryIndexedTree(n);

        // Update the BIT with some values
        bit.update(1, 5);  // Add 5 to index 1
        bit.update(2, 3);  // Add 3 to index 2
        bit.update(3, 7);  // Add 7 to index 3
        bit.update(4, 2);  // Add 2 to index 4

        // Get the prefix sum from 1 to 4
        System.out.println("Prefix sum of first 4 elements: " + bit.query(4));  // Output should be 17

        // Get the range sum from 2 to 4
        System.out.println("Sum from index 2 to 4: " + bit.rangeQuery(2, 4));  // Output should be 12
    }
}

