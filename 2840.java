class Solution {
    public boolean checkStrings(String s1, String s2) {
        // Create a 2D array to track character frequency differences
        // frequencyDiff[0] tracks characters at even indices
        // frequencyDiff[1] tracks characters at odd indices
        int[][] frequencyDiff = new int[2][26];
      
        // Iterate through both strings simultaneously
        for (int i = 0; i < s1.length(); i++) {
            // Determine if current index is even (0) or odd (1)
            int parityIndex = i & 1;
          
            // Get character indices (0-25 for 'a'-'z')
            int charIndexS1 = s1.charAt(i) - 'a';
            int charIndexS2 = s2.charAt(i) - 'a';
          
            // Increment count for character from s1 at this parity position
            frequencyDiff[parityIndex][charIndexS1]++;
          
            // Decrement count for character from s2 at this parity position
            frequencyDiff[parityIndex][charIndexS2]--;
        }
      
        // Check if all frequency differences are zero
        // If zero, characters at even/odd positions match between strings
        for (int charIndex = 0; charIndex < 26; charIndex++) {
            // Check even position frequencies
            if (frequencyDiff[0][charIndex] != 0) {
                return false;
            }
            // Check odd position frequencies
            if (frequencyDiff[1][charIndex] != 0) {
                return false;
            }
        }
      
        // All frequency differences are zero, strings can be made equal
        return true;
    }
}