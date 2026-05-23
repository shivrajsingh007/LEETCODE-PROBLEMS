class Solution {
    /**
     * Checks if the array is a rotation of a sorted array.
     * A sorted array rotated at some pivot will have at most one position
     * where the current element is greater than the next element.
     * 
     * @param nums the input array to check
     * @return true if the array is a valid rotation of a sorted array, false otherwise
     */
    public boolean check(int[] nums) {
        // Count the number of "breaks" where current element > next element
        int breakCount = 0;
        int arrayLength = nums.length;
      
        // Iterate through all elements and compare with the next element (circular)
        for (int i = 0; i < arrayLength; i++) {
            // Use modulo to wrap around to index 0 when reaching the last element
            int nextIndex = (i + 1) % arrayLength;
          
            // Check if current element breaks the non-decreasing order
            if (nums[i] > nums[nextIndex]) {
                breakCount++;
            }
        }
      
        // Valid rotation has at most 1 break point
        return breakCount <= 1;
    }
}