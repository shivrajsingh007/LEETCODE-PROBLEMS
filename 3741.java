import java.util.*;

class Solution {
    public int minimumDistance(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        // Step 1: Store indices
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
        }

        int ans = Integer.MAX_VALUE;

        // Step 2: Process each value
        for (List<Integer> list : map.values()) {
            int n = list.size();
            
            if (n < 3) continue;

            // Step 3: Check consecutive triples
            for (int i = 0; i + 2 < n; i++) {
                int first = list.get(i);
                int third = list.get(i + 2);

                int dist = 2 * (third - first);
                ans = Math.min(ans, dist);
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}