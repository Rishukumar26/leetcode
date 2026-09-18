import java.util.*;

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] first = new int[26];
        int[] last = new int[26];
        Arrays.fill(first, -1);
        Arrays.fill(last, -1);

        // Step 1: Record first and last occurrence for each character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (first[c] == -1) {
                first[c] = i;
            }
            last[c] = i;
        }

        List<String> result = new ArrayList<>();
        int rightmostEnd = -1;

        // Step 2: Iterate through the string to find valid valid minimal substrings
        for (int i = 0; i < n; i++) {
            // Only process if 'i' is the first occurrence of s.charAt(i)
            if (i == first[s.charAt(i) - 'a']) {
                int newEnd = checkSubstring(s, i, first, last);
                
                if (newEnd != -1) {
                    // If this valid substring starts after the previous selected end, add it
                    if (i > rightmostEnd) {
                        result.add("");
                    }
                    // Keep the substring with the smallest right end (greedy choice)
                    rightmostEnd = newEnd;
                    result.set(result.size() - 1, s.substring(i, rightmostEnd + 1));
                }
            }
        }

        return result;
    }

    private int checkSubstring(String s, int start, int[] first, int[] last) {
        int right = last[s.charAt(start) - 'a'];

        for (int i = start; i <= right; i++) {
            // If a character inside expands before 'start', it's an invalid starting point
            if (first[s.charAt(i) - 'a'] < start) {
                return -1;
            }
            // Expand the boundary to cover all occurrences of s.charAt(i)
            right = Math.max(right, last[s.charAt(i) - 'a']);
        }

        return right;
    }
}