class Solution {
    public int bitwiseComplement(int n) {
        // Special case: complement of 0 is 1
        if (n == 0) {
            return 1;
        }
      
        int result = 0;
        int bitPosition = 0;
      
        // Process each bit of n from right to left
        while (n != 0) {
            // Get the least significant bit of n and flip it (0->1, 1->0)
            int flippedBit = (n & 1) ^ 1;
          
            // Place the flipped bit at the correct position in result
            result |= flippedBit << bitPosition;
          
            // Move to the next bit position
            bitPosition++;
          
            // Right shift n to process the next bit
            //EASUS APPROACH
            n >>= 1;
        }
      
        return result;
    }
}
