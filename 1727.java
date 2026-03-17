class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
      
        // Transform the matrix to store consecutive 1s count from top
        // For each cell with value 1, update it to be the count of consecutive 1s above it plus itself
        for (int row = 1; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                if (matrix[row][col] == 1) {
                    // Add the count of consecutive 1s from the cell above
                    matrix[row][col] = matrix[row - 1][col] + 1;
                }
            }
        }
      
        int maxArea = 0;
      
        // For each row, find the maximum rectangle area that can be formed
        for (int[] currentRow : matrix) {
            // Sort the row to group heights together in ascending order
            Arrays.sort(currentRow);
          
            // Calculate the maximum rectangle area for this row
            // Start from the tallest height (rightmost after sorting)
            int width = 1;
            for (int col = numCols - 1; col >= 0 && currentRow[col] > 0; col--) {
                // Calculate area: height * width
                // Height is currentRow[col], width increases as we move left
                int currentArea = currentRow[col] * width;
                maxArea = Math.max(maxArea, currentArea);
                width++;
            }
        }
      
        return maxArea;
    }
}