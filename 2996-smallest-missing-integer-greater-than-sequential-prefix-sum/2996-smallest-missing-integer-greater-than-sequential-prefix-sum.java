import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingInteger(int[] nums) {
        // 1. Find the longest sequential prefix sum starting at index 0
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }
        
        // 2. Add all elements into a Set for quick O(1) lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        // 3. Find the smallest integer >= sum that is missing from nums
        while (set.contains(sum)) {
            sum++;
        }
        
        return sum;
    }
}