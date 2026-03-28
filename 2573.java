class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char[] result = new char[n];
        int currentIndex = 0;
      
        // Try to assign characters from 'a' to 'z' to build the string
        for (char currentChar = 'a'; currentChar <= 'z'; ++currentChar) {
            // Find the next unassigned position
            while (currentIndex < n && result[currentIndex] != '\0') {
                ++currentIndex;
            }
          
            // If all positions are filled, we're done
            if (currentIndex == n) {
                break;
            }
          
            // Assign current character to all positions that should have it
            // based on LCP values (if lcp[currentIndex][j] > 0, they share a prefix)
            for (int j = currentIndex; j < n; ++j) {
                if (lcp[currentIndex][j] > 0) {
                    result[j] = currentChar;
                }
            }
        }
      
        // Check if all positions have been assigned a character
        for (int i = 0; i < n; ++i) {
            if (result[i] == '\0') {
                return "";  // Not enough characters to satisfy LCP constraints
            }
        }
      
        // Validate the constructed string against LCP matrix
        for (int i = n - 1; i >= 0; --i) {
            for (int j = n - 1; j >= 0; --j) {
                if (result[i] == result[j]) {
                    // Characters match, verify LCP value
                    if (i == n - 1 || j == n - 1) {
                        // At boundary, LCP should be exactly 1
                        if (lcp[i][j] != 1) {
                            return "";
                        }
                    } else {
                        // LCP should be 1 + LCP of next positions
                        if (lcp[i][j] != lcp[i + 1][j + 1] + 1) {
                            return "";
                        }
                    }
                } else {
                    // Characters don't match, LCP should be 0
                    if (lcp[i][j] > 0) {
                        return "";
                    }
                }
            }
        }
      
        return String.valueOf(result);
    }
}