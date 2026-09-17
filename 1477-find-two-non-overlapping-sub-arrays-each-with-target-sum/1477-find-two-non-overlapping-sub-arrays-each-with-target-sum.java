import java.util.Arrays;

class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        Arrays.fill(minLen, Integer.MAX_VALUE);
        
        int left = 0, currentSum = 0;
        int minSumOfLengths = Integer.MAX_VALUE;
        int minSoFar = Integer.MAX_VALUE;
        
        for (int right = 0; right < n; right++) {
            currentSum += arr[right];
            
            // Shrink window if sum exceeds target
            while (currentSum > target && left <= right) {
                currentSum -= arr[left];
                left++;
            }
            
            // Found a valid sub-array
            if (currentSum == target) {
                int currentLen = right - left + 1;
                
                // If there's a valid sub-array to the left that doesn't overlap
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    minSumOfLengths = Math.min(minSumOfLengths, currentLen + minLen[left - 1]);
                }
                
                minSoFar = Math.min(minSoFar, currentLen);
            }
            
            // Store the shortest valid sub-array seen up to the current index
            minLen[right] = minSoFar;
        }
        
        return minSumOfLengths == Integer.MAX_VALUE ? -1 : minSumOfLengths;
    }
}