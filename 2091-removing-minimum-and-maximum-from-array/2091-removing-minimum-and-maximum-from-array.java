class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIdx = 0;
        int maxIdx = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIdx]) minIdx = i;
            if (nums[i] > nums[maxIdx]) maxIdx = i;
        }

        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);

        int option1 = j + 1;                  // Remove both from front
        int option2 = n - i;                  // Remove both from back
        int option3 = (i + 1) + (n - j);      // Remove one from front, one from back

        return Math.min(option1, Math.min(option2, option3));
    }
}