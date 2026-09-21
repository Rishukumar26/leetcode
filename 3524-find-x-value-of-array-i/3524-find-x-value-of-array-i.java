class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        long[] dp = new long[k];

        for (int num : nums) {
            long[] nextDp = new long[k];
            int mod = num % k;

            // Start a new subarray with just the current element
            nextDp[mod]++;

            // Extend existing subarrays from previous index
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int nextMod = (r * mod) % k;
                    nextDp[nextMod] += dp[r];
                }
            }

            // Accumulate the counts for subarrays ending at this index
            for (int r = 0; r < k; r++) {
                result[r] += nextDp[r];
            }

            dp = nextDp;
        }

        return result;
    }
}