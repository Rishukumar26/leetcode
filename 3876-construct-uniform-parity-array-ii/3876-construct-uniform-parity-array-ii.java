class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        boolean hasOdd = false;

        for (int x : nums1) {
            if (x < min) {
                min = x;
            }
            if (x % 2 != 0) {
                hasOdd = true;
            }
        }

        // If the smallest number is odd, we can make everything odd.
        if (min % 2 != 0) {
            return true;
        }

        // If the smallest number is even, we can only succeed if there are no odd numbers.
        return !hasOdd;
    }
}