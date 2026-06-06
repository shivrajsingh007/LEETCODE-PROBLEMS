class Solution {
    public int[] leftRigthDifference(int[] nums) {
        // Initialize left sum as 0 and right sum as total sum of array
        int leftSum = 0;
        int rightSum = Arrays.stream(nums).sum();
      
        // Get array length
        int n = nums.length;
      
        // Initialize result array
        int[] result = new int[n];
      
        // Iterate through each element
        for (int i = 0; i < n; i++) {
            // Exclude current element from right sum
            rightSum -= nums[i];
          
            // Calculate absolute difference between left and right sums
            result[i] = Math.abs(leftSum - rightSum);
          
            // Include current element in left sum for next iteration
            leftSum += nums[i];
        }
      
        return result;
    }
}