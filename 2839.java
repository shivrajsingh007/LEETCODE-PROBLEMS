class Solution {
    public boolean canBeEqual(String s1, String s2) {
        // Create a 2D array to track character frequency differences
        // First dimension: [0] for even positions, [1] for odd positions  
        // Second dimension: 26 slots for each lowercase letter (a-z)
        int[][] characterCountDifference = new int[2][26];
      
        // Process each character position in both strings
        for (int position = 0; position < s1.length(); position++) {
            // Determine if position is even (0) or odd (1) using bitwise AND
            int parityIndex = position & 1;
          
            // Get the character index (0-25) for the current character in s1
            int s1CharIndex = s1.charAt(position) - 'a';
            // Increment count for this character at this parity position
            characterCountDifference[parityIndex][s1CharIndex]++;
          
            // Get the character index (0-25) for the current character in s2
            int s2CharIndex = s2.charAt(position) - 'a';
            // Decrement count for this character at this parity position
            characterCountDifference[parityIndex][s2CharIndex]--;
        }
      
        // Check if all character counts are balanced (equal to zero)
        // This means each parity position has the same characters in both strings
        for (int charIndex = 0; charIndex < 26; charIndex++) {
            // Check even position counts
            if (characterCountDifference[0][charIndex] != 0) {
                return false;
            }
            // Check odd position counts
            if (characterCountDifference[1][charIndex] != 0) {
                return false;
            }
        }
      
        // All character counts are balanced, strings can be made equal
        return true;
    }
}