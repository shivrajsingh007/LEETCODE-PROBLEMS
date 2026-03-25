class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        // Calculate the total sum of all elements in the grid
        long totalSum = 0;
        for (int[] row : grid) {
            for (int value : row) {
                totalSum += value;
            }
        }
      
        // If total sum is odd, we cannot partition into two equal halves
        if (totalSum % 2 != 0) {
            return false;
        }
      
        int rows = grid.length;
        int cols = grid[0].length;
      
        // Check if we can partition horizontally (between rows)
        long prefixSum = 0;
        for (int i = 0; i < rows; i++) {
            // Add current row sum to prefix sum
            for (int value : grid[i]) {
                prefixSum += value;
            }
            // Check if prefix sum equals half of total sum
            // Also ensure we're not at the last row (need at least one row on each side)
            if (prefixSum * 2 == totalSum && i < rows - 1) {
                return true;
            }
        }
      
        // Check if we can partition vertically (between columns)
        prefixSum = 0;
        for (int j = 0; j < cols; j++) {
            // Add current column sum to prefix sum
            for (int i = 0; i < rows; i++) {
                prefixSum += grid[i][j];
            }
            // Check if prefix sum equals half of total sum
            // Also ensure we're not at the last column (need at least one column on each side)
            if (prefixSum * 2 == totalSum && j < cols - 1) {
                return true;
            }
        }
      
        // No valid partition found
        return false;
    }
}
