class Solution {
    public int numberOfSpecialChars(String word) {
        // Create a boolean array to track presence of characters
        // Size is 'z' + 1 (123) to accommodate all uppercase and lowercase letters
        boolean[] characterPresence = new boolean['z' + 1];
      
        // Iterate through each character in the word
        for (int i = 0; i < word.length(); i++) {
            // Mark the character as present using its ASCII value as index
            characterPresence[word.charAt(i)] = true;
        }
      
        // Initialize counter for special characters
        int specialCharCount = 0;
      
        // Check each letter from 'a' to 'z' (26 letters total)
        for (int i = 0; i < 26; i++) {
            // A character is special if both its lowercase and uppercase forms exist
            if (characterPresence['a' + i] && characterPresence['A' + i]) {
                specialCharCount++;
            }
        }
      
        // Return the total count of special characters
        return specialCharCount;
    }
}