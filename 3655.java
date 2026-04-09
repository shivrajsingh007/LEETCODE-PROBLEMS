import java.util.*;

class Solution {
    static final int MOD = 1_000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int n = nums.length;
        int B = (int) Math.sqrt(n) + 1;

        // For small k: store multiplier for each (k, remainder, index block)
        List<int[]> smallQueries = new ArrayList<>();

        // Process large k directly
        for (int[] q : queries) {
            int l = q[0], r = q[1], k = q[2], v = q[3];

            if (k >= B) {
                for (int i = l; i <= r; i += k) {
                    nums[i] = (int)((1L * nums[i] * v) % MOD);
                }
            } else {
                smallQueries.add(q);
            }
        }

        // For small k, process per index
        for (int i = 0; i < n; i++) {
            long val = nums[i];

            for (int[] q : smallQueries) {
                int l = q[0], r = q[1], k = q[2], v = q[3];

                if (i >= l && i <= r && (i - l) % k == 0) {
                    val = (val * v) % MOD;
                }
            }

            nums[i] = (int) val;
        }

        // XOR
        int xor = 0;
        for (int x : nums) xor ^= x;

        return xor;
    }
}