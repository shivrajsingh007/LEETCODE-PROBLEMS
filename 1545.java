class Solution {
    /**
     * Finds the k-th bit in the n-th binary string S_n.
     * The binary string is constructed recursively where:
     * S_1 = "0"
     * S_i = S_(i-1) + "1" + reverse(invert(S_(i-1)))
     * 
     * @param n the index of the binary string (1 to 20)
     * @param k the position of the bit to find (1-indexed)
     * @return the k-th bit as a character ('0' or '1')
     */
    public char findKthBit(int n, int k) {
        // Convert the numeric result (0 or 1) to character ('0' or '1')
        return (char) ('0' + dfs(n, k));
    }

    /**
     * Recursively determines the value of the k-th bit in S_n.
     * 
     * @param n the index of the binary string
     * @param k the position of the bit (1-indexed)
     * @return 0 or 1 representing the bit value
     */
    private int dfs(int n, int k) {
        // Base case: first position is always '0'
        if (k == 1) {
            return 0;
        }
      
        // Check if k is a power of 2 (middle positions are always '1')
        // k & (k - 1) equals 0 only when k is a power of 2
        if ((k & (k - 1)) == 0) {
            return 1;
        }
      
        // Calculate the total length of S_n: 2^n - 1
        int totalLength = 1 << n;  // 2^n
      
        // If k is in the first half (before middle), recurse on S_(n-1)
        if (k * 2 < totalLength - 1) {
            return dfs(n - 1, k);
        }
      
        // If k is in the second half (after middle), find corresponding position
        // in S_(n-1) and invert the result (XOR with 1)
        // The second half is reverse(invert(S_(n-1))), so we map k to its mirror position
        return dfs(n - 1, totalLength - k) ^ 1;
    }
}