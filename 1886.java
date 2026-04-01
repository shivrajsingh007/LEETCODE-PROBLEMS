class Solution {
    /**
     * Determines if matrix 'mat' can be rotated (0, 90, 180, or 270 degrees clockwise)
     * to match the 'target' matrix.
     * 
     * @param mat The source matrix to rotate
     * @param target The target matrix to match
     * @return true if mat can be rotated to match target, false otherwise
     */
    public boolean findRotation(int[][] mat, int[][] target) {
        // Check all 4 possible rotations (0°, 90°, 180°, 270°)
        int rotationAttempts = 4;
      
        while (rotationAttempts-- > 0) {
            // Check if current rotation matches target
            if (equals(mat, target)) {
                return true;
            }
            // Rotate matrix 90 degrees clockwise for next check
            rotate(mat);
        }
      
        return false;
    }

    /**
     * Rotates the given square matrix 90 degrees clockwise in-place.
     * Uses a layer-by-layer approach from outside to inside.
     * 
     * @param matrix The matrix to rotate
     */
    private void rotate(int[][] matrix) {
        int n = matrix.length;
      
        // Process each layer from outer to inner
        for (int layer = 0; layer < n / 2; ++layer) {
            // Define the boundaries of current layer
            int first = layer;
            int last = n - 1 - layer;
          
            // Rotate elements in current layer
            for (int offset = first; offset < last; ++offset) {
                // Save top element
                int temp = matrix[first][offset];
              
                // Move left to top
                matrix[first][offset] = matrix[n - offset - 1][first];
              
                // Move bottom to left
                matrix[n - offset - 1][first] = matrix[last][n - offset - 1];
              
                // Move right to bottom
                matrix[last][n - offset - 1] = matrix[offset][last];
              
                // Move saved top to right
                matrix[offset][last] = temp;
            }
        }
    }

    /**
     * Checks if two matrices are identical.
     * 
     * @param matrix1 The first matrix
     * @param matrix2 The second matrix
     * @return true if matrices are identical, false otherwise
     */
    private boolean equals(int[][] matrix1, int[][] matrix2) {
        int n = matrix1.length;
      
        // Compare each element
        for (int row = 0; row < n; ++row) {
            for (int col = 0; col < n; ++col) {
                if (matrix1[row][col] != matrix2[row][col]) {
                    return false;
                }
            }
        }
      
        return true;
    }
}
