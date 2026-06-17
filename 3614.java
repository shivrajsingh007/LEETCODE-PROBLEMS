class Solution {
    public char processStr(String s, long k) {
        int n = s.length();
        long[] len = new long[n + 1];

        // Build lengths after each operation
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                len[i + 1] = len[i] + 1;
            } else if (ch == '*') {
                len[i + 1] = Math.max(0, len[i] - 1);
            } else if (ch == '#') {
                len[i + 1] = Math.min(Long.MAX_VALUE / 2, len[i] * 2);
            } else { // '%'
                len[i + 1] = len[i];
            }
        }

        if (k >= len[n]) return '.';

        // Work backwards
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);

            if (ch >= 'a' && ch <= 'z') {
                if (k == len[i]) {
                    return ch;
                }
            } else if (ch == '*') {
                // Before deletion length was len[i], after deletion len[i+1]
                // k remains unchanged
            } else if (ch == '#') {
                long half = len[i];
                if (k >= half) {
                    k -= half;
                }
            } else if (ch == '%') {
                long size = len[i];
                k = size - 1 - k;
            }
        }

        return '.';
    }
}