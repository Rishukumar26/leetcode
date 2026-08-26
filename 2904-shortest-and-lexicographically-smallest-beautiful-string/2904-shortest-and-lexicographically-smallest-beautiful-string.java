class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String result = "";
        int minLen = Integer.MAX_VALUE;

        int left = 0;
        int countOnes = 0;

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                countOnes++;
            }

            // Shrink window while maintaining at least k ones to ensure shortest window starting at 'left'
            while (countOnes == k) {
                // Shrink leading '0's from the left to get the minimal valid substring length for this right bound
                while (s.charAt(left) == '0') {
                    left++;
                }

                String current = s.substring(left, right + 1);
                int currentLen = current.length();

                if (currentLen < minLen) {
                    minLen = currentLen;
                    result = current;
                } else if (currentLen == minLen) {
                    if (current.compareTo(result) < 0) {
                        result = current;
                    }
                }

                // Move left pointer forward to look for the next valid window
                if (s.charAt(left) == '1') {
                    countOnes--;
                }
                left++;
            }
        }

        return result;
    }
}