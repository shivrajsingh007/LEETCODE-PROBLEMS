class Solution {

    String s;
    long[][][][][] cntDp;
    long[][][][][] sumDp;
    boolean[][][][][] vis;

    public long totalWaviness(long num1, long num2) {
        return calc(num2) - calc(num1 - 1);
    }

    private long calc(long n) {
        if (n < 0) return 0;

        s = String.valueOf(n);
        int m = s.length();

        cntDp = new long[m + 1][11][11][2][2];
        sumDp = new long[m + 1][11][11][2][2];
        vis   = new boolean[m + 1][11][11][2][2];

        return dfs(0, 10, 10, true, false)[1];
    }

    private long[] dfs(int pos, int p1, int p2,
                       boolean tight, boolean started) {

        if (pos == s.length()) {
            return new long[]{1, 0};
        }

        int t = tight ? 1 : 0;
        int st = started ? 1 : 0;

        if (!tight && vis[pos][p1][p2][st][0]) {
            return new long[]{
                cntDp[pos][p1][p2][st][0],
                sumDp[pos][p1][p2][st][0]
            };
        }

        long cnt = 0;
        long sum = 0;

        int lim = tight ? s.charAt(pos) - '0' : 9;

        for (int d = 0; d <= lim; d++) {

            boolean nt = tight && d == lim;
            boolean ns = started || d != 0;

            if (!ns) {
                long[] nxt = dfs(pos + 1, 10, 10, nt, false);
                cnt += nxt[0];
                sum += nxt[1];
                continue;
            }

            int add = 0;

            if (started && p2 != 10) {
                if ((p1 > p2 && p1 > d) ||
                    (p1 < p2 && p1 < d)) {
                    add = 1;
                }
            }

            int np2 = started ? p1 : d;
            int np1 = d;

            long[] nxt = dfs(pos + 1, np1, np2, nt, true);

            cnt += nxt[0];
            sum += nxt[1] + add * nxt[0];
        }

        if (!tight) {
            vis[pos][p1][p2][st][0] = true;
            cntDp[pos][p1][p2][st][0] = cnt;
            sumDp[pos][p1][p2][st][0] = sum;
        }

        return new long[]{cnt, sum};
    }
}