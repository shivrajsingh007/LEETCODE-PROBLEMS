class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
      
        // Create a 2D prefix sum array with padding for easier calculation
        // prefixSum[i][j] represents the sum of all elements in the submatrix 
        // from (0,0) to (i-1, j-1) in the original grid
        int[][] prefixSum = new int[rows + 1][cols + 1];
      
        int count = 0;
      
        // Build prefix sum array and count valid submatrices
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                // Calculate prefix sum using inclusion-exclusion principle:
                // Add the sum from top and left, subtract the overlapping top-left,
                // then add the current element
                prefixSum[i][j] = prefixSum[i - 1][j]           // Sum from top
                                + prefixSum[i][j - 1]           // Sum from left
                                - prefixSum[i - 1][j - 1]       // Remove overlap
                                + grid[i - 1][j - 1];           // Add current element
              
                // If the sum of submatrix from (0,0) to current position is <= k,
                // increment the counter
                if (prefixSum[i][j] <= k) {
                    count++;
                }
            }
        }
      
        return count;
    }
}