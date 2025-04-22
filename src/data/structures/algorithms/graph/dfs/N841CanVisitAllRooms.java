package data.structures.algorithms.graph.dfs;

import java.util.*;

public class N841CanVisitAllRooms {
    public static void main(String[] args) {
        N841CanVisitAllRooms solution = new N841CanVisitAllRooms();

        // Example 1
        List<List<Integer>> rooms1 = new ArrayList<>();
        rooms1.add(List.of(1));        // Room 0 has key to room 1
        rooms1.add(List.of(2));        // Room 1 has key to room 2
        rooms1.add(List.of(3));        // Room 2 has key to room 3
        rooms1.add(new ArrayList<>());       // Room 3 has no keys
        System.out.println(solution.canVisitAllRooms(rooms1));  // Output: true

        // Example 2
        List<List<Integer>> rooms2 = new ArrayList<>();
        rooms2.add(Arrays.asList(1, 3));     // Room 0 has keys to rooms 1 and 3
        rooms2.add(Arrays.asList(3, 0, 1));  // Room 1 has keys to rooms 0, 1, and 3
        rooms2.add(List.of(2));        // Room 2 has key to room 2
        rooms2.add(List.of(0));        // Room 3 has key to room 0
        System.out.println(solution.canVisitAllRooms(rooms2));  // Output: false
        System.out.println();
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> visited = new HashSet<>(rooms.size());
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        visited.add(0);

        while (!stack.isEmpty()) {
            int room = stack.pop();
            List<Integer> roomsList = rooms.get(room);
            for (int toBeVisited : roomsList) {
                if (!visited.contains(toBeVisited)) {
                    visited.add(toBeVisited);
                    stack.push(toBeVisited);
                }
            }
        }
        System.out.println("visited : " + visited);
        for (int i = 0; i < rooms.size(); i++) {
            if (!visited.contains(i)) {
                return false;
            }
        }
        return true;
    }
}
