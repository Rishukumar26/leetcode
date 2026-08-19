import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Integer> rowMasks = new HashMap<>();

        for (int[] seat : reservedSeats) {
            rowMasks.put(seat[0], rowMasks.getOrDefault(seat[0], 0) | (1 << seat[1]));
        }

        int totalGroups = (n - rowMasks.size()) * 2;

        for (int mask : rowMasks.values()) {
            boolean left   = (mask & (1 << 2 | 1 << 3 | 1 << 4 | 1 << 5)) == 0; // seats 2,3,4,5
            boolean right  = (mask & (1 << 6 | 1 << 7 | 1 << 8 | 1 << 9)) == 0; // seats 6,7,8,9
            boolean middle = (mask & (1 << 4 | 1 << 5 | 1 << 6 | 1 << 7)) == 0; // seats 4,5,6,7

            if (left && right) {
                totalGroups += 2;
            } else if (left || right || middle) {
                totalGroups += 1;
            }
        }

        return totalGroups;
    }
}