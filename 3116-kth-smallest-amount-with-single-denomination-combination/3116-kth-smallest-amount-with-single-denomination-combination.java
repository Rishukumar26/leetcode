class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int numSubsets = 1 << n;
        
        // Precompute LCM and sign (-1^(size-1)) for all subsets
        long[] lcms = new long[numSubsets];
        int[] signs = new int[numSubsets];
        
        lcms[0] = 1;
        signs[0] = -1;
        
        for (int mask = 1; mask < numSubsets; mask++) {
            int lastBit = Integer.numberOfTrailingZeros(mask);
            int prevMask = mask ^ (1 << lastBit);
            
            long prevLcm = lcms[prevMask];
            if (prevLcm == -1) {
                lcms[mask] = -1; // Overflow/exceeds bounds
            } else {
                long currentLcm = lcm(prevLcm, coins[lastBit]);
                lcms[mask] = currentLcm;
            }
            signs[mask] = -signs[prevMask];
        }

        // Binary Search
        long low = 1;
        long minCoin = coins[0];
        for (int c : coins) minCoin = Math.min(minCoin, c);
        long high = minCoin * (long) k;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            long count = countAmounts(mid, numSubsets, lcms, signs);

            if (count >= k) {
                ans = mid;
                high = mid - 1; // Try to find a smaller valid amount
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private long countAmounts(long target, int numSubsets, long[] lcms, int[] signs) {
        long count = 0;
        for (int mask = 1; mask < numSubsets; mask++) {
            if (lcms[mask] != -1) {
                count += signs[mask] * (target / lcms[mask]);
            }
        }
        return count;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}