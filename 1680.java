class Solution {
    public int concatenatedBinary(int n) {
        // Define modulo constant for preventing integer overflow
        final int MOD = 1000000007;
      
        // Initialize result variable to store the concatenated binary value
        long result = 0;
      
        // Iterate through each number from 1 to n
        for (int currentNumber = 1; currentNumber <= n; currentNumber++) {
            // Calculate the number of bits needed to represent currentNumber
            // This is done by: 32 - (number of leading zeros in 32-bit representation)
            int bitsRequired = 32 - Integer.numberOfLeadingZeros(currentNumber);
          
            // Shift existing result left by bitsRequired positions to make room
            // Then perform bitwise OR with currentNumber to append it
            // Apply modulo to keep the result within bounds
            result = ((result << bitsRequired) | currentNumber) % MOD;
        }
      
        // Cast and return the final result as an integer
        return (int) result;
    }
}