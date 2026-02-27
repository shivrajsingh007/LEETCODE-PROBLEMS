import java.util.*;

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();
        int cnt0 = 0;
        for (char c : s.toCharArray()) {
            if (c == '0') cnt0++;
        }

        // We use two TreeSets to store unvisited '0' counts based on parity.
        // This allows us to efficiently find counts in the range [L, R].
        TreeSet<Integer>[] ts = new TreeSet[2];
        ts[0] = new TreeSet<>();
        ts[1] = new TreeSet<>();
        for (int i = 0; i <= n; i++) {
            ts[i % 2].add(i);
        }

        // Initial state: remove the starting count of zeros
        ts[cnt0 % 2].remove(cnt0);
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(cnt0);

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int cur = queue.poll();
                if (cur == 0) return steps;

                // Calculate the range of possible number of zeros after one flip
                // Lower bound: Flip as many zeros as possible (up to k)
                int L = Math.abs(cur - k);
                // Upper bound: Flip as many ones as possible (up to k)
                int R = n - Math.abs(n - cur - k);

                // The parity of the new count is always (cur + k) % 2
                TreeSet<Integer> targetSet = ts[(cur + k) % 2];
                
                // Find all unvisited counts in [L, R] and add to queue
                Integer next = targetSet.ceiling(L);
                while (next != null && next <= R) {
                    queue.offer(next);
                    targetSet.remove(next);
                    next = targetSet.ceiling(L);
                }
            }
            steps++;
        }

        return -1;
    }
}