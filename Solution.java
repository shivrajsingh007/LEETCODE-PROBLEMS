class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        // Define modulo constant for preventing overflow
        final int MOD = 12345;
      
        // Get dimensions of the input grid
        int rows = grid.length;
        int cols = grid[0].length;
      
        // Initialize result matrix to store products
        int[][] productMatrix = new int[rows][cols];
      
        // First pass: Calculate suffix products (from bottom-right to top-left)
        // Each cell will initially store the product of all elements after it
        long suffixProduct = 1;
        for (int i = rows - 1; i >= 0; i--) {
            for (int j = cols - 1; j >= 0; j--) {
                // Store current suffix product at this position
                productMatrix[i][j] = (int) suffixProduct;
                // Update suffix product by multiplying with current grid element
                suffixProduct = (suffixProduct * grid[i][j]) % MOD;
            }
        }
      
        // Second pass: Multiply by prefix products (from top-left to bottom-right)
        // This combines prefix and suffix products to get the final result
        long prefixProduct = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // Multiply stored suffix product by current prefix product
                productMatrix[i][j] = (int) ((productMatrix[i][j] * prefixProduct) % MOD);
                // Update prefix product by multiplying with current grid element
                prefixProduct = (prefixProduct * grid[i][j]) % MOD;
            }
        }
      
        return productMatrix;
    }
}