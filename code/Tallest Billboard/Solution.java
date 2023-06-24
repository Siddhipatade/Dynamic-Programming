import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.AbstractMap.SimpleEntry;

class Solution {
    // Helper function to collect every combination `(left, right)`
    static Map<Integer, Integer> helper(int[] rods, int leftIndex, int rightIndex) {
        Set<SimpleEntry<Integer, Integer>> states = new HashSet<>();
        states.add(new SimpleEntry<>(0, 0));
        for (int i = leftIndex; i < rightIndex; ++i) {
            int r = rods[i];
            Set<SimpleEntry<Integer, Integer>> newStates = new HashSet<>();
            for (SimpleEntry<Integer, Integer> p : states) {
                newStates.add(new SimpleEntry<>(p.getKey() + r, p.getValue()));
                newStates.add(new SimpleEntry<>(p.getKey(), p.getValue() + r));
            }
            states.addAll(newStates);
        }

        Map<Integer, Integer> dp = new HashMap<>();
        for (SimpleEntry<Integer, Integer> p : states) {
            int left = p.getKey(), right = p.getValue();
            dp.put(left - right, Math.max(dp.getOrDefault(left - right, 0), left));
        }
        return dp;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of rods: ");
        int n = scanner.nextInt();
        int[] rods = new int[n];
        System.out.println("Enter the lengths of rods:");
        for (int i = 0; i < n; i++) {
            rods[i] = scanner.nextInt();
        }

        Map<Integer, Integer> firstHalf = helper(rods, 0, rods.length / 2);
        Map<Integer, Integer> secondHalf = helper(rods, rods.length / 2, rods.length);

        int answer = 0;
        for (int diff : firstHalf.keySet()) {
            if (secondHalf.containsKey(-diff)) {
                answer = Math.max(answer, firstHalf.get(diff) + secondHalf.get(-diff));
            }
        }

        System.out.println("Largest possible height: " + answer);
    }
}



// You are installing a billboard and want it to have the largest height. The billboard will have two steel supports, one on each //side. Each steel support must be an equal height.

//You are given a collection of rods that can be welded together. For example, if you have rods of lengths 1, 2, and 3, you can weld // them together to make a support of length 6.

// Return the largest possible height of your billboard installation. If you cannot support the billboard, return 0.

 