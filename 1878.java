class Solution {
    public int[] getBiggestThree(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
      
        // Prefix sum arrays for diagonal directions
        // diagonalSumDownRight[i][j] stores sum along down-right diagonal ending at (i-1, j-1)
        int[][] diagonalSumDownRight = new int[rows + 1][cols + 2];
        // diagonalSumDownLeft[i][j] stores sum along down-left diagonal ending at (i-1, j-1)
        int[][] diagonalSumDownLeft = new int[rows + 1][cols + 2];
      
        // Build prefix sums for both diagonal directions
        for (int i = 1; i <= rows; ++i) {
            for (int j = 1; j <= cols; ++j) {
                // Down-right diagonal: from top-left to bottom-right
                diagonalSumDownRight[i][j] = diagonalSumDownRight[i - 1][j - 1] + grid[i - 1][j - 1];
                // Down-left diagonal: from top-right to bottom-left
                diagonalSumDownLeft[i][j] = diagonalSumDownLeft[i - 1][j + 1] + grid[i - 1][j - 1];
            }
        }
      
        // TreeSet to maintain unique sums in sorted order
        TreeSet<Integer> uniqueSums = new TreeSet<>();
      
        // Iterate through each cell as potential rhombus center
        for (int centerRow = 1; centerRow <= rows; ++centerRow) {
            for (int centerCol = 1; centerCol <= cols; ++centerCol) {
                // Calculate maximum possible rhombus radius from this center
                int maxRadius = Math.min(
                    Math.min(centerRow - 1, rows - centerRow), 
                    Math.min(centerCol - 1, cols - centerCol)
                );
              
                // Add single cell (radius = 0) as a rhombus
                uniqueSums.add(grid[centerRow - 1][centerCol - 1]);
              
                // Try all possible rhombus sizes with current cell as center
                for (int radius = 1; radius <= maxRadius; ++radius) {
                    // Calculate sum of four edges of the rhombus using prefix sums
                    // Top-right edge
                    int topRightEdge = diagonalSumDownRight[centerRow + radius][centerCol] 
                                     - diagonalSumDownRight[centerRow][centerCol - radius];
                    // Bottom-right edge
                    int bottomRightEdge = diagonalSumDownRight[centerRow][centerCol + radius] 
                                        - diagonalSumDownRight[centerRow - radius][centerCol];
                    // Top-left edge
                    int topLeftEdge = diagonalSumDownLeft[centerRow][centerCol - radius] 
                                    - diagonalSumDownLeft[centerRow - radius][centerCol];
                    // Bottom-left edge
                    int bottomLeftEdge = diagonalSumDownLeft[centerRow + radius][centerCol] 
                                       - diagonalSumDownLeft[centerRow][centerCol + radius];
                  
                    // Calculate total rhombus sum
                    // Adjust for overlapping corners (bottom vertex counted twice, top vertex not counted)
                    int rhombusSum = topRightEdge + bottomRightEdge + topLeftEdge + bottomLeftEdge 
                                   - grid[centerRow + radius - 1][centerCol - 1]  // Remove duplicate bottom vertex
                                   + grid[centerRow - radius - 1][centerCol - 1];  // Add missing top vertex
                  
                    uniqueSums.add(rhombusSum);
                }
              
                // Keep only the 3 largest sums
                while (uniqueSums.size() > 3) {
                    uniqueSums.pollFirst();  // Remove smallest element
                }
            }
        }
      
        // Convert TreeSet to array in descending order
        int[] result = new int[uniqueSums.size()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = uniqueSums.pollLast();  // Extract largest element first
        }
      
        return result;
    }
}