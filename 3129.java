class Solution {
    // Modulo value for large number calculations
    private final int MOD = (int) 1e9 + 7;
  
    // Memoization table: dp[zeros][ones][lastDigit]
    // lastDigit: 0 means last digit is 0, 1 means last digit is 1
    private Long[][][] dp;
  
    // Maximum consecutive same digits allowed
    private int maxConsecutive;

    public int numberOfStableArrays(int zero, int one, int limit) {
        // Initialize memoization table
        dp = new Long[zero + 1][one + 1][2];
        this.maxConsecutive = limit;
      
        // Calculate total stable arrays ending with either 0 or 1
        long result = (dfs(zero, one, 0) + dfs(zero, one, 1)) % MOD;
        return (int) result;
    }

    /**
     * Dynamic programming with memoization to count stable arrays
     * @param zerosLeft - number of zeros remaining to place
     * @param onesLeft - number of ones remaining to place
     * @param lastDigit - the last digit placed (0 or 1)
     * @return number of valid stable arrays with given constraints
     */
    private long dfs(int zerosLeft, int onesLeft, int lastDigit) {
        // Base case: invalid state with negative counts
        if (zerosLeft < 0 || onesLeft < 0) {
            return 0;
        }
      
        // Base case: only zeros left
        if (zerosLeft == 0) {
            // Valid only if last digit is 1 and remaining ones don't exceed limit
            return (lastDigit == 1 && onesLeft <= maxConsecutive) ? 1 : 0;
        }
      
        // Base case: only ones left
        if (onesLeft == 0) {
            // Valid only if last digit is 0 and remaining zeros don't exceed limit
            return (lastDigit == 0 && zerosLeft <= maxConsecutive) ? 1 : 0;
        }
      
        // Return memoized result if already calculated
        if (dp[zerosLeft][onesLeft][lastDigit] != null) {
            return dp[zerosLeft][onesLeft][lastDigit];
        }
      
        // Calculate result based on last digit placed
        if (lastDigit == 0) {
            // If last digit is 0, we're placing a 0 at current position
            // Total = arrays with one less 0 ending in 0 + arrays with one less 0 ending in 1
            // Subtract invalid arrays where we would have more than limit consecutive 0s
            long totalArrays = dfs(zerosLeft - 1, onesLeft, 0) + dfs(zerosLeft - 1, onesLeft, 1);
            long invalidArrays = dfs(zerosLeft - maxConsecutive - 1, onesLeft, 1);
            dp[zerosLeft][onesLeft][lastDigit] = (totalArrays - invalidArrays + MOD) % MOD;
        } else {
            // If last digit is 1, we're placing a 1 at current position
            // Total = arrays with one less 1 ending in 0 + arrays with one less 1 ending in 1
            // Subtract invalid arrays where we would have more than limit consecutive 1s
            long totalArrays = dfs(zerosLeft, onesLeft - 1, 0) + dfs(zerosLeft, onesLeft - 1, 1);
            long invalidArrays = dfs(zerosLeft, onesLeft - maxConsecutive - 1, 0);
            dp[zerosLeft][onesLeft][lastDigit] = (totalArrays - invalidArrays + MOD) % MOD;
        }
      
        return dp[zerosLeft][onesLeft][lastDigit];
    }
}