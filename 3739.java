class Solution {
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;

        class FenwickTree {
            private int[] tree;

            public FenwickTree(int size) {
                this.tree = new int[size + 1];
            }

            public void update(int i, int delta) {
                while (i < tree.length) {
                    tree[i] += delta;
                    i += i & (-i);
                }
            }

            public int query(int i) {
                int s = 0;
                while (i > 0) {
                    s += tree[i];
                    i -= i & (-i);
                }
                return s;
            }
        }

        // The range of prefix sums is [-n, n].
        // We map a value v to index v + n + 1.
        // The indices will be in [1, 2n + 1].
        int ft_size = 2 * n + 1;
        int offset = n + 1;
        FenwickTree ft = new FenwickTree(ft_size);

        long total_count = 0;
        int current_sum = 0;

        // Add the initial empty prefix sum (value 0) to the Fenwick tree.
        ft.update(current_sum + offset, 1);

        for (int num : nums) {
            if (num == target) {
                current_sum += 1;
            } else {
                current_sum -= 1;
            }

            // We are looking for previous prefix sums `p_prev` such that
            // current_sum - p_prev > 0, which means p_prev < current_sum.
            // This is equivalent to p_prev <= current_sum - 1.
            // We query the Fenwick tree for the count of sums up to `current_sum - 1`.
            int query_value = current_sum - 1;
            int query_index = query_value + offset;

            int count_smaller = ft.query(query_index);
            total_count += count_smaller;

            // Add the current prefix sum to the Fenwick tree.
            int update_index = current_sum + offset;
            ft.update(update_index, 1);
        }

        return total_count;
    }
}