class Solution {
    /**
     * Determines if a sequence of moves returns to the origin position.
     * Starting from origin (0, 0), each move changes the position:
     * 'U' (Up): moves up by increasing y-coordinate
     * 'D' (Down): moves down by decreasing y-coordinate
     * 'L' (Left): moves left by decreasing x-coordinate
     * 'R' (Right): moves right by increasing x-coordinate
     * 
     * @param moves String containing sequence of moves (U, D, L, R)
     * @return true if the final position is origin (0, 0), false otherwise
     */
    public boolean judgeCircle(String moves) {
        // Initialize coordinates at origin
        int xCoordinate = 0;
        int yCoordinate = 0;
      
        // Process each move character
        for (char move : moves.toCharArray()) {
            switch (move) {
                case 'U':  // Move up
                    yCoordinate++;
                    break;
                case 'D':  // Move down
                    yCoordinate--;
                    break;
                case 'L':  // Move left
                    xCoordinate--;
                    break;
                case 'R':  // Move right
                    xCoordinate++;
                    break;
                default:
                    // Invalid move character, ignore
                    break;
            }
        }
      
        // Check if returned to origin
        return xCoordinate == 0 && yCoordinate == 0;
    }
}
