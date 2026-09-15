class Solution {
    public int maxPalindromes(String s, int k) {
        int count = 0;
        int n = s.length();
        int lastEnd = -1; // End index of the last chosen palindrome

        for (int i = 0; i < n; i++) {
            // Check for palindrome of length k centered around i
            if (isPalindrome(s, i - k / 2, i + k / 2 - (k % 2 == 0 ? 1 : 0), lastEnd)) {
                count++;
                lastEnd = i + k / 2 - (k % 2 == 0 ? 1 : 0);
            } 
            // Check for palindrome of length k + 1 centered around i
            else if (isPalindrome(s, i - k / 2, i + k / 2 + (k % 2 == 0 ? 0 : 1), lastEnd)) {
                count++;
                lastEnd = i + k / 2 + (k % 2 == 0 ? 0 : 1);
            }
        }

        return count;
    }

    private boolean isPalindrome(String s, int left, int right, int lastEnd) {
        if (left <= lastEnd || right >= s.length()) {
            return false;
        }
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}