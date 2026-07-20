class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        int m = grid.length;
        int n = grid[0].length;

        int total = m * n;
        k = k % total;

        int[][] newGrid = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                int index = i * n + j;

                int newIndex = (index + k) % total;

                int row = newIndex / n;
                int col = newIndex % n;

                newGrid[row][col] = grid[i][j];
            }
        }

        List<List<Integer>> ans = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            List<Integer> temp = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                temp.add(newGrid[i][j]);
            }

            ans.add(temp);
        }

        return ans;
    }
}