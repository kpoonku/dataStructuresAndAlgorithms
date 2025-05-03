package data.structures.algorithms.oracle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Given two strings, str1, and str2, where str1  contains exactly
one character more than str2,
find the indices of the characters in str1 that can be removed to make str1
equal to str2. Return the array of indices in increasing order.
 If it is not possible, return the array \[-1\].

**Note:** Use 0-based indexing.

**Example**

str1 = "abdgggda"

str2 = "abdggda"

Any "g" character at positions 3, 4, or 5 can be deleted to obtain str2. Return \[3, 4, 5\].
 */
public class getRemovableIndices {
    public static List<Integer> getRemovableIndices(String longg, String shortt) {
        if(longg.isEmpty() || shortt.isEmpty()) {
            return List.of(-1);
        }

        if (longg.length() < shortt.length()) {
            return getRemovableIndices(shortt, longg);
        }

        if(shortt.length() + 1 != longg.length()) {
            return List.of(-1);
        }

        Map<Character, List<Integer>> map1 = new HashMap<>();
        for (int i = 0; i < longg.length(); i++) {
            if (!map1.containsKey(longg.charAt(i))) {
                map1.put(longg.charAt(i), new ArrayList<>());
            }
            map1.get(longg.charAt(i)).add(i);
        }
        int i = 0;
        for (; i < shortt.length(); i++) {
            char c = shortt.charAt(i);

            if (!map1.containsKey(c)) {
                return List.of(-1);
            }
            if (c != longg.charAt(i) && map1.containsKey(longg.charAt(i))) {
                return map1.get(longg.charAt(i));
            }
        }
        return map1.get(longg.charAt(i));
    }

    public static void main(String[] args) {
        System.out.println("value : " +
                getRemovableIndices("abdgggda", "abdggda"));
        System.out.println("value : " +
                getRemovableIndices("mmgghh", "mfggh"));
        System.out.println("value : " +
                getRemovableIndices("aabbb", "aabb"));
    }
}
