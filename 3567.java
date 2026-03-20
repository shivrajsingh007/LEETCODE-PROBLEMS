class Solution {
    /**
     * Finds the minimum absolute difference between any two distinct elements 
     * in each k x k subgrid of the given grid.
     * 
     * @param grid The input 2D grid of integers
     * @param k The size of the square subgrid
     * @return A 2D array where each element represents the minimum absolute difference
     *         for the corresponding k x k subgrid (0 if all elements are the same)
     */
    public int[][] minAbsDiff(int[][] grid, int k) {
        int rows = grid.length;
        int cols = grid[0].length;
      
        // Result array to store minimum absolute differences for each subgrid
        int[][] result = new int[rows - k + 1][cols - k + 1];
      
        // Iterate through all possible starting positions for k x k subgrids
        for (int startRow = 0; startRow <= rows - k; startRow++) {
            for (int startCol = 0; startCol <= cols - k; startCol++) {
              
                // Collect all elements from the current k x k subgrid
                List<Integer> subgridElements = new ArrayList<>();
                for (int row = startRow; row < startRow + k; row++) {
                    for (int col = startCol; col < startCol + k; col++) {
                        subgridElements.add(grid[row][col]);
                    }
                }
              
                // Sort elements to find minimum difference between adjacent elements
                Collections.sort(subgridElements);
              
                // Find minimum absolute difference between any two distinct elements
                int minDifference = Integer.MAX_VALUE;
                for (int index = 1; index < subgridElements.size(); index++) {
                    int previousElement = subgridElements.get(index - 1);
                    int currentElement = subgridElements.get(index);
                  
                    // Only consider distinct elements
                    if (previousElement != currentElement) {
                        minDifference = Math.min(minDifference, 
                                               Math.abs(previousElement - currentElement));
                    }
                }
              
                // If no distinct elements found (all elements are the same), set to 0
                result[startRow][startCol] = (minDifference == Integer.MAX_VALUE) ? 0 : minDifference;
            }
        }
      
        return result;
    }
}