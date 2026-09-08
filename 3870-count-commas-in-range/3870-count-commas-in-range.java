class Solution {
    public int countCommas(int n) {
        int totalCommas = 0;
        long threshold = 1000L;

        while (n >= threshold) {
            totalCommas += (int)(n - threshold + 1);
            threshold *= 1000L;
        }

        return totalCommas;
    }
}