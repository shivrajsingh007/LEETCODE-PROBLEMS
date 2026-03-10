class Solution {
    // Modulo value for preventing integer overflow
    private static final int MOD = 1_000_000_007;
  
    // Memoization table: dp[zeroCount][oneCount][lastElement]
    // where lastElement: 0 means last element is 0, 1 means last element is 1
    private Long[][][] dp;
  
    // Maximum consecutive elements allowed
    private int maxConsecutive;

    public int numberOfStableArrays(int zero, int one, int limit) {
        // Initialize memoization table
        dp = new Long[zero + 1][one + 1][2];
        this.maxConsecutive = limit;
      
        // Calculate total stable arrays ending with either 0 or 1
        long totalArrays = (dfs(zero, one, 0) + dfs(zero, one, 1)) % MOD;
        return (int) totalArrays;
    }

    /**
     * Recursively calculates the number of stable arrays
     * @param zerosRemaining - number of zeros left to place
     * @param onesRemaining - number of ones left to place
     * @param lastElementType - type of the last element placed (0 or 1)
     * @return number of valid stable arrays for given parameters
     */
    private long dfs(int zerosRemaining, int onesRemaining, int lastElementType) {
        // Base case: invalid state if negative counts
        if (zerosRemaining < 0 || onesRemaining < 0) {
            return 0;
        }
      
        // Base case: only zeros remaining
        if (zerosRemaining == 0) {
            // Valid only if last element was 0 and remaining ones don't exceed limit
            return (lastElementType == 1 && onesRemaining <= maxConsecutive) ? 1 : 0;
        }
      
        // Base case: only ones remaining
        if (onesRemaining == 0) {
            // Valid only if last element was 1 and remaining zeros don't exceed limit
            return (lastElementType == 0 && zerosRemaining <= maxConsecutive) ? 1 : 0;
        }
      
        // Return memoized result if already computed
        if (dp[zerosRemaining][onesRemaining][lastElementType] != null) {
            return dp[zerosRemaining][onesRemaining][lastElementType];
        }
      
        long result;
      
        if (lastElementType == 0) {
            // If last element is 0, we're placing a 0 at current position
            // Sum of arrays with one less 0, ending with either 0 or 1
            long totalWays = dfs(zerosRemaining - 1, onesRemaining, 0) + 
                            dfs(zerosRemaining - 1, onesRemaining, 1);
          
            // Subtract invalid arrays where we'd have more than 'limit' consecutive 0s
            long invalidWays = dfs(zerosRemaining - maxConsecutive - 1, onesRemaining, 1);
          
            // Apply modulo arithmetic to handle negative values correctly
            result = (totalWays - invalidWays + MOD) % MOD;
        } else {
            // If last element is 1, we're placing a 1 at current position
            // Sum of arrays with one less 1, ending with either 0 or 1
            long totalWays = dfs(zerosRemaining, onesRemaining - 1, 0) + 
                            dfs(zerosRemaining, onesRemaining - 1, 1);
          
            // Subtract invalid arrays where we'd have more than 'limit' consecutive 1s
            long invalidWays = dfs(zerosRemaining, onesRemaining - maxConsecutive - 1, 0);
          
            // Apply modulo arithmetic to handle negative values correctly
            result = (totalWays - invalidWays + MOD) % MOD;
        }
      
        // Memoize and return the result
        dp[zerosRemaining][onesRemaining][lastElementType] = result;
        return result;
    }
}