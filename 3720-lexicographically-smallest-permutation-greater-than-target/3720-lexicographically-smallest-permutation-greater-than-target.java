import java.util.*;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Try to find the longest matching prefix with target, 
        // then backtrack from the deepest matching point down to 0.
        int matched = 0;
        int[] tempCount = count.clone();
        for (int i = 0; i < n; i++) {
            int ch = target.charAt(i) - 'a';
            if (tempCount[ch] > 0) {
                tempCount[ch]--;
                matched++;
            } else {
                break;
            }
        }

        // Try pivot position p from 'matched' down to 0
        for (int p = matched; p >= 0; p--) {
            // Reconstruct count for remaining characters after matching prefix of length p
            int[] currentCount = count.clone();
            for (int i = 0; i < p; i++) {
                currentCount[target.charAt(i) - 'a']--;
            }

            if (p < n) {
                int targetChar = target.charAt(p) - 'a';
                // Try to find the smallest character strictly greater than target[p]
                for (int c = targetChar + 1; c < 26; c++) {
                    if (currentCount[c] > 0) {
                        // Found valid pivot char
                        StringBuilder sb = new StringBuilder();
                        sb.append(target.substring(0, p));
                        sb.append((char) ('a' + c));
                        currentCount[c]--;

                        // Append the remaining available characters in sorted (smallest first) order
                        for (int k = 0; k < 26; k++) {
                            while (currentCount[k] > 0) {
                                sb.append((char) ('a' + k));
                                currentCount[k]--;
                            }
                        }
                        return sb.toString();
                    }
                }
            }
        }

        return "";
    }
}