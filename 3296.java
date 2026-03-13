class Solution {
    private int mountainHeight;
    private int[] workerTimes;

    /**
     * Finds the minimum number of seconds needed for workers to reduce the mountain to ground level.
     * Uses binary search to find the optimal time.
     *
     * @param mountainHeight The initial height of the mountain
     * @param workerTimes Array where workerTimes[i] represents the time for worker i to reduce height by 1
     * @return The minimum number of seconds needed
     */
    public long minNumberOfSeconds(int mountainHeight, int[] workerTimes) {
        this.mountainHeight = mountainHeight;
        this.workerTimes = workerTimes;

        // Binary search using standard template
        long left = 1;
        long right = (long) 1e16;
        long firstTrueIndex = -1;

        while (left <= right) {
            long mid = left + (right - left) / 2;

            if (canCompleteInTime(mid)) {
                // Feasible: can complete in 'mid' seconds
                firstTrueIndex = mid;
                right = mid - 1;  // Try to find smaller time
            } else {
                // Not feasible: need more time
                left = mid + 1;
            }
        }

        return firstTrueIndex;
    }

    /**
     * Checks if workers can reduce the mountain to ground level within given time.
     * Each worker reduces height following arithmetic progression: 1st unit takes workerTime[i],
     * 2nd unit takes 2*workerTime[i], nth unit takes n*workerTime[i].
     * Total time for n units: workerTime[i] * (1 + 2 + ... + n) = workerTime[i] * n * (n + 1) / 2
     *
     * @param timeLimit The time limit to check
     * @return true if the mountain can be reduced within timeLimit, false otherwise
     */
    private boolean canCompleteInTime(long timeLimit) {
        long totalHeightReduced = 0;

        for (int workerTime : workerTimes) {
            // For each worker, calculate maximum height they can reduce in timeLimit
            // Using formula: workerTime * n * (n + 1) / 2 <= timeLimit
            // Solving for n: n = (-1 + sqrt(1 + 8 * timeLimit / workerTime)) / 2
            // Simplified: n = sqrt(2 * timeLimit / workerTime + 0.25) - 0.5
            long maxHeightByWorker = (long) (Math.sqrt(timeLimit * 2.0 / workerTime + 0.25) - 0.5);
            totalHeightReduced += maxHeightByWorker;
        }

        // Check if total height reduced by all workers meets the requirement
        return totalHeightReduced >= mountainHeight;
    }
}
