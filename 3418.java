class Solution {
    // Memoization cache: dp[row][col][neutralizationsLeft]
    private Integer[][][] dp;
    private int[][] coins;
    private int rows;
    private int cols;

    /**
     * Finds the maximum amount of coins that can be collected from top-left to bottom-right.
     * Can neutralize up to 2 negative cells (make them 0 instead of negative).
     * 
     * @param coins 2D grid where each cell contains coin values (can be negative)
     * @return Maximum coins that can be collected
     */
    public int maximumAmount(int[][] coins) {
        this.rows = coins.length;
        this.cols = coins[0].length;
        this.coins = coins;
        // Initialize memoization array: [rows][cols][3 states for neutralizations (0, 1, 2)]
        this.dp = new Integer[rows][cols][3];
      
        // Start DFS from top-left corner with 2 neutralizations available
        return dfs(0, 0, 2);
    }

    /**
     * Recursive DFS with memoization to find maximum coins from current position.
     * 
     * @param row Current row position
     * @param col Current column position
     * @param neutralizationsLeft Number of neutralizations still available
     * @return Maximum coins collectible from current position to bottom-right
     */
    private int dfs(int row, int col, int neutralizationsLeft) {
        // Base case: out of bounds
        if (row >= rows || col >= cols) {
            return Integer.MIN_VALUE / 2;  // Return very small value to avoid this path
        }
      
        // Check memoization cache
        if (dp[row][col][neutralizationsLeft] != null) {
            return dp[row][col][neutralizationsLeft];
        }
      
        // Base case: reached destination (bottom-right corner)
        if (row == rows - 1 && col == cols - 1) {
            // If we have neutralizations left, we can choose to neutralize negative values
            return neutralizationsLeft > 0 ? Math.max(0, coins[row][col]) : coins[row][col];
        }
      
        // Option 1: Collect current coin and move to next cell (right or down)
        int maxCoins = coins[row][col] + Math.max(
            dfs(row + 1, col, neutralizationsLeft),     // Move down
            dfs(row, col + 1, neutralizationsLeft)       // Move right
        );
      
        // Option 2: If current cell is negative and we have neutralizations left,
        // we can neutralize it (skip collecting negative coins)
        if (coins[row][col] < 0 && neutralizationsLeft > 0) {
            maxCoins = Math.max(maxCoins, Math.max(
                dfs(row + 1, col, neutralizationsLeft - 1),     // Neutralize and move down
                dfs(row, col + 1, neutralizationsLeft - 1)       // Neutralize and move right
            ));
        }
      
        // Store result in cache and return
        dp[row][col][neutralizationsLeft] = maxCoins;
        return maxCoins;
    }
}
