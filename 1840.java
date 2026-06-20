	import java.util.*;
class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        List<int[]> res = new ArrayList<>();
        for (int[] r : restrictions) res.add(r);
        res.add(new int[]{1, 0});
        if (res.isEmpty() || res.get(res.size()-1)[0] != n)
            res.add(new int[]{n, n-1});
        res.sort(Comparator.comparingInt(a -> a[0]));
        int m = res.size();
        // Forward pass
        for (int i = 1; i < m; ++i) {
            int d = res.get(i)[0] - res.get(i-1)[0];
            res.get(i)[1] = Math.min(res.get(i)[1], res.get(i-1)[1] + d);
        }
        // Backward pass
        for (int i = m-2; i >= 0; --i) {
            int d = res.get(i+1)[0] - res.get(i)[0];
            res.get(i)[1] = Math.min(res.get(i)[1], res.get(i+1)[1] + d);
        }
        int ans = 0;
        for (int i = 1; i < m; ++i) {
            int left = res.get(i-1)[0], h1 = res.get(i-1)[1];
            int right = res.get(i)[0], h2 = res.get(i)[1];
            int d = right - left;
            int peak = (h1 + h2 + d) / 2;
            ans = Math.max(ans, peak);
        }
        return ans;
    }
}