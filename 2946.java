class Solution {
    /**
     * Checks if a matrix remains similar after k cyclic shifts.
     * - Even rows (0-indexed) are shifted left by k positions
     * - Odd rows (0-indexed) are shifted right by k positions
     * 
     * @param mat The input matrix
     * @param k The number of positions to shift
     * @return true if the matrix remains the same after shifts, false otherwise
     */
    public boolean areSimilar(int[][] mat, int k) {
        int rowCount = mat.length;
        int columnCount = mat[0].length;
      
        // Optimize k to avoid unnecessary full rotations
        k = k % columnCount;
      
        // Check each element in the matrix
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
              
                // For odd rows (1, 3, 5...), check right shift
                if (row % 2 == 1) {
                    int shiftedPosition = (col + k) % columnCount;
                    if (mat[row][col] != mat[row][shiftedPosition]) {
                        return false;
                    }
                }
              
                // For even rows (0, 2, 4...), check left shift
                if (row % 2 == 0) {
                    // Add columnCount to handle negative modulo correctly
                    int shiftedPosition = (col - k + columnCount) % columnCount;
                    if (mat[row][col] != mat[row][shiftedPosition]) {
                        return false;
                    }
                }
            }
        }
      
        // All elements match their shifted positions
        return true;
    }
}
