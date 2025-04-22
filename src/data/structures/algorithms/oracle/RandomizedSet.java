package data.structures.algorithms.oracle;

import java.util.*;

public class RandomizedSet {
    private final Map<Integer, Integer> indexMap = new HashMap<>();
    private final List<Integer> list = new ArrayList<>();

    public boolean insert(int value) {
        if (indexMap.containsKey(value)) {
            return false;
        }
        indexMap.put(value, list.size());
        list.add(value);
        return true;
    }

    public boolean remove(int value) {
        if (!indexMap.containsKey(value)) {
            return false;
        }
        int currentValueIndex = indexMap.get(value);
        list.remove(currentValueIndex);
        int lastValue = list.getLast();
        list.add(currentValueIndex, lastValue);
        list.removeLast();
        indexMap.put(lastValue, currentValueIndex);
        indexMap.remove(value);
        return true;
    }

    public int getRandom() {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        for (int i = 0; i < 20; i++) {
            randomizedSet.insert(i);
        }
        System.out.println(randomizedSet.indexMap);
        for (int i = 0; i < 5; i++) {
            randomizedSet.remove(i);
        }
        System.out.println(randomizedSet.indexMap);
        System.out.println("random value : " + randomizedSet.getRandom());
    }
}
