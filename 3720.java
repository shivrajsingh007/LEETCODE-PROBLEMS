class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        // Find the maximum prefix length of target that can be matched
        int matchLen = 0;
        int[] tempCount = count.clone();
        while (matchLen < n && tempCount[target.charAt(matchLen) - 'a'] > 0) {
            tempCount[target.charAt(matchLen) - 'a']--;
            matchLen++;
        }

        // We can diverge at index up to min(matchLen, n - 1)
        int maxI = Math.min(matchLen, n - 1);

        // Prepare counts for remaining characters after matching prefix 0..maxI-1
        int[] currCount = count.clone();
        for (int j = 0; j < maxI; j++) {
            currCount[target.charAt(j) - 'a']--;
        }

        // Scan backwards to find the longest matching prefix where we can place a strictly larger character
        for (int i = maxI; i >= 0; i--) {
            int targetChar = target.charAt(i) - 'a';

            // Find the smallest available character strictly greater than target[i]
            int chosenChar = -1;
            for (int c = targetChar + 1; c < 26; c++) {
                if (currCount[c] > 0) {
                    chosenChar = c;
                    break;
                }
            }

            if (chosenChar != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(target, 0, i);
                sb.append((char) ('a' + chosenChar));
                currCount[chosenChar]--;

                // Append remaining characters in ascending order
                for (int c = 0; c < 26; c++) {
                    while (currCount[c] > 0) {
                        sb.append((char) ('a' + c));
                        currCount[c]--;
                    }
                }
                return sb.toString();
            }

            // Restore character for backtracking
            if (i > 0) {
                currCount[target.charAt(i - 1) - 'a']++;
            }
        }

        return "";
    }
}