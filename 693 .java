class Solution {
    public boolean hasAlternatingBits(int n) {
        int prev = n & 1;  // Get last bit
        
        n = n >> 1;  // Right shift
        
        while (n > 0) {
            int curr = n & 1;  // Get current last bit
            
            if (curr == prev) {
                return false;
            }
            
            prev = curr;
            n = n >> 1;
        }
        
        return true;
    }
}
