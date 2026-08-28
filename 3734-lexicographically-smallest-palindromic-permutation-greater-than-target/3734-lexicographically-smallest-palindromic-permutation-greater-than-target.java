import java.util.*;

class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        // Check palindrome validity
        int oddCount = 0;
        int oddChar = -1;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                oddCount++;
                oddChar = i;
            }
        }
        if ((n % 2 == 0 && oddCount > 0) || (n % 2 == 1 && oddCount != 1)) {
            return "";
        }

        int halfLen = n / 2;
        int[] halfFreq = new int[26];
        for (int i = 0; i < 26; i++) {
            halfFreq[i] = freq[i] / 2;
        }

        // Try to match longest prefix of target's first half
        for (int l = halfLen; l >= 0; l--) {
            int[] curFreq = halfFreq.clone();
            boolean validPrefix = true;
            char[] prefix = new char[halfLen];

            // Match prefix up to index l - 1
            for (int i = 0; i < l; i++) {
                int c = target.charAt(i) - 'a';
                if (curFreq[c] > 0) {
                    prefix[i] = target.charAt(i);
                    curFreq[c]--;
                } else {
                    validPrefix = false;
                    break;
                }
            }
            if (!validPrefix) continue;

            // At index l, try characters strictly larger than target[l] (if l < halfLen)
            // Or if l == halfLen, check if exact prefix mirror + mid > target
            int startChar = (l < halfLen) ? (target.charAt(l) - 'a' + 1) : 0;

            for (int c = startChar; c < 26; c++) {
                if (l < halfLen && curFreq[c] == 0) continue;

                int[] tempFreq = curFreq.clone();
                if (l < halfLen) {
                    prefix[l] = (char) ('a' + c);
                    tempFreq[c]--;
                }

                // Fill remaining positions of first half with smallest available characters
                int idx = l + (l < halfLen ? 1 : 0);
                for (int ch = 0; ch < 26; ch++) {
                    while (tempFreq[ch] > 0) {
                        prefix[idx++] = (char) ('a' + ch);
                        tempFreq[ch]--;
                    }
                }

                // Construct full palindrome
                StringBuilder sb = new StringBuilder();
                sb.append(new String(prefix));
                if (n % 2 == 1) {
                    sb.append((char) ('a' + oddChar));
                }
                for (int i = halfLen - 1; i >= 0; i--) {
                    sb.append(prefix[i]);
                }

                String cand = sb.toString();
                if (cand.compareTo(target) > 0) {
                    return cand;
                }
            }
        }

        return "";
    }
}