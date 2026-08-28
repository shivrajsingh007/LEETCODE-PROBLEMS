class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        // More than one odd-frequency character cannot form a palindrome
        if (oddCount > 1) {
            return "";
        }

        int m = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        // 1. Try exact match of the first m characters
        int[] curCount = halfCount.clone();
        boolean canMatchExact = true;
        for (int i = 0; i < m; i++) {
            int c = target.charAt(i) - 'a';
            if (curCount[c] > 0) {
                curCount[c]--;
            } else {
                canMatchExact = false;
                break;
            }
        }

        if (canMatchExact) {
            String cand = buildPalindrome(target.substring(0, m), n, midChar);
            if (cand.compareTo(target) > 0) {
                return cand;
            }
        }

        // 2. Try placing a strictly larger character at index i (from m - 1 down to 0)
        for (int i = m - 1; i >= 0; i--) {
            curCount = halfCount.clone();
            boolean prefixValid = true;
            for (int j = 0; j < i; j++) {
                int c = target.charAt(j) - 'a';
                if (curCount[c] > 0) {
                    curCount[c]--;
                } else {
                    prefixValid = false;
                    break;
                }
            }

            if (!prefixValid) continue;

            for (int c = (target.charAt(i) - 'a') + 1; c < 26; c++) {
                if (curCount[c] > 0) {
                    curCount[c]--;
                    StringBuilder firstHalf = new StringBuilder();
                    firstHalf.append(target, 0, i);
                    firstHalf.append((char) ('a' + c));

                    for (int k = 0; k < 26; k++) {
                        while (curCount[k] > 0) {
                            firstHalf.append((char) ('a' + k));
                            curCount[k]--;
                        }
                    }

                    return buildPalindrome(firstHalf.toString(), n, midChar);
                }
            }
        }

        return "";
    }

    private String buildPalindrome(String firstHalf, int n, char midChar) {
        StringBuilder sb = new StringBuilder(n);
        sb.append(firstHalf);
        if (n % 2 != 0) {
            sb.append(midChar);
        }
        for (int j = firstHalf.length() - 1; j >= 0; j--) {
            sb.append(firstHalf.charAt(j));
        }
        return sb.toString();
    }
}